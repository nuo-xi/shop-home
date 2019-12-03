package com.fh.controller;

import com.fh.enumUtil.ResponseServer;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/httpShops")
public class HttpShopController {

    @Autowired
    private ProductService productService;

    /**
     * 通过商品id获取商品的信息
     *
     * @param shopId
     * @return
     */
    @GetMapping("/{shopId}")
    public ResponseServer getShopById(@PathVariable("shopId") Integer shopId ){
        return productService.getShopById(shopId);
    }



}
