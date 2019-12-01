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

    @GetMapping("/{shopId}")
    public ResponseServer getShopById(@PathVariable("shopId") Integer shopId ){
        return productService.getShopById(shopId);
    }



}
