<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/9
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";//+"WEB-INF/classes/com/controller/"
    pageContext.setAttribute("path", path);
    pageContext.setAttribute("basePath",basePath);

%>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        h4 {
            width: 180px;
            height: 38px;
            margin: 100px auto;
            text-align: center;
            line-height: 38px;
            background: mediumvioletred;
            border-radius: 4px;
            font: -apple-system-caption2;
        }
        h3 {
            width: 300px;
            height: 30px;
            margin: 20px auto;
            text-align: center;
            line-height: 38px;
            background: lawngreen;
            border-radius: 4px;
            font: -apple-system-short-caption1;
        }
        h2{
            text-align: center;
            font-size: small;
            font: -apple-system-body;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    网课模拟管理系统
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>${sessionScope.get("user").getUserName()}您好！</small>
                </h1>
            </div>
        </div>
    </div>
        <h3>
            <a href="${path}/useMainService.jsp" >模拟使用网课系统</a>
        </h3>

        <h3>
            <a href="${pageContext.request.contextPath}/soso/getInfo" >网课三类服务费用说明</a>
        </h3>
        <h3>
            <a href="${pageContext.request.contextPath}/soso/getMonthList" >本月消费情况</a>
        </h3>
        <h3>
            <a href="${path}/soso/printDetailedList" >打印消费情况详单</a>
        </h3>

    <h3>
            <a href="${path}/soso/getRemainInfo" >查询套餐内余量</a>
        </h3>
        <h3>
            <a href="${path}/changePackPage.jsp" >修改套餐包</a>
        </h3>
        <h3>
            <a href="${path}/chargePage.jsp" >充值并查询余量</a>
        </h3>
        <h3>
            <a href="${path}/deleteZombieCard.jsp" >删除僵尸小号</a>
        </h3>

</div>
<h4>
    <a href="${path}/index.jsp">返回</a>
</h4>
</body>







