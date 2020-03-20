package com.liqj.servise;

import com.liqj.dao.UserDao;
import com.liqj.entity.Dept;
import com.liqj.entity.Page;
import com.liqj.entity.User;
import com.liqj.utils.DateUtil;

import java.util.List;

/**
 * @author Liqj
 * @date 2020/3/18 13:53
 */
public class UserServise {
    UserDao userDao=new UserDao();

    public List<User> listAll(User user, Page page){
        return userDao.listAll(user,page);
    }

    public User selectUserById(Integer id){
        return userDao.selectUserById(id);
    }

    public Integer count(User user) {
        return userDao.count(user);
    }

    /**
     * @author Liqj
     * @date 2020/3/18
     * @params [user]
     * @return java.lang.Integer
     * @description 检查用户名是否相同
     */
    public Integer findUserName(User user){
        if (user.getUsername()==null||user.getUsername()==""){
            return null;
        }
        return userDao.findUserName(user);
    }

    public Integer findEmail(User user) {
        if(user.getEmail()==null){
            return null;
        }
        return userDao.findEmail(user);
    }

    public void register(User user){
        String dateStr= DateUtil.dateStr();
        user.setRegisterTime(dateStr);
        user.setCreateTime(dateStr);
        user.setCreateBy(null);
         userDao.register(user);
    }

    public void uploadPic(Integer id,String path){
        if(id==null||path==null){
            return;
        }
        userDao.uploadPic(id,path);
    }
}
