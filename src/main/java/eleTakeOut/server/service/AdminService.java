package eleTakeOut.server.service;

import eleTakeOut.pojo.dto.LoginDTO;
import eleTakeOut.pojo.vo.LoginVO;

public interface AdminService {
    /**
     * 管理员登录
     * @param loginDTO
     * @return
     */
    LoginVO login(LoginDTO loginDTO);
}
