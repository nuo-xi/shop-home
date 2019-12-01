package com.fh.controller;

import com.fh.beans.vo.BrandBeanVo;
import com.fh.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandConreoller {

    @Autowired
    private BrandService brandService;

    /**
     * 点击类型获取 类型下面的品牌
     * @return
     */
    @RequestMapping("queryBrandList")
    @CrossOrigin(maxAge = 3000,origins = "http://localhost:8080",methods = RequestMethod.GET)
    public List<BrandBeanVo> queryBrandList(String typeId){
        System.out.println("传进来的类型id是：---"+typeId);
        return brandService.queryBrandList(typeId);
    }
}
