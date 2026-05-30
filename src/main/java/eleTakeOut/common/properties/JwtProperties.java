package eleTakeOut.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    //密钥
    private String secretKey;
    //过期时间
    private Long ttl;
    //token名称
    private String tokenName;

}
