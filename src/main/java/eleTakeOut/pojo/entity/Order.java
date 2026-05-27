package eleTakeOut.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @TableId(type = IdType.AUTO)
    public Long id;

    public String number;//订单号

    public Long userId;

    public Long shopId;

    public Long addressId;

    public String status;//状态(待支付/已支付/配送中/已完成/已取消/已退款)

    public Double totalPrice;

    public Double deliveryFee;

    public Double actualPayment;//实付金额=总价-优惠（优惠是前端固定的金额）

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime createTime;


}
