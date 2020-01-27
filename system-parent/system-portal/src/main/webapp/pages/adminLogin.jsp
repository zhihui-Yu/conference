<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = application.getContextPath() + "/pages";
	String cssPath = basePath + "/css/";
	String jsPath = basePath + "/js/";
	String imgPath = basePath + "/img/";
%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<title>后台登入</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href=<%=cssPath + "login.css"%> />
<script type="text/javascript" src=<%=jsPath + "login.js"%>></script>
<script type="text/javascript" src=<%=jsPath + "jquery-1.9.1.js"%>></script>
</head>

<body style="background: #393D49;">
	<div class="header">
		<!--右边的登入和 注册-->
		<div class="right">
			<span class="right_text right_text_left"> <a href="login">用户登入</a>
			</span> <span class="right_text right_text_left"> &nbsp;&nbsp;<a
				href="main">主页</a>&nbsp;&nbsp;
			</span>
		</div>
	</div>
	<div class="main">
		<div class="mainin">
			<h1>
				<span style="font-size: 35px; color: white; font-family: '微软雅黑'">后台管理</span>
			</h1>
			<form onsubmit="return false;">
				<div class="mainin1">
					<ul>
						<li><span>用户名：</span> <input id="adminname" type="text"
							id="loginName" placeholder="登录名" class="SearchKeyword"></li>
						<li><span>密码：</span> <input type="password" id="adminP"
							placeholder="密码" class="SearchKeyword2"></li>
						<li><input class="tijiao" type="button"
							onclick="adminLogin()" value="提交" /></li>
					</ul>
				</div>
			</form>
		</div>
	</div>
	<img src="images/loading.gif" id="loading"
		style="display: none; position: absolute;" />
	<div id="POPLoading" class="cssPOPLoading">
		<div style="height: 110px; border-bottom: 1px solid #9a9a9a">
			<div class="showMessge"></div>
		</div>
		<div style="line-height: 40px; font-size: 14px; letter-spacing: 1px;">
			<a onclick="puc()">确定</a>
		</div>
	</div>
	<input type="hidden" value=<%=basePath%> id="jspPath">
</body>

</html>