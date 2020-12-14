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


<div class="row1" style="height: auto; overflow: auto;">
    <div class="col-md-16" style="background-color:#fff;">
        <table class="table table-hover table-bordered table-striped">
            <thead>
            <tr>
                <th>省份</th>
                <th>确诊人数</th>
                <th>疑似人数</th>
                <th>隔离人数</th>
                <th>治愈人数</th>
                <th>死亡人数</th>
            </tr>
            </thead>
            <tbody id="tbody1">

            </tbody>
        </table>
    </div>
</div>

<div class="container">
    <a href="${pageContext.request.contextPath}/wellcome.jsp" class="btn btn-primary form-control">返回</a>
</div>


<%--<script type="text/javascript" src="${pageContext.request.contextPath}/echarts/echarts.js"></script>--%>
<script type="text/javascript">
    $(function () {
        //获取当前日期
        var myDate = new Date();
        var y = myDate.getFullYear();
        var m = myDate.getMonth()+1;
        var d = myDate.getDate();
        var nowDate = y+"-"+m+"-"+d;


        //定义个用来给表格中装载数据的函数
        var fillToTable = function (epidemics) {
            var tbody1 = $("#tbody1");
            tbody1.empty();
            $.each(epidemics, function (index, epidemic) {
                var tr = $("<tr>");
                var td = $("<td>");
                td.text(epidemic.provinceName);
                tr.append(td);
                td = $("<td>");
                td.html("" + epidemic.affirmedTotal);
                tr.append(td);

                td = $("<td>");
                td.html("" + epidemic.suspectedTotal);
                tr.append(td);


                td = $("<td>");
                td.html("" + epidemic.isolatedTotal);
                tr.append(td);

                td = $("<td>");
                td.html("" + epidemic.curedTotal);
                tr.append(td);

                td = $("<td>");
                td.html("" + epidemic.deadTotal);
                tr.append(td);

                tbody1.append(tr);
            });
        };

        //发送请求获取最新疫情数据
        $.get("${pageContext.request.contextPath}/epidemicData/ajax/lastestData", {}, function (resp) {
            if (resp.code < 0) {
                alert(resp.msg);
            } else {
                fillToTable(resp.data);
            }
        }, "json");
    });
</script>
</body>
</html>
