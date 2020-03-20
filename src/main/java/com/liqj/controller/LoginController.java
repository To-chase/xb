package com.liqj.controller;

import com.liqj.constants.SysConstant;
import com.liqj.entity.User;
import com.liqj.servise.LoginServise;
import com.liqj.utils.ImgCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Liqj
 * @date 2020/3/17 18:12
 */
@WebServlet("/login/*")
public class LoginController extends BaseController {
    private LoginServise servise=new LoginServise();
    /**
     * @author Liqj
     * @date 2020/3/18
     * @params [request, response]
     * @return void
     * @description 登录验证
     */
    protected void login(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String name=request.getParameter("username");
            String password=request.getParameter("password");
            String code=request.getParameter("code");
            HttpSession session=request.getSession();
            if(name==null||name==""||password==null||password==""){
                response.sendRedirect("/index.jsp");
                return;
            }
//            Object codeObj=session.getAttribute(SysConstant.SESSION_LOGIN_CODE);
//            if(code==null){
//                response.sendRedirect("/index.jsp");
//                return;
//            }
//            if(!(codeObj.toString().equals(code))){
//                response.sendRedirect("/index.jsp");
//                return;
//            }
            User user=new User();
            user.setUsername(name);
            user.setPassword(password);
            User result=servise.checkLogin(user);
            if(result==null){
                response.sendRedirect("/index.jsp");
                return;
            }
            session.setAttribute(SysConstant.SESSION_LOGIN_USER,result);
            request.setAttribute("loginUser",result);
            request.setAttribute("result",result);
            request.getRequestDispatcher("/html/home.jsp").forward(request,response);
        } catch (Exception e) {
//            e.printStackTrace();
            try {
                response.sendRedirect("/index.jsp");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * @author Liqj
     * @date 2020/3/18
     * @params [request, response]
     * @return void
     * @description 获取验证码
     */
    protected void getCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgCodeUtil imgCodeUtil=new ImgCodeUtil();
        BufferedImage image=imgCodeUtil.getImage();
        String code=imgCodeUtil.getText();

        HttpSession session=request.getSession();
        session.setAttribute(SysConstant.SESSION_LOGIN_CODE,code);


        // 禁止图像缓存
        //　no-cache指示请求或响应消息不能缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        //设置页面在浏览器的缓存里保存的时间
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //获取把图像下画出去的响应流
        ServletOutputStream sos=response.getOutputStream();

        ImageIO.write(image,"jpeg",sos);
        sos.flush();
        sos.close();
    }
}
