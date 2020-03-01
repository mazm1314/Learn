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
    <title>作业答题</title>
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
<input type="hidden" id="homeworkId" value="<c:out value='${homework.id}'/>"/>
<input type="hidden" id="homeworkType" value="<c:out value='${homework.homeworkType}'/>"/>
<input type="hidden" id="flag" value="<c:out value='${flag}'/>"/>
<input type="hidden" id="homeworkAndUserId" value="<c:out value='${homeworkAndUserId}'/>"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="location clearfix">
        <p class="pull-left"><i class="fa fa-map-marker"></i> 作业答题</p>
    </div>
    <!--location end-->

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins new_add_box">
                <div class="ibox-content clearfix">
                    <div class="col-sm-10 col-sm-offset-1 mt20">
                        <div class="form-group clearfix">
                            <label class="col-sm-2 control-label">作业答题：</label>
                            <div class="col-sm-6">
                                <!-- 选择题 -->
                                <c:if test="${homework.homeworkType=='choice'}">
                                    <label class="col-sm-2 control-label"><c:out value="${homework.title}"></c:out>：</label>
                                    <br/>
                                    <div class="col-sm-7 pt6">
                                        <c:forEach items="${contentList}" var="item" varStatus="index">
                                            <div class="radio radio-inline">
                                                <input type="radio" id="${item}_${index}" value="${item}" <c:if test="${answer==item}">checked</c:if> name="radioInline">
                                                <label for="${item}_${index}"> ${item}</label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                                <!-- 简答题 -->
                                <c:if test="${homework.homeworkType=='shortAnswer'}">
                                    <label class="col-sm-2 control-label"><c:out value="${homework.title}"></c:out>：</label>
                                    <br/>
                                    <div class="col-sm-7 pt6">
                                        <textarea id="shortAwswerContent" value="<c:out value="${answer}"></c:out>" class="form-control" rows="4" cols="5">
                                        <c:out value="${answer}"></c:out>
                                        </textarea>
                                    </div>
                                </c:if>
                                <!-- 判断题 -->
                                <c:if test="${homework.homeworkType=='judge'}">
                                    <label class="col-sm-2 control-label"><c:out value="${homework.title}"></c:out>：</label>
                                    <br/>
                                    <div class="col-sm-7 pt6">
                                        <div class="radio radio-inline">
                                            <input type="radio" id="flag_yes" <c:if test="${answer=='yes'}">checked</c:if> value="yes" name="radioInline">
                                            <label for="flag_yes"> 是</label>
                                        </div>
                                        <div class="radio radio-inline">
                                            <input type="radio" id="flag_no" <c:if test="${answer=='no'}">checked</c:if> value="no" name="radioInline">
                                            <label for="flag_no"> 否</label>
                                        </div>
                                    </div>
                                </c:if>

                                <label class="col-sm-2 control-label">老师留言：</label>
                                <br/>
                                <div class="col-sm-7 pt6">
                                        <textarea id="teacherComment"   <c:if test="${flag=='xs'}">disabled="disabled"</c:if> value="<c:out value="${teacherComment}"></c:out>" class="form-control" rows="4" cols="5">
                                        <c:out value="${teacherComment}"></c:out>
                                        </textarea>
                                </div>

                                <label class="col-sm-2 control-label">分数：</label>
                                <br/>
                                <div class="col-sm-7 pt6">
                                        <input id="score" <c:if test="${flag=='xs'}">disabled="disabled"</c:if> value="<c:out value="${score}"></c:out>" class="form-control">
                                        </input>
                                </div>


                            </div>
                        </div><!--form-group end-->
                    </div>

                    <div class="col-sm-10 col-sm-offset-1 mt40">
                        <label class="col-sm-2">&nbsp;</label>
                        <div class="col-sm-6">
                            <button class="btn btn-primary" type="button" onClick="answerHomework();">确定</button>
                        </div>
                    </div>



                </div>
            </div>
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
<script src="<%=path%>/js/plugins/layer/layer.js"></script>
<script type="text/javascript">

    $(function(){

    });


    function answerHomework(){
        var homeworkId = $("#homeworkId").val();
        var type = $("#homeworkType").val();
        var flag = $("#flag").val();
        var teacherComment = $("#teacherComment").val();
        var score = $("#score").val();
        if(flag=="ls"){
            var homeworkAndUserId = $("#homeworkAndUserId").val();
            debugger;
            $.ajax({
                url:"./checkHomework?homeworkAndUserId="+homeworkAndUserId+"&teacherComment="+teacherComment.trim()+"&score="+score,
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
            return;
        }

        if(score!=""){
            layer.msg("老师已经打分,不能修改!",{icon: 0, time: 2000});
            return;
        }

        var answer = "";
        if(type=="choice"){
            answer = $(":radio:checked").val();
        }else if(type=="judge"){
            answer = $(":radio:checked").val();
        }else if(type=="shortAnswer"){
            answer = $("#shortAwswerContent").val();
        }

        $.ajax({
            url:"./answerHomework?homeworkId="+homeworkId+"&answer="+answer,
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
