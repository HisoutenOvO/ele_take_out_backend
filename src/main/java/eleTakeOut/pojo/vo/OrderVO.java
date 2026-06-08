package eleTakeOut.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import eleTakeOut.pojo.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO {

    private Long id;//订单id

    private Long shopId;

    private String shopName;

    private String shopImage;

    private String status;//状态(待支付/已支付/配送中/已完成/已取消/已退款)

    private Double actualPayment;//实付金额=总价-优惠（优惠是前端固定的金额）

    private String dishName;

    private String dishImage;

    private Integer quantity;//件数

}
