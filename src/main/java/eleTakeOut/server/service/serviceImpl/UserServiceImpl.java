package eleTakeOut.server.service.serviceImpl;

import eleTakeOut.common.exception.BaseException;
import eleTakeOut.common.properties.JwtProperties;
import eleTakeOut.common.utils.JwtUtils;
import eleTakeOut.pojo.dto.LoginDTO;
import eleTakeOut.pojo.dto.UserRegisterDTO;
import eleTakeOut.pojo.entity.User;
import eleTakeOut.pojo.vo.LoginVO;
import eleTakeOut.server.mapper.UserMapper;
import eleTakeOut.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final JwtProperties jwtProperties;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();;
        String password = loginDTO.getPassword();

        User user = userMapper.getByUsername(username);
        if(user == null){
            throw new BaseException("用户不存在！");
        }
        String psw = user.getPassword();
        //对密码进行md5加密后与数据库比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!user.getPassword().equals(password)){
            throw new BaseException("用户名或密码错误！");
        }
        //若登录成功，则返回登录信息
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("username",user.getUsername());
        String token = JwtUtils.createJwt(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        return new LoginVO(user.getId(),user.getUsername(),token);
    }

    /**
     * 用户注册
     * @param userRegisterDTO
     */
    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getUsername();
        String phone = userRegisterDTO.getPhone();
        if(userMapper.getByPhone(phone) != null){
            throw new BaseException("手机号已注册！");
        }
        if(userMapper.getByUsername(username) != null){
            throw new BaseException("用户名已存在！");
        }
        String password = userRegisterDTO.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO,user);
        user.setPassword(password);
        userMapper.insert(user);
    }
}
