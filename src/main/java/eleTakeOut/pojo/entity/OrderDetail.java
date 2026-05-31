package eleTakeOut.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String image;

    private Long dishId;

    private Long orderId;

    private Integer number;

    private Double amount;//菜品单价
}
