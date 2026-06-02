package eleTakeOut.pojo.vo;

import eleTakeOut.pojo.entity.Address;
import eleTakeOut.pojo.entity.Cart;
import eleTakeOut.pojo.entity.Dish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSubmitVO {

    private Long orderId;

    private String notice;

    private String shopName;

    private String number;

    private Double totalPrice;

    private Double deliveryFee;

    private Double packingFee;

    private Double actualPayment;

    private List<OrderDishVO> dishList;

    private OrderAddressVO address;

}