<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="application/javascript">
    $("#bntable").jqGrid({
        styleUI:"Bootstrap",
        autowidth:true,
        editurl:"${path}/banner/edit",
        pager : '#bnpage',  //分页工具栏
        viewrecords : true,  //是否显示总条数
        autowidth:true,
        height : "auto",
        rowNum : 2,  //每页展示条数   page   rows
        rowList : [3,6,9],  //可选展示条数
        url:"${path}/banner/showAll",
        datatype:"json",
        colNames : [ 'Id', '名字', '图片', '状态', '描述','上传时间'],
        colModel : [
            {name : 'id',width : 55},
            {name : 'name',editable:true,width : 90},
            {name : 'img_path',editable:true,width : 100,align : "center",edittype:"file",
                formatter:function(cellValue){
                    return "<img src='${path}/upload/photo/"+cellValue+"' style='width:100px;height:80px' >";
                }
            },
            {name : 'status',width : 80,align : "center",
                formatter:function(cellValue,rowObject){
                    if(cellValue!="冻结"){
                        return "<button class='btn btn-info' onclick='changeStatu(\""+rowObject.rowId+"\")'>"+cellValue+"</button>";
                    }else {
                        return "<button class='btn btn-warning' onclick='changeStatu(\""+rowObject.rowId+"\")'>"+cellValue+"</button>";
                    }
                }
            },
            {name : 'description',editable:true,width : 80,align : "right"},
            {name : 'up_date',width : 80,align : "right"}
        ]

    }).jqGrid("navGrid", '#bnpage', {edit : true,add : true,del : true,addtext:"添加",edittext:"修改",deltext:"删除"},
        {
            closeAfterEdit:true,
            afterSubmit:function (data) {
                if(data.responseText!=null){
                $.ajaxFileUpload({
                    url:"${path}/banner/bannerUpload",
                    datatype:"json",
                    type:"post",
                    fileElementId:"img_path", // 需要上传的文件域的ID
                    data:{id:data.responseText},
                    success:function(){
                        //刷新页面
                        $("#bntable").trigger("reloadGrid");
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
                    url:"${path}/banner/bannerUpload",
                    datatype:"json",
                    type:"post",
                    fileElementId:"img_path", // 需要上传的文件域的ID
                    data:{id:data.responseText},
                    success:function(){
                        //刷新页面
                        $("#bntable").trigger("reloadGrid");
                    }
                });

                return "ssaa";
                
            }

        },//添加后额外的操作
        {});//删除后额外的操作
function changeStatu(id) {
   $.ajax({
       url:"${path}/banner/changeStatus",
       datatype:"json",
       type:"post",
       data: "rowId="+id,
       success:function () {
           $("#bntable").trigger("reloadGrid");
       }
   })
    
}

</script>
<div class="panel panel-info">

    <div class="panel panel-heading">
        轮播图信息
    </div>

    <ul class="nav nav-tabs" >
        <li class="active"><a href="#">轮播图信息</a></li>
    </ul>

    <%--初始表单--%>
    <table id="bntable" />

    <%--分页工具栏--%>
    <div id="bnpage" />
</div>