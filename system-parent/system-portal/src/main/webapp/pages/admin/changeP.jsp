<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%  String pages = application.getContextPath()+"/pages/"; %>
<jsp:include page="top.jsp" />
<div class="layui-item" style="padding: 300px 0px 15px 590px;">
	<div class="layui-form-item">
		<label class="layui-form-label">原密码</label>
		<div class="layui-input-block">
			<input type="text" name="oldPassword" required id="oldPassword"
				lay-verify="required" placeholder="请输入原密码" autocomplete="off"
				class="layui-input" style="width: 260px">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">新密码</label>
		<div class="layui-input-block">
			<input type="text" name="newPassword1" required id="newPassword1"
				lay-verify="required" placeholder="请输入新密码" autocomplete="off"
				class="layui-input" style="width: 260px">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">确认新密码</label>
		<div class="layui-input-block">
			<input type="text" name="newPassword2" required id="newPassword2"
				lay-verify="required" placeholder="请再次输入新密码" autocomplete="off"
				class="layui-input" style="width: 260px">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="submit" value="修改" class="layui-btn"
				style="width: 100px" /> <input type="reset" value="重置"
				class="layui-btn" style="width: 100px" />
		</div>
	</div>
</div>
<script type="text/javascript">
	
	
</script>