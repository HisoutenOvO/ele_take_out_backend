package eleTakeOut.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    /**
     * 创建JWT
     * @param secretKeyBase64 创建和解析令牌所需的base64编码，已在yml中配置好，直接调用jwtProperties的属性即可
     * @param ttl 令牌过期时间
     * @param claims 令牌载荷，用于存放关键信息的容器
     * @return 返回一个String类型的jwt
     */
    public static String createJwt(String secretKeyBase64, Long ttl, Map<String,Object> claims){
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBase64.getBytes(StandardCharsets.UTF_8));
        long ttlMillis = System.currentTimeMillis() + ttl;
        Date date = new Date(ttlMillis);
        return Jwts.builder()
                .claims(claims)
                .expiration(date)
                .signWith(secretKey)
                .compact();
    }

    /**
     * 解析JWT
     * @param secretKeyBase64 同上
     * @param token 要解析的token
     * @return 解析后返回解析出来的载荷
     */
    public static Claims parseJwt(String secretKeyBase64,String token){
        SecretKey key = Keys.hmacShaKeyFor(secretKeyBase64.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                //创建载荷
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
