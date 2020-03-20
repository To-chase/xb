package com.liqj.controller;

import com.liqj.entity.Dept;
import com.liqj.entity.Page;
import com.liqj.entity.User;
import com.liqj.servise.UserServise;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Liqj
 * @date 2020/3/18 18:44
 */
@WebServlet("/user/*")
public class UserController extends BaseController {
    private UserServise userServise = new UserServise();
    private static final String URL="D:\\upload\\header\\";

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page page = new Page();
        String pageStr = request.getParameter("page");
        String name = request.getParameter("username");
        name = name == null ? "" : name;
        User user=new User();
        user.setUsername(name);
        //当前页
        Integer pageCurrent = pageStr == null ? 1 : Integer.valueOf(pageStr);
        Integer count = userServise.count(user);
        page.setPageCurrent(pageCurrent);
        page.setCount(count);
        List<User> list = userServise.listAll(user,page);
        //总记录数

        if (list == null || list.size() <= 0) {
            return;
        }
        request.setAttribute("page", page);
        request.setAttribute("user",user);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/html/user_list.jsp").forward(request, response);
    }


    /**
     * @author Liqj
     * @date 2020/3/19
     * @description 加载用户头像
     */
    protected void getPic(HttpServletRequest request, HttpServletResponse response){
        String pic=request.getParameter("pic");
        String realPath=URL+pic;
        File file=new File(realPath);
       if(!file.exists()){
            return;
        }

        try {
            BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
            OutputStream os=response.getOutputStream();
            byte[] b=new byte[1024];
            int len=0;
            while((len=bis.read(b))!=-1){
                os.write(b,0,b.length);
            }
            os.flush();
            os.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePic(HttpServletRequest request, HttpServletResponse response){
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload sf=new ServletFileUpload(factory);

        sf.setFileSizeMax(1024*1024*2);

        try {
            PrintWriter out=response.getWriter();

            List<FileItem> items=sf.parseRequest(request);
            String fileName="";

            for(FileItem fileItem:items){
                if(!fileItem.isFormField()){
                    String name=fileItem.getName();

                    //获取文件后缀
                    String last=name.substring(name.lastIndexOf("."));

                    //生成新文件名
                    fileName= UUID.randomUUID().toString()+last;

                    String realPath=URL+fileName;
                    File file=new File(realPath);
                    if(!file.exists()){
                        //关键:保存图片到服务器
                        fileItem.write(file);
                    }


                    userServise.uploadPic(loginUser.getId(),fileName);

                    loginUser.setPic(fileName);

                    Map dataMap = new HashMap();
                    dataMap.put("status", 200);
                    dataMap.put("picUrl", fileName);

                    writerObjToString(response, dataMap);
                }
            }
        } catch (Exception e) {
            Map dataMap = new HashMap();
            dataMap.put("status", 200);
            dataMap.put("message", "图片上传失败");

            writerObjToString(response, dataMap);
            e.printStackTrace();
        }
    }

    /**
     * @author Liqj
     * @date 2020/3/19
     * @description 个人信息编辑，信息回显
     */
    protected void toUpdate(HttpServletRequest request, HttpServletResponse response){
        User user=userServise.selectUserById(loginUser.getId());
        if(user==null){
            return;
        }
        request.setAttribute("user",user);
        try {
            request.getRequestDispatcher("/html/user_info.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

