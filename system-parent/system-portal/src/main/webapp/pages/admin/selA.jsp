<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp" />
<div style="padding: 15px;">
	<!--查找待审核-->
	<div class="selA">
		<div class="layui-item" style="padding: 20px 0px 15px 430px;">
			<div class="layui-input-inline">
				<input type="text" name="userName" id="" required
					lay-verify="required" placeholder="请输入" autocomplete="off"
					class="layui-input">
			</div>
			<button type="button" class="layui-btn">查询</button>
		</div>
		<table class="layui-table" id="table4" lay-data=""></table>
	</div>
</div>
