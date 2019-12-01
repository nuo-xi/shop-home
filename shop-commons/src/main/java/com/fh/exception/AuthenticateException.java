package com.fh.exception;

import com.fh.enumUtil.EnumService;
import lombok.Getter;

/**
 * 自定义异常 随便放
 */
@Getter
public class AuthenticateException extends RuntimeException {

    //自定义异常状态码
    private Integer code ;

    public AuthenticateException(EnumService enumService) {
        super(enumService.getMessage());
        this.code=enumService.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
