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
        <form action="/user/list?page=1" method="post">
            用户名:<input type="text" name="username" value="${user.username}">
            <input type="submit" value="查询">
        </form>
        <a href="/html/user_list.jsp" class="btn btn-success">新增用户</a>
        <table class="table table-bordered">
            <tr>
                <td><input type="checkbox" id="checkAll"></td>
                <td>编号</td>
                <td>id</td>
                <td>用户名</td>
                <td>真实名字</td>
                <td>年龄</td>
                <td>电话号码</td>
                <td>性别</td>
                <td>创建时间</td>
                <td>创建人</td>
                <td>操作</td>
            </tr>

            <c:forEach items="${list}" varStatus="status" var="user">
                <tr>
                    <td><input type="checkbox" name="every_box"></td>
                    <td>${status.index+1}</td>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.realName}</td>
                    <td>${user.age}</td>
                    <td>${user.phone}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.sex==1}">男</c:when>
                            <c:when test="${user.sex==0}">女</c:when>
                        </c:choose>
                    </td>
                    <td>${user.createTime}</td>
                    <td>${user.createName}</td>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
        <div class="page">
            <a href="/user/list?page=1&username=${user.username}">首页</a>
            <a href="/user/list?page=${page.pageCurrent-1<=0?1:page.pageCurrent-1}&username=${user.username}">上一页</a>
            <a href="/user/list?page=${page.pageCurrent+1>=page.pageTotal?page.pageTotal:page.pageCurrent+1}&username=${user.username}">下一页</a>
            <a href="/user/list?page=${page.pageTotal}&username=${user.username}">尾页</a>
            <span>总页数:${page.pageTotal}</span>,<span>当前第${page.pageCurrent}页</span>
        </div>
    </div>
</div>
<%--<%@include file="/incloud/foot.jsp"%>--%>
</body>
</html>
