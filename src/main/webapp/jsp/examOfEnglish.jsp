<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
         <%--pageEncoding="UTF-8"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<%--%>
    <%--String path = request.getContextPath();--%>
    <%--String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
<%--%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>

<%--<html>--%>
<%--<head>--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<meta name="renderer" content="webkit">--%>
    <%--<meta http-equiv="Cache-Control" content="no-siteapp" />--%>
    <%--<title>英语试题</title>--%>
    <%--<link href="<%=path %>/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">--%>
    <%--<link href="<%=path %>/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">--%>
    <%--<link href="<%=path %>/css/plugins/iCheck/custom.css" rel="stylesheet">--%>
    <%--<link href="<%=path %>/css/animate.min.css" rel="stylesheet">--%>
    <%--<link href="<%=path %>/css/layout.css?v=1.0.0" rel="stylesheet">--%>
    <%--<link href="<%=path %>/css/plugins/iCheck/custom.css" rel="stylesheet">--%>
    <%--<link href="<%=path %>/css/plugins/dataTables/jquery.dataTables.css" rel="stylesheet">--%>
    <%--<link rel="stylesheet" type="text/css" href="<%=path %>/css/plugins/markdown/bootstrap-markdown.min.css" />--%>
    <%--<link href="<%=path %>/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">--%>

<%--</head>--%>

<%--<body class="gray-bg">--%>
<%--<input type="hidden" id="homeworkId" value="<c:out value='${homework.id}'/>"/>--%>
<%--<input type="hidden" id="homeworkType" value="<c:out value='${homework.homeworkType}'/>"/>--%>
<%--<div class="wrapper wrapper-content animated fadeInRight">--%>
    <%--<div class="location clearfix">--%>
        <%--<p class="pull-left"><i class="fa fa-map-marker"></i> 英语考试</p>--%>
    <%--</div>--%>
    <%--<!--location end-->--%>

    <%--<div class="row">--%>
        <%--<div class="col-sm-12">--%>
            <%--<div class="ibox float-e-margins new_add_box">--%>
                <%--<div class="ibox-content clearfix">--%>
                    <%--<div class="col-sm-10 col-sm-offset-1 mt20">--%>
                        <%--<div class="form-group clearfix">--%>
                            <%--<div class="col-sm-6">--%>
                                <%--<label class="col-sm-2 control-label">1.That woman has a bag in her right hand. What's in her ______ hand?</label>--%>
                                <%--<div class="col-sm-7 pt6">--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="1_A"  name="radioInline">--%>
                                        <%--<label for="1_A"> A. another</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="1_B" name="radioInline">--%>
                                        <%--<label for="1_B"> B. other</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="1_C"  name="radioInline">--%>
                                        <%--<label for="1_C"> C. one</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="1_D" name="radioInline">--%>
                                        <%--<label for="1_D"> D. the other</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<label class="col-sm-2 control-label">2. We don't allow ______ in this room.</label>--%>
                                <%--<div class="col-sm-7 pt6">--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="2_A"  name="radioInline">--%>
                                        <%--<label for="2_A"> A. smoking</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="2_B" name="radioInline">--%>
                                        <%--<label for="2_B"> B. to smoke</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="2_C"  name="radioInline">--%>
                                        <%--<label for="2_C"> C. people smoking</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="2_D" name="radioInline">--%>
                                        <%--<label for="2_D"> D. people to smoking</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>


                                <%--<label class="col-sm-2 control-label">3.I haven't got a chair ______. Will you make room for me?</label>--%>
                                <%--<div class="col-sm-7 pt6">--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="3_A"  name="radioInline">--%>
                                        <%--<label for="3_A"> A. to sit</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="3_B" name="radioInline">--%>
                                        <%--<label for="3_B"> B. to sit in</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="3_C"  name="radioInline">--%>
                                        <%--<label for="3_C"> C. for sitting</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="3_D" name="radioInline">--%>
                                        <%--<label for="3_D"> D. sitting on</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<label class="col-sm-2 control-label">4.I will spend as much time as I ______ the lesson.</label>--%>
                                <%--<div class="col-sm-7 pt6">--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="4_A"  name="radioInline">--%>
                                        <%--<label for="4_A"> A. can go over</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="4_B" name="radioInline">--%>
                                        <%--<label for="4_B"> B. can to go over</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="4_C"  name="radioInline">--%>
                                        <%--<label for="4_C"> C. can going over</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="4_D" name="radioInline">--%>
                                        <%--<label for="4_D"> D. go over</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>


                                <%--<label class="col-sm-2 control-label">5.She ______ his number in the phone book to make sure that she had got it right.</label>--%>
                                <%--<div class="col-sm-7 pt6">--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="5_A"  name="radioInline">--%>
                                        <%--<label for="5_A"> A. looked for</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="5_B" name="radioInline">--%>
                                        <%--<label for="5_B"> B. looked up</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="5_C"  name="radioInline">--%>
                                        <%--<label for="5_C"> C. looked after</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="radio radio-inline">--%>
                                        <%--<input type="radio" id="5_D" name="radioInline">--%>
                                        <%--<label for="5_D"> D. looked like</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>









                            <%--</div>--%>
                        <%--</div><!--form-group end-->--%>
                    <%--</div>--%>

                    <%--<div class="col-sm-10 col-sm-offset-1 mt40">--%>
                        <%--<label class="col-sm-2">&nbsp;</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<button class="btn btn-primary" type="button" onClick="answerHomework();">确定</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>



                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<!--col-sm-12 end-->--%>
    <%--</div>--%>
    <%--<!--row end-->--%>
<%--</div>--%>
<%--<!--wrapper end-->--%>


<%--<script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>--%>
<%--<script src="<%=path %>/js/bootstrap.min.js?v=3.3.5"></script>--%>
<%--<script src="<%=path %>/js/plugins/iCheck/icheck.min.js"></script>--%>
<%--<script src="<%=path %>/js/hplus.min.js"></script>--%>
<%--<script type="text/javascript" src="<%=path %>/js/plugins/markdown/markdown.js"></script>--%>
<%--<script type="text/javascript" src="<%=path %>/js/plugins/markdown/to-markdown.js"></script>--%>
<%--<script type="text/javascript" src="<%=path %>/js/plugins/markdown/bootstrap-markdown.js"></script>--%>
<%--<script type="text/javascript" src="<%=path %>/js/plugins/markdown/bootstrap-markdown.zh.js"></script>--%>
<%--<script type="text/javascript">--%>
    <%----%>
    <%--function answerHomework(){--%>
        <%--var homeworkId = $("#homeworkId").val();--%>
        <%--var type = $("#homeworkType").val();--%>
        <%--var answer = "";--%>
        <%--if(type=="choice"){--%>
            <%--answer = $(":radio:checked").val();--%>
        <%--}else if(type=="judge"){--%>
            <%--answer = $(":radio:checked").val();--%>
        <%--}else if(type=="shortAnswer"){--%>
            <%--answer = $("#shortAwswerContent").val();--%>
        <%--}--%>

        <%--$.ajax({--%>
            <%--url:"./answerHomework?homeworkId="+homeworkId+"&answer="+answer,--%>
            <%--async: false,--%>
            <%--type:"get",--%>
            <%--dataType:"text",--%>
            <%--success:function(responseText){--%>
                <%--window.close();--%>
                <%--window.opener.location.reload();--%>
            <%--},--%>
            <%--error:function(){--%>
                <%--layer.msg("创建失败!",{icon: 0, time: 2000});--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
    <%----%>
    <%----%>
<%--</script>--%>
<%--</body>--%>

<%--</html>--%>
