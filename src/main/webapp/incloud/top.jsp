<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="top">
    <div class="header">
        <img src="/user/getPic?pic=${loginUser.pic}" alt="加载失败" style="width: 50px;height: 50px;" id="headerImg">
        ${loginUser.username}
    </div>
</div>
</body>
<script>
    $(function () {
        $("#headerImg").click(function () {
            window.location.href="/user/toUpdate";
        });
    });
</script>
</html>
