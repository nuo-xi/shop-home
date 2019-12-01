package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.beans.po.UserBean;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginDao extends BaseMapper<UserBean> {
    UserBean getUserByPhone(String phonenum);

    UserBean getUserByAccount(String account);
}
