package eleTakeOut.server.service.serviceImpl;

import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.exception.BaseException;
import eleTakeOut.pojo.dto.OrderSubmitDTO;
import eleTakeOut.pojo.entity.Address;
import eleTakeOut.pojo.entity.Cart;
import eleTakeOut.pojo.entity.Shop;
import eleTakeOut.pojo.vo.OrderSubmitVO;
import eleTakeOut.pojo.vo.OrderVO;
import eleTakeOut.server.mapper.AddressMapper;
import eleTakeOut.server.mapper.CartMapper;
import eleTakeOut.server.mapper.OrderMapper;
import eleTakeOut.server.mapper.ShopMapper;
import eleTakeOut.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO) {
        Long shopId = orderSubmitDTO.getShopId();
        Long addressId = orderSubmitDTO.getAddressId();
        //先获取地址信息
        Address address = addressMapper.getByIdAndUserId(addressId,BaseContext.getCurrentUserId());
        //再获取店铺信息
        Shop shop = shopMapper.getById(shopId);
        String shopName = shop.getName();
        String notice = shop.getNotice();
        //最后获取购物车的菜品信息
        List<Cart> cartList = cartMapper.getByDishIdAndUserIdAndShopId(shopId,BaseContext.getCurrentUserId(),shopId);
        Double deliveryFee = shop.getDeliveryFee();
        Double packingFee = shop.getPackingFee();
        Double totalPrice = 0.0;
        for (Cart cart : cartList) {
            totalPrice += cart.getAmount();
        }
        //封装进OrderSubmitVO里，用builder简化代码
        return OrderSubmitVO.builder()
                .shopName(shopName)
                .notice(notice)
                .address(address)
                .dishList(cartList)
                .deliveryFee(deliveryFee)
                .packingFee(packingFee)
                .totalPrice(totalPrice)
                .actualPayment(totalPrice + packingFee + deliveryFee - AccountFee)
                .build();
    }
}
