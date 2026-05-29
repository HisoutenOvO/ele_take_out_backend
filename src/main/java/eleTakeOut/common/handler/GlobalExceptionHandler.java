package eleTakeOut.common.handler;


import eleTakeOut.common.exception.BaseException;
import eleTakeOut.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，用于捕获所有Controller抛出的异常并返回给前端
 */
@RestControllerAdvice(basePackages = ("eleTakeOut.server.controller"))
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result globalExceptionHandler(BaseException e){
        log.error("业务异常：{}",e.getMessage());
        return Result.error(e.getMessage());
    }
}
