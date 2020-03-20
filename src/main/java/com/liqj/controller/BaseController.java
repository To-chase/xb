package com.liqj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liqj.constants.SysConstant;
import com.liqj.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Liqj
 * @date 2020/3/18 9:37
 */
public class BaseController extends HttpServlet {
    HttpSession session=null;
    public User loginUser=null;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session=request.getSession();
        loginUser=(User)session.getAttribute(SysConstant.SESSION_LOGIN_USER);
        String uri=request.getRequestURI();
        uri=uri.substring(uri.lastIndexOf("/")+1);
        //this代表谁调用就代表谁
        Class baseClass=this.getClass();
        try {
           Method method= baseClass.getDeclaredMethod(uri,HttpServletRequest.class,HttpServletResponse.class);
           method.setAccessible(true);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writerObjToString(HttpServletResponse response, Object obj) {
        try {
            ObjectMapper om=new ObjectMapper();
            String jsonStr=om.writeValueAsString(obj);

            response.setContentType("application/json;charset=utf8");
            response.getWriter().write(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
