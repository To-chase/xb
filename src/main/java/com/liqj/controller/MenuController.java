package com.liqj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liqj.entity.Menu;
import com.liqj.servise.MenuServise;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liqj
 * @date 2020/3/19 16:46
 */
@WebServlet("/menu/*")
public class MenuController extends BaseController {
    private MenuServise menuServise=new MenuServise();
    protected void menu(HttpServletRequest request, HttpServletResponse response){
        List<Menu> list=menuServise.listAll();
        if(list==null|list.size()<=0){
            return;
        }
        List<Menu> parent=new ArrayList<>();
        List<Menu> child=new ArrayList<>();


      parent= list.stream().filter(n->{
            return n.getType()==0;
        }).collect(Collectors.toList());

        child= list.stream().filter(n->{
            return n.getType()==1;
        }).collect(Collectors.toList());

        try {
            Map<String,List<Menu>> map=new HashMap<>();
            map.put("parent",parent);
            map.put("child",child);
//            BaseController baseController=new BaseController();
//            baseController.writerObjToString(response,map);
            ObjectMapper om=new ObjectMapper();
            String jsonMap=om.writeValueAsString(map);
            response.getWriter().write(jsonMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
