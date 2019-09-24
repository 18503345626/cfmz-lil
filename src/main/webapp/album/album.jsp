<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function(){
        $("#abtable").jqGrid({
            url : '${path}/album/showAll',
            editurl:"${path}/album/edit",
            datatype : "json",
            autowidth:true,
            styleUI:"Bootstrap",
            height : "auto",
            rowNum : 3,
            rowList : [ 3, 6, 9 ],
            pager : '#abpage',
            viewrecords : true,
            colNames : [ 'ID', '名称', '封面', '作者','评分','播音', '集数','内容', '上传时间' ],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'title',editable:true,index : 'invdate',width : 90},
                {name : 'cover',editable:true,index : 'name',width : 100,align:"center",edittype:"file",
                    formatter:function(cellValue){
                        return "<img src='${path}/upload/photo/"+cellValue+"' style='width:100px;height:80px' >";
                    }},
                {name : 'author',editable:true,index : 'amount',width : 80,align : "center"},
                {name : 'score',editable:true,index : 'score',width : 80,align : "center"},
                {name : 'broadcast',editable:true,index : 'score',width : 80,align : "center"},
                {name : 'counts',editable:true,index : 'tax',width : 80,align : "center"},
                {name : 'content',editable:true,index : 'total',width : 80,align : "center"},
                {name : 'crea_date',index : 'note',width : 150,sortable : false}
            ],
            subGrid : true,  //是否开启子表格  // subgrid_id是在表数据中创建的div标签的id
            subGridRowExpanded : function(subgrid_id, row_id) {  //subgrid_id  子表格id  row_id 行id
                //开启子表格
                addSubGrid(subgrid_id,row_id);
            }
        });
        //处理增删改方法
        $("#abtable").jqGrid('navGrid', '#abpage',
            {add : true,edit : true,del : true,addtext:"添加",edittext:"修改",deltext:"删除"},
            {
                closeAfterEdit:true,
                afterSubmit:function (data) {
                    if(data.responseText!=null){
                        $.ajaxFileUpload({
                            url:"${path}/album/albumUpload",
                            datatype:"json",
                            type:"post",
                            fileElementId:"cover", // 需要上传的文件域的ID
                            data:{id:data.responseText},
                            success:function(){
                                //刷新页面
                                $("#abtable").trigger("reloadGrid");
                            }
                        });
                    }
                    return "ssaa";

                }
            },//修改后额外的操作
            { //关闭对话框
                closeAfterAdd:true,
                afterSubmit:function (data) {
                    $.ajaxFileUpload({
                        url:"${path}/album/albumUpload",
                        datatype:"json",
                        type:"post",
                        fileElementId:"cover", // 需要上传的文件域的ID
                        data:{id:data.responseText},
                        success:function(){
                            //刷新页面
                            $("#abtable").trigger("reloadGrid");
                        }
                    });

                    return "ssaa";

                }

            },//添加后额外的操作
            {}

        );
    });

    //子表格
    function addSubGrid(subgridId,rowId){

        //tableId
        var subgridTableId = subgridId+"table";

        //工具栏Id
        var pagerId = subgridId+"page";

        //在子表格中创建一个表单table，创建一个工具栏div
        $("#"+subgridId).html("" +
            "<table id='"+subgridTableId+"'/>" +
            "<div id='"+pagerId+"' />"
        );

        //初始子表格
        $("#" + subgridTableId).jqGrid({
            //url : "/chapter/queryByPage?AlbumId="+rowId,
            url : "${path}/chapter/showAll?albumId="+rowId,
            datatype : "json",
            rowNum : 3,
            rowList : [ 3, 6, 9 ],
            pager : "#"+pagerId,
            autowidth:true,
            editurl:"${path}/chapter/edit?albumId="+rowId,
            styleUI:"Bootstrap",
            height : "auto",
            viewrecords : true,
            colNames : [ 'Id', '名称', '路径', '大小','时长','上传时间','专辑Id','操作'],
            colModel : [
                {name : "id",  index : "num",width : 80,key : true},
                {name : "name",editable:true,index : "item",  width : 130},
                {name : "url",editable:true,index : "qty",width : 70,align : "center",edittype:"file"},
                {name : "sizes",index : "unit",width : 70,align : "center"},
                {name : "duration",index : "total",width : 70},
                {name : "up_date",index : "total",width : 70},
                {name : "album_id",width : 70,readOnly:true},
                {name : "url",index : "total",width : 70,
                    formatter:function(value){
                        return "<a href='#' onclick='play(\""+value+"\")'><span class='glyphicon glyphicon-play-circle'/></a> &emsp; &nbsp; " +
                            "<a href='#' onclick='downloads(\""+value+"\")'><span class='glyphicon glyphicon-download'/></a> ";
                    }
                }
            ]
        });

        //增删该方法
        $("#" + subgridTableId).jqGrid('navGrid',"#" + pagerId,
            {edit : true,add : true,del : true,addtext:"添加",edittext:"修改",deltext:"删除"},
            {
                closeAfterEdit:true,
                afterSubmit:function (data) {
                    if(data.responseText!='123'){
                        $.ajaxFileUpload({
                            url:"${path}/chapter/chapterUpload",
                            datatype:"json",
                            type:"post",
                            fileElementId:"url", // 需要上传的文件域的ID
                            data:{id:data.responseText},
                            success:function(){
                                //刷新页面
                                $("#"+subgridTableId).trigger("reloadGrid");
                            }
                        });
                    }
                    return "ssaa";

                }
            },
            {
                closeAfterAdd:true,
                afterSubmit:function (data) {
                        $.ajaxFileUpload({
                            url:"${path}/chapter/chapterUpload",
                            datatype:"json",
                            type:"post",
                            fileElementId:"url", // 需要上传的文件域的ID
                            data:{id:data.responseText},
                            success:function(){
                                //刷新页面
                                $("#"+subgridTableId).trigger("reloadGrid");
                            }
                        });
                    return "ssaa";

                    }
                },
            {}
        );
    }

    //在线播放
    function play(name){
        location.href="${path}/chapter/chapterPlay?filename="+name;
    }

    //下载
    function downloads(name){
        location.href="${path}/chapter/chapterDownload?filename="+name;
    }

</script>

<div class="panel panel-info">

    <div class="panel panel-heading">
        专辑信息
    </div>

    <ul class="nav nav-tabs" >
        <li class="active"><a href="#">专辑信息</a></li>
    </ul>

    <%--初始表单--%>
    <table id="abtable" />

    <%--分页工具栏--%>
    <div id="abpage" />
</div>