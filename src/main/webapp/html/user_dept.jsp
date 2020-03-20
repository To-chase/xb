<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/19
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/incloud/top.jsp"%>
<div class="center">

    <%@include file="/incloud/menu.jsp"%>
    <div class="center-right">
        <form action="/dept/dept?page=1" method="post">
           部门名:<input type="text" name="name" value="${department.name}">
            <input type="submit" value="查询">
        </form>
        <%--<a href="/html/dept_add.jsp" class="btn btn-success">新增部门</a>--%>
            <form action="/dept/deptAdd" method="post">
                部门名:<input type="text" name="name" value="" id="deptAdd"><span id="spanName"></span>
                <input type="submit" value="添加部门" id="add">
            </form>
        <table class="table table-bordered">
            <tr>
                <td>编号</td>
                <td>部门名称</td>
                <td>部门人数</td>
                <td>操作</td>
            </tr>

            <c:forEach items="${list}" var="dept" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${dept.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${dept.count!=null}">${dept.count}</c:when>
                            <c:otherwise>0</c:otherwise>
                        </c:choose>

                    </td>
                    <td>
                        <button type="button" class="btn btn-danger" id="dept_del" onclick="deptDel(${dept.id})">删除</button>
                        <a href="" class="btn btn-info">修改</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="page">
            <a href="/dept/dept?page=1&name=${department.name}">首页</a>
            <a href="/dept/dept?page=${page.pageCurrent-1<=0?1:page.pageCurrent-1}&name=${department.name}">上一页</a>
            <a href="/dept/dept?page=${page.pageCurrent+1>=page.pageTotal?page.pageTotal:page.pageCurrent+1}&name=${department.name}">下一页</a>
            <a href="/dept/dept?page=${page.pageTotal}&name=${department.name}">尾页</a>
            <span>总页数:${page.pageTotal}</span>,<span>当前第${page.pageCurrent}页</span>
        </div>
    </div>
</div>
<%--<%@include file="/incloud/foot.jsp"%>--%>
</body>
<script>
    function deptDel(deptId) {
        var flag=confirm("是否删除");
        if(flag==false){
            return;
        }
        window.location.href='/dept/deptDel?id='+deptId;

    }

    //异步检查部门名是否重复
    $(function () {
        $("#deptAdd").blur(function () {
            var name=$(this).val();
            if(name===""||name===null){
               $("#spanName").text("部门名不能为空");
               $("#add").attr("disabled",true);
                return;
            }

            $.ajax({
                url:"/dept/checkDeptName",
                type:"get",
                data:{name:name},
                dataType:"text",
                success:function (data) {
                    if(data==200){
                        //用户名可以使用
                        $("#spanName").text("");
                        $("#add").attr("disabled",false);
                    }else {
                        //用户名不可以使用
                        $("#spanName").text("该部门名重复");
                    }
                }
            });
        });
    });
</script>
</html>
