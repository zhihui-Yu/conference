<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = application.getContextPath() + "/pages";
	String cssPath = basePath + "/css/";
	String jsPath = basePath + "/js/";
	String imgPath = basePath + "/img/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<link rel="stylesheet" type="text/css" href=<%=cssPath + "login.css"%> />
<script type="text/javascript" src=<%=jsPath + "jquery-1.9.1.js"%>></script>
<script type="text/javascript" src=<%=jsPath + "common.js"%>></script>
<script type="text/javascript" src=<%=jsPath + "login.js"%>></script>
<style type="text/css">
a {
	color: white;
}

a:hover {
	color: lightgrey
}
</style>
</head>
<body style="background: #393D49;">
	<div class="header">
		<!--左边的logo-->
		<div class="title">
			<%-- 			<img src=<%=application.getContextPath() + "/pages/img/logo.png" %> /> --%>
			<span class="logotext"> 欢迎登入 </span>
		</div>
		<!--右边的登入和 注册-->
		<div class="right">
			<span class="right_text right_text_left"> <a href="adminLogin">管理员登入</a>
			</span> <span class="right_text right_text_left"> &nbsp;&nbsp;<a
				href="main">主页</a>&nbsp;&nbsp;
			</span>
		</div>
	</div>
	<div class="form">
		<form onsubmit="return false;">
			<div class="login">
				<div class="username">
					<img src=<%=application.getContextPath() + "/pages/img/u.jpg"%>
						class="pwdAndusrimg" /> <input type="text" id="username"
						placeholder="用户名" class="pwdAndusrtext" id="username" />
				</div>
				<div class="passowrd">
					<img src=<%=application.getContextPath() + "/pages/img/p.jpg"%>
						class="pwdAndusrimg" /> <input type="password" id="password"
						placeholder="密码" class="pwdAndusrtext" id="password" />
				</div>
				<div class="veri">
					<img src=<%=application.getContextPath() + "/pages/img/Vcode.jpg"%>
						class="vimg1" /> <input type="text" class="vtext" id="code"
						placeholder="验证码" /> <img src="Vcode" class="vimg2"
						onClick="this.src=this.src+'?'+Math.random()" />
				</div>
				<div class="forget">
					<div class="register">
						<span class="font"><a href="register">注册</a></span>
					</div>
				</div>

				<div>
					<button type="button" onclick="userLogin()" class="sub">登入</button>
				</div>
			</div>
		</form>
		<input type="hidden" value=<%=basePath %> id="jspPath">
	</div>
</body>
</html>