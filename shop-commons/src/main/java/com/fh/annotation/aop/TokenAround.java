package com.fh.annotation.aop;

import com.fh.JWT.JwtUtils;
import com.fh.annotation.TokenAnnotation;
import com.fh.enumUtil.EnumService;
import com.fh.enumUtil.ResponseServer;
import com.fh.exception.AuthenticateException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(5)  /*aop执行的顺序  默认：2147483647 */
public class TokenAround {

    /* aop环绕通知 */
    @Around(value = "execution(* com.fh.controller.*.*(..))&&@annotation(tokenAnnotation)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint ,TokenAnnotation tokenAnnotation){
        /*从request中获取token 值*/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");

        /*验证token 值*/
        /*如果前端传来  null  则不能判断 */
        if(StringUtils.isBlank(token)){
            /*token值为空*/
            throw new AuthenticateException(EnumService.TOKEN_TIMEOUT);
        }

        /*解析token 值*/
        ResponseServer responseServer = JwtUtils.resolverToken(token);
        Integer code = responseServer.getCode();
        if(code != 2000){
            /*token解析失败*/
            throw new AuthenticateException(EnumService.SAFETY_ERROR);
        }

        /*取出解析数据*/
        Claims claims = (Claims)responseServer.getData();
        /*取出电话号码  放到request中  （业务需求） */
        String  phonenum = (String) claims.get("phonenum");
        request.setAttribute("phonenum",phonenum);

        Object obj =null;
        try {
            obj=joinPoint.proceed();
            /*后置通知*/
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            /*异常通知*/
        }
        return obj;
    }
}
