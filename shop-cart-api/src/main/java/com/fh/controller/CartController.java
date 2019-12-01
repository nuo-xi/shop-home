package com.fh.controller;

import com.fh.annotation.TokenAnnotation;
import com.fh.enumUtil.ResponseServer;
import com.fh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/carts")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080",exposedHeaders = "TOKEN_TIMEOUT")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加购物车
     *
     * @param shopId
     * @param request
     * @return
     */
    @GetMapping
    @TokenAnnotation
    public ResponseServer addCart(String shopId, HttpServletRequest request){
        /*从aop中获取电话号码*/
        String  telnum =(String) request.getAttribute("phonenum");
        return cartService.addCart(telnum,shopId);
    }

    @PostMapping
    @TokenAnnotation
    public ResponseServer queryCartById(HttpServletRequest request){
        /*从aop中获取电话号码*/
        String  telnum =(String) request.getAttribute("phonenum");
        return cartService.queryCartById(telnum);
    }



}
