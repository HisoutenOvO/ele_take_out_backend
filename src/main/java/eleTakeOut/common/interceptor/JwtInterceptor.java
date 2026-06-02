package eleTakeOut.common.interceptor;

import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.exception.BaseException;
import eleTakeOut.common.properties.JwtProperties;
import eleTakeOut.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    //注入jwt属性
    private final JwtProperties jwtProperties;

    /**
     * 校验jwt
     */
    @Override
    //这三个参数分别代表了请求、响应、处理方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        //判断当前拦截到的是否是controller里的方法，我们只要controller里的方法
        if(!(handler instanceof HandlerMethod)){
            //HandlerMethod就是controller一系列方法
            return true;
        }
        //在请求头中拿到token令牌
        String token = request.getHeader(jwtProperties.getTokenName());
        //然后校验令牌
        try{
            log.info("开始校验令牌:{}",token);
            //调用jwt工具类，使用jwtProperties中的secret-key属性解析，成功则返回claims对象，失败抛出业务异常
            Claims claims = JwtUtils.parseJwt(jwtProperties.getSecretKey(),token);
            //获取当前操作者的id
            Long id = Long.valueOf(claims.get("id").toString());
            //存放解析出来的id，用于后续登录等相关操作使用，注意不同的客户端存放不同id
            log.info("校验完毕，id是{}",id);
            String url = request.getRequestURI();
            if (url.startsWith("/admin/")) {
                BaseContext.setCurrentAdminId(id);
            } else if(url.startsWith("/user/")){
                BaseContext.setCurrentUserId(id);
            }else{
                BaseContext.setCurrentShopId(id);
            }
            return true;
        }catch (Exception e){
            log.info("令牌校验失败");
            //校验失败则抛出业务异常
            throw new BaseException("用户未登录");
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        BaseContext.clear();
    }
}
