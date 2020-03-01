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
    <title>文件管理</title>
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
                url: './index/fileList.do',
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
                    if(data.flag!="ls"){
                        $("#tul1").css("display", "none"); // 隐藏上传模块；
                    }

                    for(var i=0;i<data.list.length;i++){
                        var item = data.list[i];
                        if(data.flag=="ls"){
                            approve = '<a href="javascript:;" onclick="deleteFile(' + item.fileId + ')">删除</a>　';
                        }else{
                            var currentFileId = item.fileId;
                            approve = '<a href="./index/fileDownload.do?fileId='+currentFileId+'">下载</a>　';
                        }
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
                    field: 'fileId',
                    title: 'ID'
                }, {
                    field: 'fileName',
                    title: '文件名'
                }, {
                    field: 'OPERATE',
                    title: '操作',
                    width:'120'
                }]
            });

        });

        function downloadFile(id) {
            $.ajax({
                url:"./index/fileDownload.do?fileId="+id,
                async: false,
                type:"get",
                dataType:"text",
                success:function(responseText){
                    //window.location.reload();
                },
                error:function(){
                    //layer.msg("删除失败!",{icon: 0, time: 2000});
                }
            });
        }


        function deleteFile(id) {
            layer.confirm('确认删除?',{title:'删除文件'},function (index) {
                $.ajax({
                    url:"./index/deleteFile.do?fileId="+id,
                    async: false,
                    type:"get",
                    dataType:"text",
                    success:function(responseText){
                        window.location.reload();
                    },
                    error:function(){
                        layer.msg("删除失败!",{icon: 0, time: 2000});
                    }
                });
            });
        };

    </script>

</head>



<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">

            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>文件管理列表信息</h5>
                    <div class="ibox-tools">
                        <form id="tul1" action="./index/fileUpload" enctype="multipart/form-data" method="post">
                            <input type="file" name="file" style="float:left;"/>
                            <input type="submit" value="上传"/>
                        </form>
                        <!--
                        <a class="btn btn-info dropdown-toggle"  href="新建会议.html" target="iframe0">
                        <i class="fa fa-file-o"></i> 文件上传 </a>
                        <a  id="drop1" class="btn btn-success dropdown-toggle" data-toggle="dropdown" href="#"> <i class="fa fa-arrow-circle-o-down"></i> 导出数据到Excel </a>
                        <ul class="dropdown-menu" aria-labelledby="drop1">
                            <li><a >导出选择列</a> </li>
                            <li><a >导出全部</a> </li>
                            <li><a >导出搜索结果</a> </li>
                        </ul>
                        -->


                    </div>
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