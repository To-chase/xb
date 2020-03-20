package com.liqj.dao;

import com.liqj.entity.Dept;
import com.liqj.entity.Page;
import com.liqj.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author Liqj
 * @date 2020/3/17 18:01
 */
public class UserDao extends BaseDao {

    public List<User> listAll(User user, Page page) {
        String sql = "SELECT " +
                " u.id id, " +
                " u.dept_id deptId, " +
                " u.username username, " +
                " u.real_name realName, " +
                " u.age age, " +
                " u.phone phone, " +
                " u.sex sex, " +
                " u.pic pic, " +
                " u.look look, " +
                " u.is_secret isSecret, " +
                " u.create_time createTime, " +
                " u.create_by createBy, " +
                " su.username createName  " +
                "FROM " +
                " sys_user u " +
                " LEFT JOIN sys_user su ON u.create_by = su.id " +
                " where u.username like ? " +
                " limit ? , ? ";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + user.getUsername()+ "%", (page.getPageCurrent() - 1) * page.getPageSize(),page.getPageSize());
    }

    public User selectUserById(Integer id){
        String sql="select id,dept_id,username,password,email,qq_openid,wx_openid,real_name,age,phone,sex,description,register_time,login_time,pic,look,is_secret,create_time,create_by,del_flag "+
                " from sys_user where id=? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id);
    }

    public Integer count(User user) {
        String sql = "select count(*) from sys_user where username like ?";
        return template.queryForObject(sql, Integer.class,"%" + user.getUsername()+ "%");
    }

    public Integer findUserName(User user) {
        String sql = "select count(*) from sys_user where username=?";
        return template.queryForObject(sql, Integer.class, user.getUsername());
    }

    public Integer findEmail(User user) {
        String sql = "select count(*) from sys_user where email=?";
        return template.queryForObject(sql, Integer.class, user.getEmail());
    }

    public void register(User user) {
        String sql = "insert into sys_user (id,dept_id,username,password,email,qq_openid,wx_openid,real_name,age,phone,sex,description,register_time,login_time,pic,look,is_secret,create_time,create_by,del_flag)" +
                "values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,
                user.getDeptId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getQqOpenid(),
                user.getWxOpenid(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getSex(),
                user.getDescription(),
                user.getRegisterTime(),
                user.getLoginTime(),
                user.getPic(),
                user.getLook(),
                user.getIsSecret(),
                user.getCreateTime(),
                user.getCreateBy(),
                user.getDelFlag());
    }

    public void uploadPic(Integer id,String path){
        String sql="update sys_user set pic=? where id=?";
        template.update(sql,path,id);
    }

}
