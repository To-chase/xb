package com.liqj.controller;

import com.liqj.entity.User;
import com.liqj.servise.UserServise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Liqj
 * @date 2020/3/18 13:55
 */
@WebServlet("/register/*")
public class RegisterController extends BaseController {
    UserServise userServise=new UserServise();
    protected void register(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String name=request.getParameter("username");
            String password=request.getParameter("password");
            String email=request.getParameter("email");
            User user=new User();
            user.setUsername(name);
            user.setPassword(password);
            user.setEmail(email);
           userServise.register(user);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    protected void findUserName(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String name=request.getParameter("username");
            User user=new User();
            user.setUsername(name);
            Integer num=userServise.findUserName(user);
            PrintWriter out=response.getWriter();
            if(num<=0){
                out.print("200");
                return;
            }else {
                out.print("500");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void findEmail(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String email=request.getParameter("email");
            User user=new User();
            user.setEmail(email);
            Integer num=userServise.findEmail(user);
            PrintWriter out=response.getWriter();
            if(num<=0){
                out.print("200");
                return;
            }else {
                out.print("500");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
