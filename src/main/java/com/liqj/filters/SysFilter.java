package com.liqj.filters;

import com.liqj.constants.SysConstant;
import com.liqj.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Liqj
 * @date 2020/3/17 18:13
 */
@WebFilter("/*")
public class SysFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Object obj = session.getAttribute(SysConstant.SESSION_LOGIN_USER);

        String url = request.getRequestURL().toString();
//        String url = urlStr.substring(urlStr.lastIndexOf("/") + 1);
//        System.out.println(url);
        //不许拦截
        if (url.endsWith("index.jsp") ||
            url.endsWith("register.jsp") ||
            url.endsWith("login")||
            url.endsWith("getCode")||
            url.endsWith("/register")||
            url.endsWith("/findUserName") ||
            url.endsWith("findEmail")
        ) {
            filterChain.doFilter(request, response);
            return;
        }
        if(obj==null){
            response.sendRedirect("/index.jsp");
            return;
        }
        request.setAttribute("loginUser",(User)obj);
        filterChain.doFilter(request, response);
    }
}
