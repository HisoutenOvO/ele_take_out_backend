package eleTakeOut.server.service.serviceImpl;


import eleTakeOut.common.context.BaseContext;
import eleTakeOut.pojo.dto.OrderSubmitDTO;
import eleTakeOut.pojo.entity.*;
import eleTakeOut.pojo.vo.*;
import eleTakeOut.server.mapper.AddressMapper;
import eleTakeOut.server.mapper.CartMapper;
import eleTakeOut.server.mapper.OrderMapper;
import eleTakeOut.server.mapper.ShopMapper;
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

    /**
     * 获取订单列表
     * @return
     */
    @Override
    public List<OrderVO> getList() {
        return orderMapper.getListByUserId(BaseContext.getCurrentUserId());
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
        //下单成功后清空购物车
        cartMapper.clearByUserIdAndShopId(BaseContext.getCurrentUserId(),shopId);
        //封装进OrderSubmitVO里，用builder简化代码
        return OrderSubmitVO.builder()
                .orderId(orderId)
                .number(number)
                .shopName(shopName)
                .notice(notice)
                .address(addressVO)
                .dishList(dishVOList)
                .deliveryFee(deliveryFee)
                .packingFee(packingFee)
                .totalPrice(totalPrice)
                .actualPayment(totalPrice + packingFee + deliveryFee - AccountFee)
                .build();
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
        orders.setStatus("已支付");
        orderMapper.updateById(orders);
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
        //把detail封装进orderVO
        orderDetailVO.setOrderDetailList(orderDetailList);
        orderDetailVO.setQuantity(orderDetailList.size());
        orderDetailVO.setShopName(shopMapper.getById(orders.getShopId()).getName());
        return orderDetailVO;
    }
}
