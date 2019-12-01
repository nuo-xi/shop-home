package com.fh.controller;

import com.fh.exception.AuthenticateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一的异常处理
 * 放在controller层（必）
 * 异常从上往下执行
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AuthenticateException.class)
    public void authenticateException(AuthenticateException e, HttpServletRequest request, HttpServletResponse response){
        response.setHeader("TOKEN_TIMEOUT","6004");
    }
}
