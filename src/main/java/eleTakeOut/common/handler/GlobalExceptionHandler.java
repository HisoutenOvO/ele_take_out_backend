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

    @ExceptionHandler
    public Result sqlExceptionHandler(SQLIntegrityConstraintViolationException e){
        String message = e.getMessage();
        if(message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String errValue = split[2]; // '1-人气推荐' 或 'xxx'
            // 去掉首尾的单引号
            errValue = errValue.replace("'", "");
            // 如果包含 '-'，说明是联合唯一索引的冲突
            if(errValue.contains("-")){
                // 提取名称部分（去掉前缀的 shopId-）
                String name = errValue.substring(errValue.indexOf("-") + 1);
                return Result.error(name + "已存在");
            }
            return Result.error(errValue + " 已存在");
        }
        return Result.error("未知错误");
    }
}
