package com.fh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.beans.po.UserBean;
import com.fh.dao.LoginDao;
import com.fh.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service("loginService")
public class LoginServcieImpl implements LoginServcie {
    @Autowired
    private LoginDao loginDao;

    @Override
    public UserBean getUserByPhone(String phonenum) {
        return loginDao.getUserByPhone(phonenum);
    }

    @Override
    public UserBean getUserByAccount(String account) {
        return loginDao.getUserByAccount(account);
    }


    @Override
    @Transactional
    public UserBean registerUser(String phonenum) {
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<UserBean>();
        queryWrapper.eq("telnum",phonenum);

        UserBean userBean1 = loginDao.selectOne(queryWrapper);
        if(userBean1 == null ){
            /*需要注册*/
            UserBean userBean = new UserBean();
            userBean.setTelnum(phonenum);
            userBean.setCartid(UUID.randomUUID().toString().replace("-",""));
            loginDao.insert(userBean);
            return userBean;
        }else{
            /*需要返回*/
            return userBean1;
        }

    }


}
