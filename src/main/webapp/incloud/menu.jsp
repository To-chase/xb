<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/18
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${path}/static/js/jquery-3.4.1.js"></script>
    <link rel="stylesheet" href="${path}/static/css/style.css">
</head>
<body>
<div class="center-left" id="center-left">
    <%--系统管理:--%>
    <%--<ul class="menu">--%>
        <%--<li><a href="/user/list?page=1">用户管理</a></li>--%>
        <%--<li><a href="/user/dept?page=1">部门管理</a></li>--%>

    <%--</ul>--%>

</div>
</body>
<script>
    $(function () {
        $.ajax({
            url:"/menu/menu",
            type:"post",
            data:{},
            dataType:"json",
             success:function (data) {
                 var parent=data.parent;
                 var child=data.child;
                 console.log(parent);
                 console.log(child);
                 var html="";
                 for(var i=0;i<parent.length;i++){
                     html+=parent[i].name;
                     html+='<ul class="menu">';
                     for (var j=0;j<child.length;j++){
                         if(parent[i].id==child[j].pId){
                            html+='<li><a href="'+child[j].url+'">'+child[j].name+'</a></li>';
                         }
                     }
                     html+='</ul>';
                 }
                 $("#center-left").append(html);
             }
        });
    });
</script>
</html>
