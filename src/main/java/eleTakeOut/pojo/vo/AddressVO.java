package eleTakeOut.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO {

    private Long userId;

    private String tag;

    private String detail;//详细地址

    private String name;//收件人

    private String phone;

    private Integer isDefault;//默认地址 1是0否
}
