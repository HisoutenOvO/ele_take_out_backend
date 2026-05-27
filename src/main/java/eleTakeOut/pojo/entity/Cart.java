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
    public Long id;

    public String name;

    public String image;

    public Long userId;

    public Long dishId;

    public Integer number;

    public Double amount;
}
