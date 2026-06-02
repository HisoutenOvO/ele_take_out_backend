package eleTakeOut.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddressVO {

    private String name;

    private String phone;

    private String detail;

    private String tag;

}