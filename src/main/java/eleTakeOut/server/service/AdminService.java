package eleTakeOut.server.service;

import eleTakeOut.pojo.dto.ChangePasswordDTO;
import eleTakeOut.pojo.dto.LoginDTO;
import eleTakeOut.pojo.vo.LoginVO;

public interface AdminService {
    /**
     * 管理员登录
     * @param loginDTO
     * @return
     */
    LoginVO login(LoginDTO loginDTO);

    /**
     * 管理员修改密码
     * @param adminId
     * @param dto
     */
    void changePassword(Long adminId, ChangePasswordDTO dto);
}
