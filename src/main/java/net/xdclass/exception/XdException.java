package net.xdclass.exception;

/**
 * 自定义异常类
 * @author 容
 * @version 1.0
 */
public class XdException extends RuntimeException{

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常消息
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public XdException() {
        super();
    }

    public XdException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
