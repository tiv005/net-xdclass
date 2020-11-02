package net.xdclass.exception;

import net.xdclass.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理控制器
 * @author 容
 * @version 1.0
 */
@ControllerAdvice
public class XdExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData Handler(Exception e){

        if(e instanceof XdException){
            XdException xdException = (XdException)e;
            return JsonData.buildError(xdException.getMsg(),xdException.getCode());
        }else{
            return JsonData.buildError("全局异常,位置错误");
        }
    }
}
