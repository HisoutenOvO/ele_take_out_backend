package eleTakeOut.common.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private Integer code; //编码：200成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据

    /**
     * 无参数的成功函数
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.code = 200;
        return result;
    }

    /**
     * 有参数的成功函数
     * @param object
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T object){
        Result<T> result = new Result<>();
        result.code = 200;
        result.data = object;
        return result;
    }

    /**
     * 失败函数
     * @param msg
     * @return
     * @param <T>
     */
    public static <T> Result<T> error(String msg){
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }
}
