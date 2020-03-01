<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<html>
<head>
    <base href="<%=basepath %>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生作业列表</title>
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
                url: './homework/homeworkList.do',
                queryParams: function (params) {
                    var param = {
                        currentPageNo: params.offset / params.limit + 1,
                        pageTotalNum:  params.limit
                    };
                    return param;
                },
                responseHandler: function (data) {
                    console.log("data-"+data);
                    var approve = "";

                    for(var i=0;i<data.list.length;i++){
                        var item = data.list[i];
                        approve = '<a href="javascript:;" onclick="checkHomework(' + item.homeworkAndUserId + ')">查看</a>　';
                        item.OPERATE = approve;
                    }
                    return {
                        total: data.totalNum,
                        rows: data.list || []
                    };
                },
                striped: 'true',
                pagination: 'true',
                sidePagination: 'server',
                columns: [{
                    field: 'studentName',
                    title: '学生',
                    width:'55'
                },{
                    field: 'subject',
                    title: '学科',
                    width: '55'
                },{
                    field: 'homeworkType',
                    title: '类型',
                    width: '55'
                },{
                    field: 'title',
                    title: '标题',
                },{
                    field: 'OPERATE',
                    title: '操作',
                    width:'120'
                }]
            });

        });

        function checkHomework(id){
            window.open("./homework/toCheckHomework?homeworkAndUserId="+id,"_blank","width=680,height=650,top=100px,left=150px");
        }

    </script>

</head>



<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">

            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>学生作业列表</h5>
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

</body>
</html>