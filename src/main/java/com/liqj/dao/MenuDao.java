package com.liqj.dao;

import com.liqj.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author Liqj
 * @date 2020/3/19 16:45
 */
public class MenuDao extends BaseDao {
    public List<Menu> listAll(){
        String sql="select id,p_id,name,url,perms,type,order_by from sys_menu";
        return template.query(sql,new BeanPropertyRowMapper<>(Menu.class));
    }
}
