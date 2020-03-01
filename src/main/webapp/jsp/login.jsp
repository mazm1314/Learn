<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>课程学习系统</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.min.css" rel="stylesheet">
    <link href="../css/animate.min.css" rel="stylesheet">
    <link href="../css/layout.css" rel="stylesheet">
    <link href="../css/login.min.css" rel="stylesheet">
    <link rel="Shortcut Icon" href="favicon.ico" />
</head>

<body class="signin">

<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>课程学习系统</h1>
                </div>
                <!--   <div class="m-b"></div>
                 <strong>如果没有账户，需要向管理员申请!</strong>-->
            </div>
        </div>

        <div class="col-sm-5" >
            <form method="post" action="<%=path %>/index/login"  id="signupForm">
                <h4 class="no-margins">登录&nbsp;|&nbsp;<a href="<%=path %>/index/toRegister">注册</a></h4>
                <p class="blank10">&nbsp;</p>
                <input type="text"     class="form-control uname"     placeholder="用户名"   name="username" />
                <input type="password" class="form-control pword"     placeholder="密码"     name="password" />
                <p class="blank20">&nbsp;</p>
                <button class="btn btn-primary btn-block" type="submit">登录</button>
            </form>
        </div>


    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2018 年度毕业设计 rev1
        </div>
    </div>
</div>


<script src="../js/jquery.min.js?v=2.1.4"></script>
<script src="../js/bootstrap.min.js?v=3.3.5"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript">
    $().ready(function() {

        //$("#commentForm").validate();
        var e = "<i class='fa fa-times-circle'></i>";
        //注册验证获取form表单的id
        $("#signupForm").validate({
            //规则
            rules: {
                username: {
                    //必须的
                    required: !0,
                    //限定文本内容的最短长度
                    minlength: 2
                },
                password: {
                    required: !0,
                    minlength: 5
                }
            },

            //留言
            messages: {
                username: {
                    required:  e + "请输入您的用户名",
                    minlength: e + "用户名必须两个字符以上"
                },
                password: {
                    required:  e + "请输入您的密码",
                    minlength: e + "密码必须5个字符以上"
                }
            }
        })
    });

</script>

</body>
</html>
