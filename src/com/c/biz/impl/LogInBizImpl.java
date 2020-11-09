package com.c.biz.impl;


import com.c.biz.LogInBiz;
import com.c.dao.impl.LoginDaoImpl;
import com.c.entity.User;

public class LogInBizImpl implements LogInBiz {
    LoginDaoImpl loginDao = new LoginDaoImpl();
    @Override
    public boolean logIn(User user) {
        if ( user==null ) {
            return false;
        }
        User user1 = loginDao.queryxThereUser(user);
        if ( user1 == null ) {
            return false;
        }
        return true;
    }
}
