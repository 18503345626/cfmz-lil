<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%--页面添加以下脚本--%>
<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id',{
            uploadJson:"${path}/editor/uploadEditor",   //指定上传图片的路径
            filePostName:"photo",   //设置上传图片的名称
            allowFileManager:true, //是否展示浏览远程图片按钮
            fileManagerJson:"${path}/editor/queryPhotos", //指定浏览远程图片的路径
            afterBlur:function(){   //在kindeditor失去焦点之后执行的内容
                this.sync();  //将kindeditor中的内容同步到表单中
            }
        }
    );
</script>
<script type="application/javascript">
    $("#artable").jqGrid({
        styleUI:"Bootstrap",
        autowidth:true,
        editurl:"${path}/article/edit",
        pager : '#arpage',  //分页工具栏
        viewrecords : true,  //是否显示总条数
        autowidth:true,
        height : "auto",
        rowNum : 2,  //每页展示条数   page   rows
        rowList : [3,6,9],  //可选展示条数
        url:"${path}/article/showAll",
        datatype:"json",
        colNames : [ 'Id', '标题', '作者', '内容', '发布时间','上师id'],
        colModel : [
            {name : 'id',width : 55,align : "center"},
            {name : 'title',editable:true,align : "center",width : 90},
            {name : 'author',editable:true,width : 100,align : "center"},
            {name : 'content',editable:true,width : 80,align : "center"},
            {name : 'crea_date',width : 80,align : "center"},
            {name : 'guru_id',width : 80,align : "center"}
        ]

    }).jqGrid("navGrid", '#arpage', {edit : false ,add : false,del:true,addtext:"添加",edittext:"修改",deltext:"删除"},
        {
            closeAfterEdit:true
        },//修改后额外的操作
        { //关闭对话框
            closeAfterAdd:true
        },//添加后额外的操作
        {});//删除后额外的操作

    /**
     * 展示文章信息
     */
    $("#btn1").click(function () {
        //只读属性，最后选择行的id
        var rowId = $("#artable").jqGrid("getGridParam","selrow");
        if(rowId!=null){
            //根据行Id获取行数据
            var row= $("#artable").jqGrid("getRowData",rowId);
            //给  title input框设置数据
            $("#tit").val(row.title);
            $("#aut").val(row.author);
            //给富文本编辑器设置内容
            KindEditor.html("#editor_id",row.content);
            //展示模态框
            $("#MyModals").modal("show");
            $("#MyFooter").html("<button class='btn btn-success' onclick='updateArticle(\""+rowId+"\")' >提交</button>" +
                "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>");
        }else {
            alert("请选中一行");
        }
    });
    //添加文章
   function updateArticle(id){
        $.ajax({
           url:"${path}/article/updateArticle?id="+id,
            dataType:"json",
            type:"post",
            data:$("#MyForm").serialize(),
            success:function () {
                //关闭模态框
                $("#MyModals").modal('hide');
                //刷新页面
                $("#artable").trigger("reloadGrid");
            }
        });
   }

   //添加按钮
    $("#btn2").click(function () {
        $("#MyForm")[0].reset();
        //清空kindeditor
        KindEditor.html("#editor_id","");
        $("#MyModals").modal("show");
        $("#MyFooter").html("<button class='btn btn-success' onclick='addArticle()' >提交</button>" +
            "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>");
    });

   function addArticle(){
       $.ajax({
           url:"${path}/article/addArticle",
           dataType:"json",
           type:"post",
           data:$("#MyForm").serialize(),
           success:function () {
               //关闭模态框
               $("#MyModals").modal('hide');
               //刷新页面
               $("#artable").trigger("reloadGrid");
           }
       });
   }
    $("#del").click(function() {
        var gr = jQuery("#artable").jqGrid('getGridParam', 'selrow');
        if (gr != null)
            jQuery("#artable").jqGrid('delGridRow', gr, {
                reloadAfterSubmit : true
            });
        else
            alert("选一行删除!");
    });

</script>
<div class="panel panel-info">

    <div class="panel panel-heading">
        文章信息
    </div>
    <div class="panel panel-body">
        <button id="btn1" class="btn btn-info">文章信息</button>&emsp;
        <button id="btn2" class="btn btn-info">添加文章</button>&emsp;
        <button id="del" class="btn btn-danger">删除文章</button>
    </div>
    <ul class="nav nav-tabs" >
        <li class="active"><a href="#">文章信息</a></li>
    </ul>

    <%--初始表单--%>
    <table id="artable" />

    <%--分页工具栏--%>
    <div id="arpage" />


    <%--初始化模态框--%>
    <div id="MyModals" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="width:730px;">

                <%--模态框标题--%>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">文章信息</h4>
                </div>

                <%--模态框内容--%>
                <div class="modal-body" align="center">
                    <%--放一个表单--%>
                    <form id="MyForm" class="form-horizontal">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">标题</span>
                            <input id="tit" name="title" type="text" class="form-control" aria-describedby="basic-addon1" style="width: 200px">
                        </div></br></br>

                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon2">作者</span>
                            <input id="aut" name="author" type="text" class="form-control" aria-describedby="basic-addon1" style="width: 200px">
                        </div></br></br>
                        <%--kindeditor输入框--%>
                        <div align="center" class="input-group">
                            <%--在需要显示编辑器的位置添加textarea输入框--%>
                            <textarea id="editor_id" name="content" style="width:700px;height:300px;"></textarea>
                        </div>
                    </form>
                </div>

                <%--模态框按钮--%>
                <div class="modal-footer" id="MyFooter">
                    <%--<button type="button" class="btn btn-default">提交</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>--%>
                </div>
            </div>
        </div>
    </div>


</div>