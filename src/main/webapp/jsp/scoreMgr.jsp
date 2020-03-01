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
    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=path%>/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="<%=path%>/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%=path%>/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path%>/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="<%=path%>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path%>/css/layout.css?v=1.0.0" rel="stylesheet">
    <base target="_blank">


    <script src="<%=path%>/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=path%>/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="<%=path%>/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="<%=path%>/js/plugins/layer/layer.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#subjectSelect").bind({
                change:function () {
                    //debugger;
                    var subjectName = $("#subjectSelect").val();
                    var url = '../score/list?subjectName='+subjectName;
                    $("#table").bootstrapTable('refresh',{url:url});
                }
            });
            updateTable("数学");
        });

        function updateTable(subjectName) {
            /* 申请单文档库ID */
            var docLibID = "";
            /* 流程节点ID */
            var flowNodeID
            /* 每页记录总数 */
            var pageTotalNum = "";
            /* 当前页 */
            var currentPageNo = "";
            var $table = $('#table');
            $table.bootstrapTable({
                toggle: 'table',
                url: '../score/list?subjectName='+subjectName,
                queryParams: function (params) {
                    var param = {
                        currentPageNo: params.offset / params.limit + 1,
                        pageTotalNum:  params.limit
                    };
                    return param;
                },
                responseHandler: function (data) {
                    return {
                        total: data.totalNum,
                        rows: data.list || []
                    };
                },
                striped: 'true',
                pagination: 'true',
                sidePagination: 'server',
                columns: [{
                    field: 'seq',
                    title: '序号',
                    width:'150'
                },{
                    field: 'subject',
                    title: '课程',
                    width:'250'
                },{
                    field: 'studentName',
                    title: '学生',
                },{
                    field: 'score',
                    title: '成绩',
                    width:"150"
                }]
            });
        }
        
        


    </script>
</head>


<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>查询</h5>
                </div>
                <div class="ibox-content clearfix" id="search_tj">
                    <div class="" id="search_tt">
                        <div class="form-group clearfix" >
                            <div class="col-sm-3">
                                <select name="meeting" id="subjectSelect" class="form-control" >
                                    <option selected value="数学">数学</option>
                                    <option value="英语">英语</option>
                                    <option value="语文">语文</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--ibox end-->

            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>学生成绩列表信息</h5>
                </div>

                <div data-role="content">
                    <div data-role="main">
                        <table id="table">
                        </table>
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

<style type="text/css">
    th, td { white-space: nowrap; }

    .DTFC_RightWrapper{ right:0px !important}
    .dataTable{ width:100% !important}
</style>

</body>
</html>