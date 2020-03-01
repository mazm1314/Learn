
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
    <title>修改个人密码</title>
    <link rel="stylesheet" href="../resources/app/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/vendor/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../resources/vendor/animo/animate+animo.css">
    <link rel="stylesheet" href="../resources/vendor/csspinner/csspinner.min.css">
    <link rel="stylesheet" href="../resources/vendor/slider/css/slider.css">
    <link rel="stylesheet" href="../resources/vendor/chosen/chosen.min.css">
    <link rel="stylesheet" href="../resources/vendor/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <!-- Codemirror -->
    <link rel="stylesheet" href="../resources/vendor/codemirror/lib/codemirror.css">
    <!-- Bootstrap tags-->
    <link rel="stylesheet" href="../resources/vendor/tagsinput/bootstrap-tagsinput.css">
    <link rel="stylesheet" href="../resources/app/css/app.css">
    <!-- Modernizr JS Script-->
    <script src="../resources/vendor/modernizr/modernizr.js"
            type="application/javascript"></script>
    <!-- FastClick for mobiles-->
    <script src="../resources/vendor/fastclick/fastclick.js"
            type="application/javascript"></script>
    <script src="../resources/vendor/jquery/jquery.min.js" type="application/javascript"></script>
</head>
<body>
    <form method="post">
        <tr>
            <td>旧密码</td>
            <td><input id="oldpass" type="text" name="oldpass"/></td>
        </tr>
        <tr>
            <td>新密码</td>
            <td><input id="newpass" type="password" name="newpass"/></td>
        </tr>
        <tr>
            <button type="button" onclick="modifyPass()" value="提交">提交</button>
        </tr>
    </form>
</body>

<script type="text/javascript">
    function modifyPass(){
        var userId =  $(window.parent.document.getElementById("userId")).val();
        var oldpass = $("#oldpass").val();
        var newpass = $("#newpass").val();
        console.log("userId="+userId+",newpass="+newpass);
        $.post("../index/modifyPass",{userId:userId,oldpass:oldpass,newpass:newpass},function(result){
            if(result=="fail"){
                alert("密码修改失败！");
            }else{
                alert("密码修改成功！");
            }
            $("#oldpass").val("");
            $("#newpass").val("");
        });
    }
</script>

</html>

