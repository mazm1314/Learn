<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/resources/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<base href="<%=basePath%>">
<title>欢迎登录</title>
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
</head>


<body>
<section> 
<!-- START Page content--> 
<section class="main-content">
<h3>
               <hr>
               <small>亲爱的<c:out value="${userName}"></c:out>您好：欢迎来到课程学习系统</small> </h3>
                     
		<div class="panel panel-default">
			<div class="panel-body">
				<c:if test="${fn:contains(userName,'ls')}">
					您有<c:out value="${commentNum}"></c:out>条留言请查看！<br/>
				</c:if>
				<c:if test="${fn:contains(userName,'xs')}">
					您有<c:out value="${homeworkNum}"></c:out>条作业请查看！<br/>

					<c:forEach items="${messageList}" var="item">
						${item.teacherName}发布消息：${item.message} <br/>
					</c:forEach>


				</c:if>
			</div>
		</div>


	</section> 
     <!-- END Page content-->
    </section>
</body>
</html>