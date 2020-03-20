package com.liqj.controller;

import com.liqj.entity.Dept;
import com.liqj.entity.Page;
import com.liqj.servise.DeptServise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Liqj
 * @date 2020/3/19 23:53
 */
@WebServlet("/dept/*")
public class DeptController extends BaseController {
    DeptServise deptServise=new DeptServise();
    protected void listAll(HttpServletRequest request, HttpServletResponse response){
        List<Dept> list=deptServise.listAll();
        if(list.size()<=0||list==null){
            return;
        }
        writerObjToString(response,list);
    }

    protected void dept(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pageStr=request.getParameter("page");
            String name=request.getParameter("name");
            name=name==null?"":name;
            Dept dept=new Dept();
            dept.setName(name);
            Integer count=deptServise.deptCount(dept);
            Integer pageCurrent=pageStr==null?1:Integer.valueOf(pageStr);
            Page page=new Page();
            page.setCount(count);
            page.setPageCurrent(pageCurrent);

            List<Dept> list=deptServise.listDept(dept,page);
            request.setAttribute("list",list);
            request.setAttribute("page",page);
            request.setAttribute("department",dept);
            request.getRequestDispatcher("/html/user_dept.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void deptDel(HttpServletRequest request, HttpServletResponse response) {
        try {
            String deptId=request.getParameter("id");
            Integer id=deptId==null?null:Integer.valueOf(deptId);
            deptServise.deptDel(id);
            request.getRequestDispatcher("/dept/dept").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void deptAdd(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name=request.getParameter("name");
            Dept dept=new Dept();
            dept.setName(name);
            deptServise.deptAdd(dept);
            response.sendRedirect("/dept/dept");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void checkDeptName(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name=request.getParameter("name");
            Dept dept=new Dept();
            dept.setName(name);
            Integer num=deptServise.checkDeptName(dept);
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
