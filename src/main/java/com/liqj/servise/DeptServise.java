package com.liqj.servise;

import com.liqj.dao.DeptDao;
import com.liqj.entity.Dept;
import com.liqj.entity.Page;
import com.liqj.utils.DateUtil;

import java.util.List;

/**
 * @author Liqj
 * @date 2020/3/19 23:53
 */
public class DeptServise {
    DeptDao deptDao=new DeptDao();
    public List<Dept> listAll(){
        return deptDao.listAll();
    }

    public List<Dept> listDept(Dept dept, Page page){
        List<Dept> list= deptDao.listDept(dept,page);
        if(list==null||list.size()<=0){
            return null;
        }
        return  list;
    }

    public Integer deptCount(Dept dept) {
        return deptDao.deptCount(dept);
    }

    public void deptDel(Integer id){
        if(id==null){
            return;
        }
        deptDao.deptDel(id);
    }

    public Integer checkDeptName(Dept dept){
        return deptDao.checkDeptName(dept);
    }

    public void deptAdd(Dept dept){
        dept.setCreateTime(DateUtil.dateStr());
        dept.setCreateBy(null);
        deptDao.deptAdd(dept);
    }
}
