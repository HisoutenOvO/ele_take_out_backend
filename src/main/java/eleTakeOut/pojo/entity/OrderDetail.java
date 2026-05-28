package eleTakeOut.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String image;

    private Long categoryId;

    private Long dishId;

    private Integer number;

    private Double amount;//菜品单价
}
