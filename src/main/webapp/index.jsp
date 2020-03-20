
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap.css">
    <script src="${path}/static/js/jquery-3.4.1.js"></script>
    <script src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<style>
    label{
        width: 80px;
        text-align: center;
    }
    span{
        color: red;
    }
    .form-group{
        width: 40%;
    }
    .container{
        margin-top: 10px;
    }
</style>
<body>
<div class="container">
    <div class="row">
        <form  action="/login/login" method="post">
            <div class="form-group container">
                <label for="username">用户名:</label>
                <input type="text" name="username" id="username"><span id="nameSpan"></span>
            </div>

            <div class="form-group container">
                <label for="password">密码:</label>
                <input type="password" name="password" id="password"><span id="pwdSpan"></span>
            </div>

            <div class="form-group container">
                <label for="password">验证码:</label>
                <input type="text" name="code" placeholder="验证码">
                <img src="/login/getCode" alt="加载失败" onclick="changeCode()" id="img">
            </div>


            <div class="form-group container" style="text-align: left">
                <input type="submit" name="btn"  value="登录" class="btn btn-success">
               <a href="/register.jsp" class="btn btn-success">注册</a>
            </div>

        </form>
    </div>
</div>



</body>
<script>
    function changeCode() {
        //document.getElementById("img").src + "?nocache=" + new Date().getTime();
       document.getElementById("img").src=document.getElementById("img").src + "?nocache=" + new Date().getTime();
    }
</script>
</html>
