<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE HTML>
<html>
<head>

    <title>首页</title>
    <style type="text/css">
        a {
            text-decoration: none;
            color: black;
            font-size: 18px;
        }
        h3 {
            width: 300px;
            height: 40px;
            margin: 20px auto;
            text-align: center;
            line-height: 38px;
            background: lawngreen;
            border-radius: 4px;
            font: -apple-system-short-caption1;
        }
        h2{
            text-align: center;
            font: -apple-system-body;
            color: black;
        }
        h1{
            text-align: center;
            font-family: STHeiti Light, fantasy;
            color: black;
        }

        login_frame {
            width: 400px;
            height: 260px;
            padding: 13px;

            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -200px;

            background-color: rgba(240, 255, 255, 0.5);

            border-radius: 10px;
            text-align: center;
        }

    </style>
</head>
<body>
<div class="login_frame">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    网课模拟管理系统
                </h1>
                <h2>
                    @开发者：Zhutian Lin
                </h2>
            </div>
        </div>
    </div>
</div>

<br><br>
<h3>
    <a href="Login.jsp" >登录</a>
</h3>
<h3>
    <a href="Register.jsp" >注册</a>
</h3>
<h3>
    <a href="delete.jsp" >注销</a>
</h3>

</body>

