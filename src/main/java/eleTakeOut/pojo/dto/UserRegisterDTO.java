package eleTakeOut.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    private String username;//账号名称

    private String password;//md5加密

    private String phone;

    private String nickname;//用户名称

}
