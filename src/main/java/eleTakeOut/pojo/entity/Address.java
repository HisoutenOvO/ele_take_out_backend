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
    public Long id;

    public Long userId;

    public String tag;

    public String detail;//详细地址

    public String name;//收件人

    public String phone;

    public Integer isDefault;//默认地址 1是0否
}
