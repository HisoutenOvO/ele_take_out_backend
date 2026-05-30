package eleTakeOut;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement//开启事务
public class EleApplication {
    public static void main(String[] args) {
        SpringApplication.run(EleApplication.class,args);
        log.info("饿了么外卖项目启动完成");
    }
}
