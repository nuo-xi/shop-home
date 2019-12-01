package com.fh.enumUtil;

public enum EnumService {

    /**
     * 登录使用的状态码
     */
      LOGIN_ACCOUNT_PWD_ISNULL(4001, "登录用户账号或密码不存在")
    , LOGIN_USER_NOT_EXIST(4003, "用户不存在")
    , REIGSTER_USER_NOT_EXIST(4004, "用户已存在")
    , LOGIN_USER_EXCEPTION(4005,"用户登录异常")
    , LOGIN_USER_PWD_NOT_TRUE(4007, "用户密码不正确")
    , LOGIN_USER_LOCK(4009, "用户已锁定，请联系管理员")
    , LOGIN_USER_NOT_RIGHT(4011, "用户没有权限，请联系管理员")

    , LOGIN_SUCCESS(2000, "登录成功")
    , CODE_SUCCESS(2001, "验证码发送成功")
    , DELETE_SUCCESS(2002,"删除成功")
    , ADD_SUCCESS(2003,"增加成功")

    , LOGIN_ERROR(5001, "登录失败")
    , DELETE_ERROR(5003,"删除失败")
    , ADD_ERROR(5004,"增加失败")

    , SERVER_ERROR(6001, "连接失败")
    , SERVER_BUSYNESS(6002, "服务器连接超时")
    , SERVER_CONNECT_ERROR(6003, "服务器连接失败")
    , TOKEN_TIMEOUT(6004, "token为空")
    , SAFETY_ERROR(6005, "token解析失败")

    /**
     * 传参使用的状态码
     */
    , PARAMETER_IS_NULL(7001,"参数为空")
    , LOGIN_CODE_IS_NULL(7002,"验证码为空")
    , LOGIN_CODE_IS_LOSE(7003,"验证码无效")


    /**
     * 业务使用的状态码
     */




    ;

    private Integer code;
    private String message;

    EnumService(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
