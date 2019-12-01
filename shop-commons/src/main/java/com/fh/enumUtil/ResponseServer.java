package com.fh.enumUtil;

public class ResponseServer {

    /*状态码*/
    private Integer code;

    /*提示信息*/
    private String message;

    /*数据*/
    private Object data;

    /*返回有数据*/
    public ResponseServer(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /*无数据返回*/
    public ResponseServer(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /*成功*/
    public static ResponseServer success() {
        return new ResponseServer(EnumService.LOGIN_SUCCESS.getCode(), EnumService.LOGIN_SUCCESS.getMessage());
    }

    public static ResponseServer success(Object data) {
        return new ResponseServer(EnumService.LOGIN_SUCCESS.getCode(), EnumService.LOGIN_SUCCESS.getMessage(), data);
    }

    public static ResponseServer success(EnumService enumService) {
        return new ResponseServer(enumService.getCode(), enumService.getMessage());
    }

    /*失败*/
    public static ResponseServer error() {
        return new ResponseServer(EnumService.LOGIN_ERROR.getCode(), EnumService.LOGIN_ERROR.getMessage());
    }

    public static ResponseServer error(EnumService enumService) {
        return new ResponseServer(enumService.getCode(), enumService.getMessage());
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
