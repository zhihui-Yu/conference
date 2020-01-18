<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
<link rel="stylesheet" type="text/css"
	href=<%=application.getContextPath() + "/pages/css/login.css"%> />
<script type="text/javascript"
	src=<%=application.getContextPath() + "/pages/js/jquery-1.9.1.js"%>></script>
<style type="text/css">
a {
	color: white;
}

a:hover {
	color: lightgrey
}
</style>
</head>
<script type="text/javascript"
	src=<%=application.getContextPath() + "/pages/js/common.js"%>></script>
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
			<span class="right_text right_text_left"> <a href="adminLogin">管理员登入</a></span>
			<span class="right_text right_text_left"> <a href="login">用户登入</a>&nbsp;&nbsp;
			</span> <span class="right_text right_text_left"> &nbsp;&nbsp;<a
				href="main">主页</a>&nbsp;&nbsp;
			</span>
		</div>
	</div>
	<div class="form">
		<form action="toRegister" method="post">
			<div class="login">
				<div class="username">
					<img src=<%=application.getContextPath() + "/pages/img/u.jpg"%>
						class="pwdAndusrimg" /> <input type="text" name="username"
						placeholder="用户名" class="pwdAndusrtext" id="username" />
				</div>
				<div class="passowrd">
					<img src=<%=application.getContextPath() + "/pages/img/p.jpg"%>
						class="pwdAndusrimg" /> <input type="text" name="password"
						placeholder="密码" class="pwdAndusrtext" id="password" />
				</div>
				<div class="passowrd">
					<img src=<%=application.getContextPath() + "/pages/img/tel.jpg"%>
						class="pwdAndusrimg" /> <input type="text" name="tel"
						placeholder="联系电话" class="pwdAndusrtext" id="tel" />
				</div>
				<div class="passowrd">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="sex" value="0" checked>男
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="sex" value="1">女
				</div>
				<div>
					<input type="submit" value="注册 " class="sub" />
				</div>
			</div>
		</form>
	</div>