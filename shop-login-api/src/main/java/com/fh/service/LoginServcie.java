package com.fh.service;

import com.fh.beans.po.UserBean;

public interface LoginServcie {
    UserBean getUserByPhone(String phonenum);

    UserBean getUserByAccount(String account);

    UserBean registerUser(String phonenum);
}
