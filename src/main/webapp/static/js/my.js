$(function () {
    $("#name").blur(function () {
        var name=$(this).val();
        $.ajax({
            url:"/user/checkName",
            type:"post",
            data:{name:name},
            dateType:"text",
            success:function (data) {
                if(data==1){
                    // alert("该用户名重复");
                    $("#nameSpan").text("该用户名重复");
                    $("#name").val("");
                }else if (data==0) {
                    // alert("该用户名可以使用");
                    $("#nameSpan").text("该用户名可以使用");
                }else {
                    $("#nameSpan").text("用户名不能为空");
                }
            }
        })
    });
});