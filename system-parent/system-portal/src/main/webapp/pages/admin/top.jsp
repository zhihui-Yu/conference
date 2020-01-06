<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>后台</title>
<link rel="stylesheet"
	href=<%=application.getContextPath() + "/pages/css/layui.css"%>>
	
<script src=<%=application.getContextPath() + "/pages/layui.all.js"%>></script>
	<script
		src=<%=application.getContextPath() + "/pages/js/jquery-1.9.1.js"%>></script>
	<script src=<%=application.getContextPath() + "/pages/js/menu.js"%>></script>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;
		});
	</script>
</head>
