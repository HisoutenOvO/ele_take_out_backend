package eleTakeOut.pojo.vo;


import eleTakeOut.pojo.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVO  extends OrderVO{

    List<OrderDetail> orderDetailList;//订单详情

}
