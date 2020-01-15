<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>main</title>
<link rel="stylesheet" type="text/css" href=<%=application.getContextPath()+"/pages/css/common.css" %> />
<link rel="stylesheet" type="text/css" href=<%=application.getContextPath()+"/pages/css/main.css" %> />
<link rel="stylesheet" type="text/css" href=<%=application.getContextPath()+"/pages/css/conferApprove.css"  %>/>
<link rel="stylesheet" type="text/css" href=<%=application.getContextPath()+"/pages/css/myConfer.css"  %>/>
<link rel="stylesheet" type="text/css" href=<%=application.getContextPath()+"/pages/css/searchConfer.css"  %>/>
<link rel="stylesheet" type="text/css" href=<%=application.getContextPath()+"/pages/css/updConfer.css"  %>/>
<script src=<%=application.getContextPath()+"/pages/js/jquery-1.9.1.js"%> type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="header">
		<!--左边的logo-->
		<div class="title">
			<img src= <%= application.getContextPath()+"/pages/img/logo.png"%> /> <span class="logotext"> 小Y </span>
		</div>
		<!--中间的菜单栏-->
		<div class="menu">
			<ul>
				<li><a href="main">主页</a></li>
				<li><a href="myConfer">我的会议</a></li>
				<li><a href="searchConfer">会议查询</a></li>
				<li><a href="conferApprove">会议审批</a></li>
				<li><a href="self.jsp">个人信息</a></li>
			</ul>
		</div>
		<!--右边的登入和 注册-->
		<div class="right">
			<c:if test="${empty user}">
				<span class="right_text right_text_left"> <a href="register">注册</a>
				</span>
				<span class="right_text right_text_color">
					&nbsp;&nbsp;|&nbsp;&nbsp; </span>
				<span class="right_text"> <a href="login">登入</a>
				</span>
			</c:if>
			<c:if test="${not empty user}">
				<span class="right_text"> <a href="logout">退出</a>
				</span>
				<span class="right_text right_text_color">
					&nbsp;&nbsp;|&nbsp;&nbsp; </span>
				<span class="right_text right_text_left"> 欢迎 ${user.username }
				</span>
			</c:if>
		</div>
	</div>