package com.fh.controller;

import com.fh.service.RedisService;
import com.fh.service.TypeService;
import com.fh.enumUtil.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/type")
public class TypeControler {

    @Autowired
    private TypeService typeService;

    @Autowired
    private RedisService redisService;

    /**
     * 查询类别集合
     *
     * @return
     */
   /* @RequestMapping("/queryTypeList")
    @CrossOrigin(maxAge = 3000, origins = "http://localhost:8080", methods = RequestMethod.GET)
    public ResponseServer queryTypeList() {
        Object typeList = null;
        *//*查看缓存数据*//*
        Boolean typelist = redisService.isExistKey("typelist");
        if (!typelist) {
            System.out.println("缓存数据丢失从数据库查");
            typeList = typeService.queryTypeList();
            redisService.setKeyAndValue("typelist", typeList);
        } else {
            typeList = redisService.getValueByKey("typelist");
        }
        return ResponseServer.success(typeList);
    }*/
    @RequestMapping("/queryTypeList")
    @CrossOrigin(maxAge = 3000, origins = "http://localhost:8080", methods = RequestMethod.GET)
    public ResponseServer queryTypeList() {
        Object typeList = null;
        typeList = typeService.queryTypeList();
        return ResponseServer.success(typeList);
    }
}
