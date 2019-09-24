<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>
    <script src="${path}/user/js/echarts.js"></script>
    <script src="${path}/user/js/china.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
</head>
<body>
    <!--顶部导航-->
    <nav class="navbar navbar-default">
        <!--导航条充满整个屏幕-->
        <div class="container-fluid">
            <!--导航条标题-->
            <div class="navbar-header">
                <!--导航条触发器的样式-->     <!--给该按钮添加导航条的触发器--> <!--点击按钮后触发对应的菜单项-->
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <!--按钮中添加3个横杠-->
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!--标题-->
                <a class="navbar-brand" href="#">持名法州管理系统</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <!--导航条展示的具体内容-->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" class="">欢迎:<span class="text-primary">${sessionScope.login.adminname}</span></a></li>
                    <li><a href="${path}/admin/logout" class="">退出登录<span class="glyphicon glyphicon-log-out"></span></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!--栅格系统-->
    <div class="container-fluid">
        <div class="row">
            <div class="row">
                <div class="col-md-2">
                    <div class="panel-group" id="accordion">
                        <!--默认的面板样式-->
                        <div class="panel panel-default panel-info">
                            <!--面板的头部-->
                            <div class="panel-heading">
                                <!--面板的标题-->
                                <h4 class="panel-title">
                                    <!--添加折叠面板的触发器-->                        <!--触发器触发的目标内容-->
                                    <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                        <center>用户管理</center>
                                    </a>
                                </h4>
                            </div>
                            <!--作为折叠的具体内容-->
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <!--面板内容-->
                                <div class="panel-body">
                                   <center><button  class="btn btn-warning"><a href="javascript:$('#mainId').load('${path}/user/user.jsp')">用户信息</a></button></center><br/>
                                    <center><button  class="btn btn-warning"><a href="javascript:$('#mainId').load('${path}/user/userEcharts.jsp')">用户统计</a></button></center><br/>
                                    <center><button  class="btn btn-warning"><a href="javascript:$('#mainId').load('${path}/user/userChina.jsp')">用户分布</a></button></center>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <!--默认的面板样式-->
                        <div class="panel panel-default panel-success">
                            <!--面板的头部-->
                            <div class="panel-heading">
                                <!--面板的标题-->
                                <h4 class="panel-title">
                                    <!--添加折叠面板的触发器-->                        <!--触发器触发的目标内容-->
                                    <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne2">
                                        <center>轮播图管理</center>
                                    </a>
                                </h4>
                            </div>
                            <!--作为折叠的具体内容-->
                            <div id="collapseOne2" class="panel-collapse collapse">
                                <!--面板内容-->
                                <div class="panel-body">
                                    <ul class="nav  nav-pills  nav-stacked">
                                        <li class="text-center"><button class="btn btn-info"><a href="javascript:$('#mainId').load('${path}/banner/banner.jsp')">展示轮播图</a></button></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <!--默认的面板样式-->
                        <div class="panel panel-default panel-warning">
                            <!--面板的头部-->
                            <div class="panel-heading">
                                <!--面板的标题-->
                                <h4 class="panel-title">
                                    <!--添加折叠面板的触发器-->                        <!--触发器触发的目标内容-->
                                    <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne3">
                                        <center>专辑管理</center>
                                    </a>
                                </h4>
                            </div>
                            <!--作为折叠的具体内容-->
                            <div id="collapseOne3" class="panel-collapse collapse">
                                <!--面板内容-->
                                <div class="panel-body">
                                    <ul class="nav  nav-pills  nav-stacked">
                                        <li class="text-center"><button class="btn btn-info"><a href="javascript:$('#mainId').load('${path}/album/album.jsp')">展示专辑</a></button></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <!--默认的面板样式-->
                        <div class="panel panel-default panel-danger ">
                            <!--面板的头部-->
                            <div class="panel-heading">
                                <!--面板的标题-->
                                <h4 class="panel-title">
                                    <!--添加折叠面板的触发器-->                        <!--触发器触发的目标内容-->
                                    <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne4">
                                        <center>文章管理</center>
                                    </a>
                                </h4>
                            </div>
                            <!--作为折叠的具体内容-->
                            <div id="collapseOne4" class="panel-collapse collapse">
                                <!--面板内容-->
                                <div class="panel-body">
                                    <ul class="nav  nav-pills  nav-stacked">
                                        <li class="text-center"><button class="btn btn-info"><a href="javascript:$('#mainId').load('${path}/artcle/artcle.jsp')">展示文章</a></button></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <!--默认的面板样式-->
                        <div class="panel panel-default panel-success">
                            <!--面板的头部-->
                            <div class="panel-heading">
                                <!--面板的标题-->
                                <h4 class="panel-title">
                                    <!--添加折叠面板的触发器-->                        <!--触发器触发的目标内容-->
                                    <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne5">
                                        <center>上师管理</center>
                                    </a>
                                </h4>
                            </div>
                            <!--作为折叠的具体内容-->
                            <div id="collapseOne5" class="panel-collapse collapse">
                                <!--面板内容-->
                                <div class="panel-body">
                                    上师
                                </div>
                            </div>
                        </div>
                        <br/>
                        <!--默认的面板样式-->
                        <div class="panel panel-default panel-warning">
                            <!--面板的头部-->
                            <div class="panel-heading">
                                <!--面板的标题-->
                                <h4 class="panel-title">
                                    <!--添加折叠面板的触发器-->                        <!--触发器触发的目标内容-->
                                    <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne6">
                                        <center>管理员管理</center>
                                    </a>
                                </h4>
                            </div>
                            <!--作为折叠的具体内容-->
                            <div id="collapseOne6" class="panel-collapse collapse">
                                <!--面板内容-->
                                <div class="panel-body">
                                    管理员
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-10" id="mainId">
                    <div class="jumbotron">
                        <h3>欢迎来到持名法州后台管理系统</h3>
                    </div>
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" align="center">
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>

                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <img src="${path}/bootstrap/img/shouye.jpg" alt="...">
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/img/2.png" alt="...">
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/img/3.png" alt="...">
                            </div>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-footer text-center">@百知教育 lil@zparkhr.com</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <!--左边手风琴部分-->
        <!--巨幕开始-->
        <!--右边轮播图部分-->
        <!--页脚-->
    <!--栅格系统-->

</body>
</html>
