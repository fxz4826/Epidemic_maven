<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/24
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册界面</title>
    <!--引用Bootstrap配置-->
    <jsp:include page="template/bootstrap_common.jsp"></jsp:include>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">--%>
    <%--    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>--%>
    <%--    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>--%>
</head>
<body style="background: #337ab7">
<div class="modal-dialog" style="margin-top:10%;">
    <form action="${pageContext.request.contextPath}/user/regist" method="post" class="form-horizontal">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title text-center" style="color:#337ab7;">疫情数据统计分析系统</h2>
            </div>
            <div class="modal-body">
                <div class="form-group" style="margin-left: 20px;margin-right: 20px;">
                    <input type="text" class="form-control" placeholder="请输入账号" autocomplete="off" name="account">
                </div>
                <div class="form-group" style="margin-left: 20px;margin-right: 20px;">
                    <input type="password" class="form-control" placeholder="请输入密码" autocomplete="off" name="password">
                </div>
                <div class="form-group" style="margin-left: 20px;margin-right: 20px;">
                    <input type="text" class="form-control" placeholder="请输入昵称" autocomplete="off" name="userName">
                </div>
            </div>
            <div class="modal-footer">
                <div class="form-group" style="margin-left: 20px;margin-right: 20px;">
                    <button type="submit" class="btn btn-primary form-control">注册</button>
                   <a href="login.jsp">返回</a>
                </div>
                <c:if test="${not empty msg}">
                    <div class="row">
                        <div class="col-md-3 col-md-offset-3">
                            <div style="width: 300px" class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert"><span>×</span></button>
                                    ${msg}
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </form>
</div>

</body>
</html>
