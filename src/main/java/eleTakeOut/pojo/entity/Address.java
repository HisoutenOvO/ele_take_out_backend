package eleTakeOut.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String tag;

    private String detail;//详细地址

    private String name;//收件人

    private String phone;

    private Integer isDefault;//默认地址 1是0否
}
