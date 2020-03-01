<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>学生子系统</title>
    <link href="<%=path %>/css/bootstrap.min.css"    rel="stylesheet">
    <link href="<%=path %>/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path %>/css/animate.min.css"      rel="stylesheet">
    <link href="<%=path %>/css/layout.css"           rel="stylesheet">
    <link href="<%=path %>/favicon.ico"              rel="Shortcut Icon"  />
    <link href="<%=path %>/css/plugins/dataTables/jquery.dataTables.css"     rel="stylesheet">
    <link href="<%=path %>/css/plugins/datapicker/jquery.datetimepicker.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">

<div id="wrapper">
    <input type="hidden" id="userName" value="${userName}">
    <input type="hidden" id="userId" value="${userId}">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i> </div>
        <div id="leftMax" class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <h3 class="fff profile-element"><strong style="font-size:20px; color:#FFAF00">课程学习系统</strong></h3>
                    <div class="logo-element" style="color:#FFAF00">学生 </div>
                </li>

                <li id="meeting" class="active">
                    <a href="#"> <i class="fa fa-building-o"></i>
                        <span class="nav-label">学生首页</span> <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li> <a class="J_menuItem" href="<%=path %>/index/toWelcome" data-index="0" target="iframe0">学生首页</a> </li>
                    </ul>
                </li>

                <li id="client"> <a href="#"> <i class="fa fa-users"></i> <span class="nav-label">文件管理</span> <span class="fa arrow"></span> </a>
                    <ul class="nav nav-second-level">
                        <li> <a class="J_menuItem" href="<%=path %>/index/toFileMgr" data-index="0" target="iframe0">文件管理</a> </li>
                    </ul>
                </li>

                <li id="meetings">
                    <a href="#"> <i class="fa fa-sitemap"></i>
                        <span class="nav-label">课程交流中心</span> <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem"
                               href="<%=path%>/comment/toCommentMgr.do" data-index="0"
                               target="iframe0">留言管理</a></li>
                        <li> <a class="J_menuItem" href="<%=path %>/homework/toHomeworkMgr.do" data-index="0" target="iframe0">作业管理</a> </li>
                        <!--
                        <li> <a class="J_menuItem" href="<%=path %>/exam/toExamPage" data-index="0" target="iframe0">考试管理</a> </li>
                        -->
                    </ul>
                </li>
            <!--
                <li id="system"><a href="#"> <i class="fa fa-sitemap"></i> <span
                        class="nav-label">系统管理中心</span> <span class="fa arrow"></span>
                </a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=path%>/fileUpload.jsp"
                               data-index="0" target="iframe0">系统帮助</a></li>
                    </ul></li>
-->
            </ul>
        </div>
    </nav>
    <!-- style="display:none" 隐藏导航 -->
    <!--左侧导航结束-->

    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a> </div>
                <ul class="nav navbar-main-links navbar-left ml20">
                    <li class="active"> <a  class="J_menuItem" data-index="0">学生子系统</a> </li>
                </ul>
                <ul class="nav navbar-top-links navbar-right">
                    <li> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span class="text-muted text-xs block"><i class="fa fa-user"></i>${user.userinfo_name}<b class="caret"></b></span> </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="<%=path %>/index/toPersonInfo.do?userId=<c:out value='${userId}'/>" target="iframe0">个人资料</a> </li>
                            <li><a class="J_menuItem" href="<%=path %>/jsp/changeComPassword.jsp" target="iframe0">修改密码</a> </li>
                        </ul>
                    </li>

                    <li> <a href="<%=path %>/index/logout.do" class="J_menuItem" data-index="0"><i class="fa fa-sign-out"></i> 退出</a> </li>
                </ul>
            </nav>
        </div>
        <!--main页面-->
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="<%=path %>/index/toWelcome" frameborder="0"  seamless></iframe>
        </div>
    </div>

    <!--右侧部分结束-->

    <script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=path %>/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="<%=path %>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=path %>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
    <script src="<%=path %>/js/hplus.min.js?v=4.0.0"></script>
    <script src="<%=path %>/js/plugins/pace/pace.min.js"></script>

    <script>
        $(document).ready(function() {

            $("#inlineRadio4").click(function () {
                $("#juese").hide();
            });

            $("#inlineRadio5").click(function () {
                $("#juese").show();
            });

        });
    </script>
</body>
</html>