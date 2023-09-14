package com.clkj.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel("返回数据")
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS_CODE = 0;

    public static final String SUCCESS_MSG = "success";


    @ApiModelProperty("状态码 0:成功,500:失败")
    int code;

    @ApiModelProperty("消息")
    String msg;

    @ApiModelProperty("数据")
    T data;

    public R() {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private static <T> R<T> build(int code, String msg, T data) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static <T> R<T> error(int code, String msg) {
        return build(code, msg, null);
    }

    public static <T> R<T> ok(String msg) {
        return build(SUCCESS_CODE, msg, null);
    }

    public static <T> R<T> ok() {
        return build(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public static <T> R<T> data(T data) {
        return build(SUCCESS_CODE, SUCCESS_MSG, data);
    }
}
