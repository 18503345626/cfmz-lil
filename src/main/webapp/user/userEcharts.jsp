<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    $(function(){
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        $.get("${path}/user/userEcharts",function (data) {
            var option = {
                title: {
                    text: '用户注册量趋势图',  //标题内容
                    show:true,
                    link:"${path}/main/main.jsp",
                    subtext:"纯属虚构",
                    sublink:"${path}/main/main.jsp",
                    subtarget:"self",
                },
                tooltip: {},  //鼠标提示
                legend: {
                    data:['小男生','小姑娘']   //选项卡
                },
                xAxis: {
                    data: data.month //横轴展示
                },
                yAxis: {},   //纵轴展示   自适应
                series: [{
                    name: '小男生',
                    type: 'bar',
                    data: data.boys //数据
                },{
                    name: '小姑娘',
                    type: 'bar',
                    data: data.girls //数据
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },"json");
    });
</script>

<script type="text/javascript">
    $(function(){

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        //GoEasy订阅消息
        var goEasy = new GoEasy({
            appkey: "BC-85bd4afa4c4d4f3db9e61c7824b1dd74"  //你的appkey
        });
        goEasy.subscribe({
            channel: "cmfz-lil",  //管道名称
            onMessage: function (message) {

                var data=message.content;

                //将json字符串转化为json对象
                var d =JSON.parse(data);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '用户注册量趋势图',  //标题内容
                        show:true,
                        link:"${path}/main/main.jsp",
                        subtext:"纯属虚构",
                        sublink:"${path}/main/main.jsp",
                        subtarget:"self",
                    },
                    tooltip: {},  //鼠标提示
                    legend: {
                        data:['小男生','小姑娘']   //选项卡
                    },
                    xAxis: {
                        data: d.month  //横轴展示
                    },
                    yAxis: {},   //纵轴展示   自适应
                    series: [{
                        name: '小男生',
                        type: 'bar',
                        data: d.boys  //数据
                    },{
                        name: '小姑娘',
                        type: 'bar',
                        data: d.girls  //数据
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        });
    });

</script>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

</body>