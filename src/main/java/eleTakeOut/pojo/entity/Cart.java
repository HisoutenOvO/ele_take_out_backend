package eleTakeOut.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String image;

    private Long userId;

    private Long dishId;

    private Long shopId;

    private Integer number;

    private Double amount;
}
