package eleTakeOut.server.service.serviceImpl;


import eleTakeOut.common.exception.BaseException;
import eleTakeOut.common.properties.JwtProperties;
import eleTakeOut.common.utils.JwtUtils;
import eleTakeOut.pojo.dto.ChangePasswordDTO;
import eleTakeOut.pojo.dto.LoginDTO;
import eleTakeOut.pojo.entity.Admin;
import eleTakeOut.pojo.vo.LoginVO;
import eleTakeOut.server.mapper.AdminMapper;
import eleTakeOut.server.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final JwtProperties jwtProperties;

    /**
     * 管理员登录
     * @param loginDTO
     * @return
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        Admin admin = adminMapper.getByUserName(username);
        if(admin == null){
            throw new BaseException("用户不存在！");
        }
        String psw = admin.getPassword();
        //对密码进行md5加密后与数据库比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!admin.getPassword().equals(password)){
            throw new BaseException("用户名或密码错误！");
        }
        //若登录成功，则返回登录信息
        //创建token
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",admin.getId());
        claims.put("username",admin.getUsername());
        String token = JwtUtils.createJwt(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);

        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUsername(admin.getUsername());
        loginVO.setId(admin.getId());
        return loginVO;
    }

    /**
     * 管理员修改密码
     * @param
     * @param dto
     */
    @Override
    public void changePassword(Long adminId, ChangePasswordDTO dto) {
        Admin admin = adminMapper.selectById(adminId);
        String oldPasswordMd5 = DigestUtils.md5DigestAsHex(dto.getOldPassword().getBytes(StandardCharsets.UTF_8));
        if (!oldPasswordMd5.equals(admin.getPassword())) {
            throw new BaseException("旧密码错误");
        }
        admin.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8)));
        adminMapper.updateById(admin);
    }
}
