package com.liqj.servise;

import com.liqj.dao.MenuDao;
import com.liqj.entity.Menu;

import java.util.List;

/**
 * @author Liqj
 * @date 2020/3/19 16:45
 */
public class MenuServise {

    private MenuDao menuDao=new MenuDao();
    public List<Menu> listAll(){
       return menuDao.listAll();
    }
}
