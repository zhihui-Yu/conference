<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp" />
<div style="padding: 15px;">
	<!--查找待审核-->
	<div class="updUser">
		<div class="layui-item" style="padding: 20px 0px 15px 430px;">
			<div class="layui-input-inline">
				<input type="text" name="userName" id="userName" required 
					lay-verify="required" placeholder="请输入" autocomplete="off"
					class="layui-input">
			</div>
			<button type="button" class="layui-btn searchU">查询</button>
		</div>
		<div id="updUser" style="padding-left: 400px;">
			<form class="layui-form" action="updUser" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">用户id</label>
					<div class="layui-input-block">
						<input type="text" name="uid" required id="uid"
							lay-verify="required" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">用户名</label>
					<div class="layui-input-block">
						<input type="text" name="username" required lay-verify="required"
							id="username" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码</label>
					<div class="layui-input-block">
						<input type="text" name="password" required lay-verify="required"
							id="password" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">性别</label>
					<div class="layui-input-block">
						<input type="text" name="sex" required lay-verify="required"
							id="sex" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">联系电话</label>
					<div class="layui-input-block">
						<input type="text" name="tel" required lay-verify="required"
							id="tel" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">账户余额</label>
					<div class="layui-input-block">
						<input type="text" name="money" required lay-verify="required"
							id="money" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<textarea name="comm" class="layui-textarea" id="comm"
							style="width: 260px"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
<!-- 						<input type="submit" value="修改" class="layui-btn"
							style="width: 100px" />
 -->						<button onclick="offUser();" type="button" class="layui-btn" id="status"
							style="width: 100px"></button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	function offUser(){
		var uid = $("#uid").val();
		$.post("offUser",{uid:uid},function(data){
			if(data!=null){
				$("#updUser").css("display","none");
				alert("禁用成功");
				$("#userName").val("");
			}
		})
	}
	function onUser(){
		var uid = $("#uid").val();
		$.post("onUser",{uid:uid},function(data){
			if(data!=null){
				$("#updUser").css("display","none");
				alert("启用成功");
				$("#userName").val("");
			}
		})
	}
	//给查询按钮绑定实践
	$(function(){
		$("#updUser").css("display","none");
		$(".searchU").click(function(){
			var username = $("#userName").val();
			if(username ==""||username==null){
				alert("请输入正确的用户名");
			}else{
				$.post("selUser",{username:username},function(data){
					if(data == null){
						alert("没有相关信息");
						$("#updUser").css("display","none");
					}else{
						$("#updUser").css("display","none");
						$("#updUser").css("display","block");
						$("#uid").val(data[0].uid);
						$("#username").val(data[0].username);
						$("#password").val(data[0].password);
						if(data[0].sex==0){
							$("#sex").val("男");
						}else{
							$("#sex").val("女");
						}
						$("#money").val(data[0].money);
						$("#tel").val(data[0].tel);
						$("#comm").html(data[0].comm);
						if(data[0].status==0){
							$("#status").attr("onclick","offUser()");
							$("#status").html("禁用");
						}else if(data[0].status==1){
							$("#status").attr("onclick","onUser()");
							$("#status").html("启用");
						}
					    
					}
				})
			}
		})
	})
</script>