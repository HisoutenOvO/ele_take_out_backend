package eleTakeOut.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

    private String number;//订单号

    private Long userId;

    private Long shopId;

    private Long addressId;

    private String status;//状态(待支付/已支付/配送中/已完成/已取消/已退款)

    private Double totalPrice;

    private Double deliveryFee;

    private Double actualPayment;//实付金额=总价-优惠（优惠是前端固定的金额）

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
