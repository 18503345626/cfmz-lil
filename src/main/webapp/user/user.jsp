<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="application/javascript">
    $("#usertable").jqGrid({
        styleUI: "Bootstrap",
        autowidth: true,
        editurl: "${path}/user/edit",
        pager: '#userpage',  //分页工具栏
        viewrecords: true,  //是否显示总条数
        autowidth: true,
        height: "auto",
        rowNum: 2,  //每页展示条数   page   rows
        rowList: [3, 6, 9],  //可选展示条数
        url: "${path}/user/showAll",
        datatype: "json",
        colNames: ['Id', '头像', '手机号', '密码', '状态', '姓名', '法名', '性别', '所在地', '签名', '注册时间', '上师id'],
        colModel: [
            {name: 'id', width: 55, align: "center"},
            {
                name: 'avatar', editable: true, width: 90, align: "center",  edittype: "file",
                formatter: function (cellValue) {
                    return "<img src='${path}/upload/photo/" + cellValue + "' style='width:100px;height:80px' >";
                }
            },
            {name: 'phone', editable: true, width: 100, align: "center"},
            {name: 'password', editable: true, width: 100, align: "center"},
            {
                name: 'status', editable: true, width: 80, align: "center",
                formatter: function (cellValue, rowObject) {
                    if (cellValue != "冻结") {
                        return "<button class='btn btn-info' onclick='changeStatu(\"" + rowObject.rowId + "\")'>" + cellValue + "</button>";
                    } else {
                        return "<button class='btn btn-warning' onclick='changeStatu(\"" + rowObject.rowId + "\")'>" + cellValue + "</button>";
                    }
                }
            },
            {name: 'name', editable: true, width: 80, align: "center"},
            {name: 'law_name', editable: true, width: 80, align: "center"},
            {name: 'sex', editable: true, width: 80, align: "center"},
            {name: 'city', editable: true, width: 80, align: "center"},
            {name: 'sign', editable: true, width: 80, align: "center"},
            {name: 'crea_date', width: 80, align: "center"},
            {name: 'guru_id', width: 80, align: "center"}
        ]

    }).jqGrid("navGrid", '#arpage', {edit: true, add: true, del: true, addtext: "添加", edittext: "修改", deltext: "删除"},
        {
            closeAfterEdit: true
        },//修改后额外的操作
        { //关闭对话框
            closeAfterAdd: true
        },//添加后额外的操作
        {});//删除后额外的操作
    //用于修改状态
    function changeStatu(id) {
        $.ajax({
            url: "${path}/user/changeStatus",
            datatype: "json",
            type: "post",
            data: "rowId=" + id,
            success: function () {
                $("#usertable").trigger("reloadGrid");
            }
        })
    }
    //导出用户信息
        $("#out").click(function () {
            $.ajax({
                url:"${path}/user/outUser",
                dataType:"JSON",
                type:"GET",
                success:function (data) {
                    $("#message").text(data.message);
                }
            })
        });
    $("#phone").click(function () {
      var phone = $("#inputPhone").val();
      $.get("${path}/user/phoneNumber?phone="+phone,function (data) {
            alert(data);
      },"text");
    });

</script>
<div class="panel panel-info">


    <div class="panel panel-heading">
        用户信息
    </div>
    <button id="out" class="btn btn-danger">导出用户</button><span id="message" style="color: red"></span>&nbsp;&nbsp;
    <div align="right">
    <input id="inputPhone" type="text" />&nbsp;&nbsp;
    <button id="phone" class="btn btn-danger">发送验证码</button>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">用户信息</a></li>
    </ul>

    <%--初始表单--%>
    <table id="usertable"/>

    <%--分页工具栏--%>
    <div id="userpage"/>


</div>