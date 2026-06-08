package eleTakeOut.server.service.serviceImpl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.OrderPageQueryDTO;
import eleTakeOut.pojo.dto.OrderSubmitDTO;
import eleTakeOut.pojo.entity.*;
import eleTakeOut.pojo.vo.*;
import eleTakeOut.server.mapper.*;
import eleTakeOut.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    //设置一个优惠券常量，就不再单独设置优惠了
    public static Double AccountFee = 19.0;

    private final OrderMapper orderMapper;
    private final AddressMapper addressMapper;
    private final ShopMapper shopMapper;
    private final CartMapper cartMapper;
    private final UserMapper userMapper;
    private final DishMapper dishMapper;

    /**
     * 获取订单列表
     * @return
     */
    @Override
    public List<OrderVO> getList() {
        List<Orders> orderList = orderMapper.getListByUserId(BaseContext.getCurrentUserId());
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Orders order : orderList) {
            List<DishOrderVO> dishList = dishMapper.getDishListByOrderId(order.getId());
            String dishName = "";
            Integer quantity = 0;
                for (DishOrderVO dish : dishList) {
                    dishName += dish.getName() + " ";
                    quantity += dish.getNumber();
                }
            String dishImage = dishList.get(0).getImage();
            OrderVO vo = OrderVO.builder()
                    .id(order.getId())
                    .shopId(order.getShopId())
                    .shopName(shopMapper.getById(order.getShopId()).getName())
                    .shopImage(shopMapper.getById(order.getShopId()).getImage())
                    .status(order.getStatus())
                    .actualPayment(order.getActualPayment())
                    .dishName(dishName)
                    .dishImage(dishImage)
                    .quantity(quantity)
                    .build();
            orderVOList.add(vo);
        }
        return orderVOList;
    }

    /**
     * 提交订单
     * @param orderSubmitDTO
     * @return
     */
    @Override
    @Transactional
    public OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO) {
        Long shopId = orderSubmitDTO.getShopId();
        Long addressId = orderSubmitDTO.getAddressId();
        //先获取地址信息
        Address address = addressMapper.getByIdAndUserId(addressId,BaseContext.getCurrentUserId());
        OrderAddressVO addressVO = new OrderAddressVO();
        BeanUtils.copyProperties(address,addressVO);
        //再获取店铺信息
        Shop shop = shopMapper.getById(shopId);
        String shopName = shop.getName();
        String notice = shop.getNotice();
        //最后获取购物车的菜品信息
        List<Cart> cartList = cartMapper.getByDishIdAndUserIdAndShopId(null,BaseContext.getCurrentUserId(),shopId);
        List<OrderDishVO> dishVOList = new ArrayList<>();
        for (Cart cart : cartList) {
            OrderDishVO vo = new OrderDishVO();
            BeanUtils.copyProperties(cart,vo);
            dishVOList.add(vo);
        }
        Double deliveryFee = shop.getDeliveryFee();
        Double packingFee = (double)1;
        Double totalPrice = 0.0;
        for (Cart cart : cartList) {
            totalPrice += cart.getAmount() * cart.getNumber();
        }
        //生成订单号: 当前时间 + 用户id
        String number = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + BaseContext.getCurrentUserId();
        //新增订单
        Orders orders = Orders.builder()
                .number(number)
                .actualPayment(totalPrice + packingFee + deliveryFee - AccountFee)
                .addressId(addressId)
                .packingFee(packingFee)
                .deliveryFee(deliveryFee)
                .shopId(shopId)
                .userId(BaseContext.getCurrentUserId())
                .totalPrice(totalPrice)
                .status("待支付")
                .build();
        orderMapper.insert(orders);
        //补全OrderDetail表
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Cart cart : cartList) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .name(cart.getName())
                    .image(cart.getImage())
                    .amount(cart.getAmount())
                    .dishId(cart.getDishId())
                    .number(cart.getNumber())
                    .orderId(orders.getId())
                    .build();
            orderDetailList.add(orderDetail);
        }
        orderMapper.insertOrderDetailList(orderDetailList);
        //回显订单id
        Long orderId = orders.getId();
        OrderSubmitVO orderSubmitVO = new OrderSubmitVO();
        orderSubmitVO.setId(orderId);
        return orderSubmitVO;
    }

    /**
     * 支付订单
     * @param id
     * @param payMethod
     */
    @Override
    public void payment(Long id, Integer payMethod) {
        Orders orders = orderMapper.selectById(id);
        orders.setPayMethod(payMethod);
        orders.setStatus("已完成");
        orderMapper.updateById(orders);
        //支付成功后清空购物车
        cartMapper.clearByUserIdAndShopId(BaseContext.getCurrentUserId(),orders.getShopId());
    }

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @Override
    public OrderDetailVO getDetail(Long id) {
        Orders orders = orderMapper.selectById(id);
        List<OrderDetail> orderDetailList = orderMapper.getDetailListByOrderId(id);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtils.copyProperties(orders,orderDetailVO);
        //把address封装进orderDetailVO
        Address address = addressMapper.selectById(orders.getAddressId());
        OrderAddressVO addressVO = new OrderAddressVO();
        BeanUtils.copyProperties(address,addressVO);
        orderDetailVO.setAddress(addressVO);
        //把detail封装进orderVO
        orderDetailVO.setOrderDetailList(orderDetailList);
        orderDetailVO.setQuantity(orderDetailList.size());
        orderDetailVO.setShopName(shopMapper.getById(orders.getShopId()).getName());
        orderDetailVO.setOrderNo(orders.getNumber());
        return orderDetailVO;
    }

    /**
     * 订单列表查询
     * @param orderPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO) {
        PageHelper.startPage(orderPageQueryDTO.getPage(),orderPageQueryDTO.getPageSize());
        orderPageQueryDTO.setShopId(BaseContext.getCurrentShopId());
        Page<OrderListVO> page = orderMapper.pageQuery(orderPageQueryDTO);
        Long total = page.getTotal();
        List<OrderListVO> records = page.getResult();
        return new PageResult(total,records);
    }

    /**
     * 商家端查询详情
     * @param id
     * @return
     */
    @Override
    public OrderShopDetailVO getByIdWithShop(Long id) {
        OrderShopDetailVO orderShopDetailVO = new OrderShopDetailVO();
        Orders orders = orderMapper.selectById(id);
        BeanUtils.copyProperties(orders,orderShopDetailVO);
        orderShopDetailVO.setId(id);
        User user = userMapper.selectById(orders.getUserId());
        String nickname = user.getNickname();
        String phone = user.getPhone();
        Address address = addressMapper.selectById(orders.getAddressId());
        OrderAddressVO orderAddressVO = new OrderAddressVO();
        BeanUtils.copyProperties(address,orderAddressVO);
        List<OrderDishVO> dishVOList = new ArrayList<>();
        List<OrderDetail> orderDetailList = orderMapper.getDetailListByOrderId(id);
        for (OrderDetail orderDetail : orderDetailList) {
            OrderDishVO orderDishVO = new OrderDishVO();
            BeanUtils.copyProperties(orderDetail,orderDishVO);
            dishVOList.add(orderDishVO);
        }
        orderShopDetailVO.setDishList(dishVOList);
        orderShopDetailVO.setNickname(nickname);
        orderShopDetailVO.setPhone(phone);
        orderShopDetailVO.setAddress(orderAddressVO);
        return orderShopDetailVO;
    }
}
