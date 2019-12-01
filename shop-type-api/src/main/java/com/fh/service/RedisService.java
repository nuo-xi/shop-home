package com.fh.service;

public interface RedisService {

    /**
     * 存放数据
     *
     * @param key
     * @param object
     */
    void setKeyAndValue(String key, Object object);

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    Object getValueByKey(String key);

    /**
     * 判断key
     *
     * @param key
     * @return
     */
    Boolean isExistKey(String key);

    /**
     * 通过key删除cash
     *
     * @param key
     * @return
     */
    Boolean deleteCashByKey(String key);
}
