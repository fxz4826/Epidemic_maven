<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>疫情信息</title>
    <!-- Bootstrap -->
    <jsp:include page="template/bootstrap_common.jsp"></jsp:include>
    <style type="text/css">
        #body1 {
            background-color: #fff;
        }
    </style>

</head>
<body id="body1">
<div class="container">

    <div class="row">
        <div class="col-md-12" style="background-color:#fff;margin-bottom: 5px;">
            <div id="myMap" style="height: 600px;"></div>
        </div>
    </div>


<%--饼状图--%>
    <div class="row">
        <div class="col-md-12" style="background-color:#fff;margin-bottom: 5px;">
            <div id="pieMap" style="height: 600px;"></div>
        </div>
    </div>



    <div class="row">
        <div class="col-md-12">
            <div id="mycharts" style="height: 500px;border: 1px solid gray;background-color:#fff;"></div>
        </div>
    </div>


<%--折线图--%>
    <div class="row">
        <div class="col-md-12" style="background-color:#fff;margin-bottom: 5px;">
            <div id="lineChart" style="height: 600px;"></div>
        </div>
    </div>


    <div class="container">
        <a href="${pageContext.request.contextPath}/wellcome.jsp"  class="btn btn-primary form-control">返回</a>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
<script type="text/javascript">
    $(function () {
        //获取当前日期
        var myDate = new Date();
        var y = myDate.getFullYear();
        var m = myDate.getMonth()+1;
        var d = myDate.getDate();
        var nowDate = y+"-"+m+"-"+d;


        //初始化折线图表
        var lineChart = echarts.init($("#lineChart ").get(0));
        //将服务器端返回的数据设置到图表上
        var fillToLineChart = function (epidemics) {
            var provinceNames = [];
            var affirmedTotal = [];
            $.each(epidemics, function (index, epidemic) {
                provinceNames.push(epidemic.provinceName);
                affirmedTotal.push(epidemic.affirmedTotal);
            });
            lineChart.setOption( {
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: provinceNames
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: affirmedTotal,
                    type: 'line',
                    areaStyle: {}
                }]
            });
        };


        //初始化饼状图表
        var pieMap = echarts.init($("#pieMap").get(0));

        //将服务器端返回的数据设置到图表上
        var fillToPieMap = function (epidemics) {
           var data=[];
            $.each(epidemics, function (index, epidemic) {
                var obj={};
                obj.name=epidemic.provinceName;
                obj.value=epidemic.affirmedTotal;
                data.push(obj);
            });
            pieMap.setOption({
                series : [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        data:data
                    }
                ]
            })
        };

        //初始化柱状图表
        var myCharts = echarts.init($("#mycharts").get(0));
        var option = {
            title: {
                text: "当日全国疫情柱状图",
                subtext: nowDate
            },
            grid: {
                show: true
            },
            legend: {
                data: [nowDate]
            }
            ,
            tooltip: {
                trigger: 'axis'
            }
            ,
            xAxis: {
                data: []
            }
            ,
            yAxis: {}
            ,
            series: [{
                type: 'bar',
                name: nowDate,
                data: []
            }]
        };
        myCharts.setOption(option);
        //将服务器端返回的数据设置到图表上
        var fillToChart = function (epidemics) {
            var provinceNames = [];
            var affirmedTotal = [];
            $.each(epidemics, function (index, epidemic) {
                provinceNames.push(epidemic.provinceName);
                affirmedTotal.push(epidemic.affirmedTotal);
            });
            myCharts.setOption({
                xAxis: {
                    data: provinceNames
                },
                series: [{
                    data: affirmedTotal
                }]
            });
        };
        var myMap = echarts.init($("#myMap").get(0));
        //获取地图json数据，显示中国地图
        $.getJSON("${pageContext.request.contextPath}/echarts/china.json", {}, function (chinaJson) {
            echarts.registerMap("china", chinaJson);
            var option = {
                title: {
                    text: nowDate+" 全国疫情分布图"
                },
                legend: {
                    data: ["累计确诊人数"]
                },
                tooltip: {},
                visualMap: {
                    type: 'piecewise',
                    min: 0,
                    max: 10000,
                    splitList:
                        [{
                            start: 1000,
                            end: 10000
                        }, {
                            start: 500,
                            end: 1000
                        }, {
                            start: 100,
                            end: 500
                        }, {
                            start: 0,
                            end: 100
                        }],
                    textStyle:
                        {
                            color: 'orange'
                        }
                },
                series: [
                    {
                        name: '累计确诊人数',
                        type: 'map',
                        mapType: 'china',
                        data: []
                    }
                ]
            };
            myMap.setOption(option);
        }, "json");
        //将数据填充到地图中
        var fillToMap = function (epidemics) {
            var data = [];
            $.each(epidemics, function (index, epidemic) {
                var obj = {};
                obj.name = epidemic.provinceName;
                obj.value = epidemic.affirmedTotal;
                data.push(obj);
            });
            myMap.setOption({
                series: [
                    {
                        name: '累计确诊人数',
                        type: 'map',
                        mapType: 'china',
                        data: data
                    }
                ]
            });
        };
        //发送请求获取最新疫情数据
        $.get("${pageContext.request.contextPath}/epidemicData/ajax/lastestData", {}, function (resp) {
            if (resp.code < 0) {
                alert(resp.msg);
            } else {
                fillToChart(resp.data);
                fillToMap(resp.data);

                //填充饼状图
                fillToPieMap(resp.data);

                //填充折线图
                fillToLineChart(resp.data);
            }
        }, "json");
    });
</script>
</body>
</html>
