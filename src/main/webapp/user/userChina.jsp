<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
    $(function(){

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.get("${path}/user/userChina",function(data){

            var series =[];

            for(var i=0;i<data.length;i++){
                var d =data[i];

                series.push({
                    name: d.title,
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data:d.citys
                });

            }

            // 指定图表的配置项和数据
            var option = {
                title : {
                    text: '每月用户注册量',
                    subtext: '纯属虚构',
                    left: 'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data:['小男生','小姑娘']
                },
                visualMap: {
                    min: 0,
                    max: 10,
                    left: 'left',
                    top: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    left: 'right',
                    top: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series :series
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        },"json");
    });
</script>
<script>
    $(function(){

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var goEasy = new GoEasy({
            appkey: "BC-85bd4afa4c4d4f3db9e61c7824b1dd74"  //你的appkey
        });
        goEasy.subscribe({
            channel: "cmfz-lil",  //管道名称
            onMessage: function (message) {
                var d1=message.content;
                //将json字符串转化为json对象
                var data =JSON.parse(d1);
                var series =[];
                for(var i=0;i<data.length;i++){
                    var d =data[i];
                    series.push({
                        name: d.title,
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:d.citys
                    });

                }
                // 指定图表的配置项和数据
                var option = {
                    title : {
                        text: '每月用户注册量',
                        subtext: '纯属虚构',
                        left: 'center'
                    },
                    tooltip : {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data:['小男生','小姑娘']
                    },
                    visualMap: {
                        min: 0,
                        max: 10,
                        left: 'left',
                        top: 'bottom',
                        text:['高','低'],           // 文本，默认为数值文本
                        calculable : true
                    },
                    toolbox: {
                        show: true,
                        orient : 'vertical',
                        left: 'right',
                        top: 'center',
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    series :series
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });
        <%--$.get("${path}/user/userChina",function(data){--%>

        <%--},"json");--%>
    });
</script>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>

</body>