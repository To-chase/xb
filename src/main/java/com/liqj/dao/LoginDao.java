package com.liqj.dao;

import com.liqj.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @author Liqj
 * @date 2020/3/17 18:08
 */
public class LoginDao extends BaseDao {
    public User checkLogin(User user){
        String sql="select * from sys_user where username=? and password=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword());
    }
}
