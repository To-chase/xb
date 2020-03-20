package com.liqj.servise;

import com.liqj.dao.LoginDao;
import com.liqj.entity.User;

/**
 * @author Liqj
 * @date 2020/3/17 18:11
 */
public class LoginServise {
    private LoginDao loginDao=new LoginDao();
    public User checkLogin(User user){
        return loginDao.checkLogin(user);
    }
}
