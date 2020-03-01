<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>个人信息</title>
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
               <small>亲爱的用户您好：欢迎来到个人中心</small>            </h3>
                     
		<div class="panel panel-default">
			<div class="panel-body">
				<form method="get" class="form-horizontal">

					<fieldset>
						<div class="form-group">
							<label class="col-sm-2 control-label">用户ID</label>
							<div class="col-sm-4" style="padding-top:9px;" id="userinfo_name">${userId}</div>
						</div>
					</fieldset>

				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-4" style="padding-top:9px;">${userName}</div>
					</div>
				</fieldset>
				

				<!--
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-4" id="userinfo_email">${email}</div>
					</div>
				</fieldset>
				-->
					<!--
				<fieldset>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<button type="button" id="edit" style="display: block"
								class="btn btn-primary">编辑</button>
							<button type="button" id="cancel" style="display: none"
								class="btn btn-default">取消</button>
							<button type="button" id="save" style="display: none"
								class="btn btn-primary">保存修改</button>
						</div>
					</div>
				</fieldset>
				-->
				</form>
			</div>
		</div>


	</section> 
     <!-- END Page content-->
    </section>
</body>
</html>