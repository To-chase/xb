<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
</style>
<body>
<div class="container">
    <div class="row">
        <form id="myform" action="/register/register" method="post">
            <div class="form-group container">
                <label for="username">用户名:</label>
                <input type="text" name="username" id="username"><span id="nameSpan"></span>
            </div>

            <div class="form-group container">
                <label for="password">密码:</label>
                <input type="password" name="password" id="password"><span id="pwdSpan"></span>
            </div>

            <div class="form-group container">
                <label for="email">邮箱:</label>
                <input type="text" name="email" id="email"><span id="emailSpan"></span>
            </div>

            <div class="form-group container">
                <label for="rePassword">确认密码:</label>
                <input type="password" name="rePassword" id="rePassword"><span id="repweSpan"></span>
            </div>

            <div class="form-group">
                <input type="submit" name="btn" id="btn-register" value="提交" class="btn btn-success">
            </div>

            <input type="hidden" id="name-flag" value="0">
            <input type="hidden" id="password-flag" value="0">
            <input type="hidden" id="email-flag" value="0">
        </form>
    </div>
</div>
</body>
<script>
    $(function () {


        $("#username").blur(function () {
            var username=$(this).val();
            if(username==null||username==""){
                $("#nameSpan").text("用户名不能为空");
                return;
            }
            $.ajax({
                url:"/register/findUserName",
                type:"post",
                data:{username:username},
                dataType:"text",
                success:function (data) {
                    if(data==200){
                        //用户名可以使用
                        $("#name-flag").val("1");
                        $("#nameSpan").text("");
                    }else {
                        //用户名不可以使用
                        $("#name-flag").val("0");
                        $("#nameSpan").text("该用户名重复");
                    }
                }
            });
        });

        /*--检验邮箱--*/
        $("#email").blur(function () {
            var email=$(this).val();
            if(email==null|email==""){
                $("#emailSpan").text("邮箱不能为空");
                return;
            }

            $.ajax({
                url:"/register/findEmail",
                type:"post",
                data:{email:email},
                dataType:"text",
                success:function (data) {
                    if(data==200){
                        //邮箱可以使用
                        $("#email-flag").val("1");
                        $("#emailSpan").text("");
                    }else {
                        //邮箱不可以使用
                        $("#email-flag").val("0");
                        $("#emailSpan").text("该邮箱已注册");
                    }
                }
            });
        });

        /*--检验两次密码--*/
        $("#rePassword").blur(function () {
            var password=$("#password").val();
            var repassword=$(this).val();
            if(password===repassword){
                $("#password-flag").val("1");
                $("#repweSpan").text("");
            }else {
                $("#password-flag").val("0");
                $("#repweSpan").text("两次密码不正确");
            }
        });
    });
</script>
</html>
