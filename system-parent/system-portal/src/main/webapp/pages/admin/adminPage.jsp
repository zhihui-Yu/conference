<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>后台管理</title>
<link rel="stylesheet"
	href=<%=application.getContextPath() + "/pages/css/layui.css"%>>
</head>

<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">会议管理系统后台</div>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;">${admin.adminName }
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:void(0);" onclick="openThis(this)" id="changeP">修改密码</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="adminlogout">退了</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:void(0);;">会议室信息管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:void(0);" onclick="openThis(this)" id="addC">添加会议室信息</a>
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="openThis(this)" id="selC">查询会议室信息</a>
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="openThis(this)" id="updC">修改会议室信息</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:void(0);">用户管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:void(0);" onclick="openThis(this)" id="selU">查询用户</a>
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="openThis(this)" id="updU">登入权限</a>
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="openThis(this)" id="takeMsg">反馈信息</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:void(0);">会议室审核管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:void(0);" onclick="openThis(this)" id="selA">待审核</a>
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="openThis(this)"
									id="selAD">已审核</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<div>
					<!--添加会议室-->
					<iframe class="addC" style="display: none;width: 100%;height: 820px;border:none" src="addConfer.jsp"></iframe>
					<!-- 查找会议室 -->
					<iframe class="selC" style="display: none;width: 100%;height: 800px;border:none" src="selConfer.jsp"></iframe>
					<!-- 修改会议室 -->
					<iframe class="updC" style="display: none;width: 100%;height: 800px;border:none" src="updConfer.jsp"></iframe>
					<!-- 查找用户 -->
					<iframe class="selU" style="display: none;width: 100%;height: 800px;border:none" src="selUser.jsp"></iframe>
					<!-- 修改用户 -->
					<iframe class="updU" style="display: none;width: 100%;height: 800px;border:none" src="updUser.jsp"></iframe>
					<!-- 修改用户 -->
					<iframe class="takeMsg" style="display: none;width: 100%;height: 800px;border:none" src="updUser.jsp"></iframe>
					<!-- 查找待审核 -->
					<iframe class="selA" style="display: none;width: 100%;height: 800px;border:none" src="selA.jsp"></iframe>
					<!-- 查找已审核 -->
					<iframe class="selAD" style="display: none;width: 100%;height: 800px;border:none" src="selAD.jsp"></iframe>
					<!-- 修改密码 -->
					<iframe class="changeP" style="display: none;width: 100%;height: 800px;border:none" src="changeP.jsp"></iframe>
				</div>
				<div class="layui-footer">
					<!-- 底部固定区域 -->
					&nbsp;&nbsp;版权所有&nbsp;&nbsp;&copy;2019-2020 &nbsp;&nbsp;LISTNER
				</div>
			</div>
		</div>
	</div>
	<script src=<%=application.getContextPath() + "/pages/layui.all.js"%>></script>
	<script src=<%=application.getContextPath() + "/pages/js/jquery-1.9.1.js"%>></script>
	<script src=<%=application.getContextPath() + "/pages/js/menu.js"%>></script>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;
		});
	</script>
</body>
</html>