package eleTakeOut.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderShopDetailVO {

    private Long id;

    private String username;

    private String phone;

    private String number;

    private Double totalPrice;

    private Double deliveryFee;

    private Double packingFee;

    private Double actualPayment;

    private List<OrderDishVO> dishList;

    private OrderAddressVO address;

    private Integer payMethod;

    private String status;

}