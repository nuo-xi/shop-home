package com.fh.controller;

import com.fh.annotation.TokenAnnotation;
import com.fh.enumUtil.ResponseServer;
import com.fh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/cartList")
@CrossOrigin(maxAge = 3600, origins = "http://localhost:8080", exposedHeaders = "TOKEN_TIMEOUT")
public class CartListController {

    @Autowired
    private CartService cartService;

    /**
     * 用户打开购物车
     *
     * @return
     */
    @GetMapping
    @TokenAnnotation
    public ResponseServer queryMyCart() {
        return ResponseServer.success();
    }

    /**
     * 获取用户的购物车列表
     *
     * @param request
     * @return
     */
    @PostMapping
    @TokenAnnotation
    public ResponseServer queryMyCart(HttpServletRequest request) {
        String telnum = (String) request.getAttribute("phonenum");
        Map<String, Object> cartMap = cartService.queryCartList(telnum);
        return ResponseServer.success(cartMap);
    }

    /**
     * 用户操作购物车
     *
     * @param shopId  商品id
     * @param type  操作的类型  2,选中或者取消选中
     * @param request
     * @return
     */
    @GetMapping("/{shopId}/{type}")
    @TokenAnnotation
    public ResponseServer updateShop(@PathVariable("shopId") String shopId,
                                     @PathVariable("type") Integer type,
                                     HttpServletRequest request) {
        String telnum = (String) request.getAttribute("phonenum");

        return cartService.updateShop(telnum, shopId, type);
    }


    /**
     * 更改了输入框
     *
     * @return
     */
    @PutMapping("/{shopId}/{sum}")
    @TokenAnnotation
    public ResponseServer changeShopNum(@PathVariable("shopId") String shopId,
                                        @PathVariable("sum") String sum,
                                        HttpServletRequest request){
        String telnum = (String) request.getAttribute("phonenum");
        return cartService.changeShopNum(shopId,telnum,sum);
    }
}
