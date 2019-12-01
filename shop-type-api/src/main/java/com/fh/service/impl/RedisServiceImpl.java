package com.fh.service.impl;

import com.fh.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    private Date createdate =new Date();

    @Override
    public void setKeyAndValue(String key, Object object) {
        System.out.println(createdate+"您向缓存中添加了一条key为"+key+"的数据（string）");
         redisTemplate.opsForValue().set(key,object);
    }

    @Override
    public Object getValueByKey(String key) {
        System.out.println(createdate+"您当前得到的数据来自缓存（string）");
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean isExistKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Boolean deleteCashByKey(String key) {
        System.out.println(createdate+"您删除了key为"+key+"的缓存数据");
        return redisTemplate.delete(key);
    }
}
