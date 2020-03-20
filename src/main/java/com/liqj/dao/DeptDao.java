package com.liqj.dao;

import com.liqj.entity.Dept;
import com.liqj.entity.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author Liqj
 * @date 2020/3/19 23:53
 */
public class DeptDao extends BaseDao {
    public List<Dept> listAll(){
        String sql="select * from sys_dept";
        return template.query(sql,new BeanPropertyRowMapper<>(Dept.class));
    }

    public List<Dept> listDept(Dept dept, Page page){
        String sql="SELECT " +
                " d.id,d.name,d.create_time,d.create_by,d.del_flag, " +
                " n.count  " +
                "FROM " +
                " sys_dept d " +
                " LEFT JOIN ( SELECT dept_id, count(*) count FROM sys_user GROUP BY dept_id ) n ON n.dept_id = d.id "+
                "where d.name like ? and del_flag=0 order by d.create_time desc "+
                "limit ?,?  ";
        return template.query(sql,new BeanPropertyRowMapper<>(Dept.class),"%"+dept.getName()+"%",(page.getPageCurrent() - 1) * page.getPageSize(),page.getPageSize());
    }

    //部门记录数
    public Integer deptCount(Dept dept) {
        String sql = "select count(*) from sys_dept where name like ?";
        return template.queryForObject(sql, Integer.class,"%"+dept.getName()+"%");
    }

    public void deptDel(Integer id){
        String sql="update sys_dept set del_flag=1 where id=? and del_flag=0 ";
        template.update(sql,id);
    }

    public Integer checkDeptName(Dept dept){
        String sql="select count(*) from sys_dept where name=?";
        return template.queryForObject(sql,Integer.class,dept.getName());
    }

    public void deptAdd(Dept dept){
        String sql="insert into sys_dept (id,name,create_time,create_by) values (null,?,?,?)";
        template.update(sql,dept.getName(),dept.getCreateTime(),dept.getCreateBy());
    }
}
