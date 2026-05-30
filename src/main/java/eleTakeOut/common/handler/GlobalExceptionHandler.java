package eleTakeOut.common.handler;


import eleTakeOut.common.exception.BaseException;
import eleTakeOut.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

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

    /**
     * 捕获sql异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result sqlExceptionHandler(SQLIntegrityConstraintViolationException e){
        //sql中报错重复的字段时会显示 Duplicate entry 'xxx' for key 'xxx'
        String message = e.getMessage();
        //用string的contains方法判断是否是sql异常
        if(message.contains("Duplicate entry")){
            //通过用空格作分隔符依次将字符串Duplicate entry 'xxx' for key 'xxx'获取到split里
            String[] split = message.split(" ");
            //调用split[2]，即'xxx'和常量异常信息拼凑成异常信息
            String errMessage = split[2] + "已存在";
            return Result.error(errMessage);
        }else{
            //不是这个错误则返回未知错误
            return Result.error("未知错误");
        }
    }
}
