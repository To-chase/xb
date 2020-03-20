<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${path}/static/css/style.css">
</head>
<body>
<%@include file="/incloud/top.jsp"%>
<div class="center">

    <%@include file="/incloud/menu.jsp"%>
    <div class="center-right">
        <div class="container">
            <div class="header">
                头像:
                <img src="/user/getPic?pic=${loginUser.pic}" alt="加载失败" style="width: 50px;height: 50px;" id="header-img">
                <input type="file" name="picFile" id="picFile" style="display: none">
            </div>
            <form action="">
                <input type="hidden" value="${user.deptId}" id="id">
                <div class="form-group">
                    <label for="realName">真实姓名:</label>
                    <input type="text" name="realName" id="realName" value="${user.realName}" class="form-control">
                </div>

                    <div class="form-group">
                        <label for="realName">所属部门:</label>
                       <select name="deptId" id="deptId">
                           <%--<option value="" <c:if test="id==${data[i].id}"></c:if> ></option>--%>
                       </select>
                    </div>

                <div class="form-group">
                    <label for="age">年龄:</label>
                    <input type="text" name="age" id="age" value="${user.age}" class="form-control">
                </div>

                <div class="form-group">
                   性别:
                    <input type="radio" name="sex" value="1" <c:if test="${user.sex==1}">checked</c:if>>男
                    <input type="radio" name="sex" value="0" <c:if test="${user.sex==0}">checked</c:if>>女
                </div>

                <div class="form-group">
                    <label for="description">个人描述:</label>
                    <textarea id="description" name="description" class="form-control" style="resize: none;">${user.description}</textarea>
                </div>

                <div class="form-group">
                    <label for="registerTime">注册时间:</label>
                    <input type="text" name="registerTime" id="registerTime" value="${user.registerTime}" class="form-control" disabled>
                </div>

                <div class="form-group">
                    <label for="loginTime">登录时间:</label>
                    <input type="text" name="loginTime" id="loginTime" value="${user.loginTime}" class="form-control" disabled>
                </div>

                <div class="form-group">
                    <label for="look">查看人数:</label>
                    <input type="text" name="look" id="look" value="${user.look}" class="form-control">
                </div>

                <div class="form-group">
                    是否私密:
                    <input type="checkbox" name="isSecret" id="isSecret" value="0"  <c:if test="${user.isSecret==0}">checked</c:if> >
                </div>
                <input type="submit" value="保存" class="btn btn-primary">

            </form>
        </div>
    </div>
</div>
<%--<%@include file="/incloud/foot.jsp"%>--%>

</body>
<script>
    $(function () {
        $("#header-img").click(function () {
            $("#picFile").click();
        });
        
        $("#picFile").change(function () {
            var formData=new FormData();
            formData.append("iconFile",document.getElementById("picFile").files[0]);

            $.ajax({
                url:"/user/updatePic",
                processData:false,//默认为true,对请求传递的参数(formData)不做编码处理
                contentType:false,
                type:"post",
                data:formData,
                async: true,
                success:function (res) {
                    if(res.status==200){
                        layer.msg('上传成功');

                        $("#picFile").val("");
                        // 替换成后台的url
                        $("#headerImg").attr("src", res.picUrl);
                        $("#header-img").attr("src", res.picUrl);
                    }else{
                        layer.msg(res.message);
                    }
                }
            });
        });

        $.ajax({
            url:"/dept/listAll",
            type:"post",
            data:{},
            dataType:"json",
            success:function (data) {
                var html="";
                var deptid=$("#id").val();
                for(var i=0;i<data.length;i++){
                    if(deptid==data[i].id){
                        html+='<option value="'+data[i].id+'" selected>'+data[i].name+'</option>';
                    }else {
                        html+='<option value="'+data[i].id+'">'+data[i].name+'</option>';
                    }

                }
                $("#deptId").append(html);
            }
        });
    });
</script>
</html>
