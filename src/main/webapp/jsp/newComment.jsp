<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>新建留言</title>
    <link href="<%=path %>/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="<%=path %>/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/layout.css?v=1.0.0" rel="stylesheet">
    <link href="<%=path %>/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="<%=path %>/css/plugins/dataTables/jquery.dataTables.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/plugins/markdown/bootstrap-markdown.min.css" />
    <link href="<%=path %>/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="location clearfix">
        <p class="pull-left"><i class="fa fa-map-marker"></i> 新建留言</p>

    </div>
    <!--location end-->

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins new_add_box">
                <div class="ibox-content clearfix">
                    <div class="col-sm-10 col-sm-offset-1 mt20">
                        <div class="form-group clearfix">
                            <label class="col-sm-2 control-label"><em class="must">*</em>老师列表：</label>
                            <div class="col-sm-6">
                                <select id="ls_select" name="type" class="form-control " gid="36" gindex="0" data-code="cusomerType" data-parent-code="">
                                    <option value=""></option>
                                    <option value="1" data-parent-code="" ctrl-hides="" ctrl-shows="">
                                        ls001</option>
                                    <option value="3" data-parent-code="" ctrl-hides="" ctrl-shows="">
                                        ls002</option>
                                    <option value="4" data-parent-code="" ctrl-hides="" ctrl-shows="">
                                        ls003</option>
                                </select>
                            </div>
                        </div><!--form-group end-->

                        <div class="form-group clearfix">
                            <label class="col-sm-2 control-label"><em class="must">*</em>课程列表：</label>
                            <div class="col-sm-6">
                                <select id="kc_select" name="type" class="form-control " gid="36" gindex="0" data-code="cusomerType" data-parent-code="">
                                    <option value=""></option>
                                    <option value="英语" data-parent-code="" ctrl-hides="" ctrl-shows="">
                                        英语</option>
                                    <option value="语文" data-parent-code="" ctrl-hides="" ctrl-shows="">
                                        语文</option>
                                    <option value="数学" data-parent-code="" ctrl-hides="" ctrl-shows="">
                                        数学</option>
                                </select>
                            </div>
                        </div><!--form-group end-->


                        <div class="form-group clearfix">
                            <label class="col-sm-2 control-label">留言：</label>
                            <div class="col-sm-6">
                                <input type="text" id="commentContent" class="form-control" name="password"   >
                            </div>
                        </div><!--form-group end-->


                    </div>

                    <div class="col-sm-10 col-sm-offset-1 mt40">
                        <label class="col-sm-2">&nbsp;</label>
                        <div class="col-sm-6">
                            <button class="btn btn-primary" type="button" onClick="createNewComment();">确定</button>
                            <%--<input class="btn btn-primary" type="button" onclick="createComment()">确定</input>--%>
                            <!--
                            <button class="btn btn-white" type="submit" onClick="">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
                            -->
                        </div>
                    </div>



                </div>
            </div>
            <!--ibox end-->



        </div>
        <!--col-sm-12 end-->

    </div>
    <!--row end-->
</div>
<!--wrapper end-->


<script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path %>/js/bootstrap.min.js?v=3.3.5"></script>
<script src="<%=path %>/js/plugins/iCheck/icheck.min.js"></script>
<script src="<%=path %>/js/hplus.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/plugins/markdown/markdown.js"></script>
<script type="text/javascript" src="<%=path %>/js/plugins/markdown/to-markdown.js"></script>
<script type="text/javascript" src="<%=path %>/js/plugins/markdown/bootstrap-markdown.js"></script>
<script type="text/javascript" src="<%=path %>/js/plugins/markdown/bootstrap-markdown.zh.js"></script>
<script type="text/javascript">
    
    function createNewComment(){
        var lsId = $('#ls_select').val();
        var commentContent = $("#commentContent").val();
        var kcName = $("#kc_select").val();

        if(lsId==""){
            alert("请选择老师！");
            return;
        }

        if(kcName==""){
            alert("请选择课程");
            return;
        }

        if(commentContent==""){
            alert("请填写留言内容！");
            return;
        }
        $.ajax({
            url:"./createComment?teacherId="+lsId+"&commentContent="+commentContent+"&kcName="+kcName,
            async: false,
            type:"get",
            dataType:"text",
            success:function(responseText){
                window.close();
                window.opener.location.reload();
            },
            error:function(){
                layer.msg("创建失败!",{icon: 0, time: 2000});
            }
        });
    }
    
    
</script>
</body>

</html>
