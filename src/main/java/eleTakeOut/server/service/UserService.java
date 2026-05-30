package eleTakeOut.server.service;

import eleTakeOut.pojo.dto.LoginDTO;
import eleTakeOut.pojo.dto.UserRegisterDTO;
import eleTakeOut.pojo.vo.LoginVO;

public interface UserService {
    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    LoginVO login(LoginDTO loginDTO);

    /**
     * 用户注册
     * @param userRegisterDTO
     */
    void register(UserRegisterDTO userRegisterDTO);
}
