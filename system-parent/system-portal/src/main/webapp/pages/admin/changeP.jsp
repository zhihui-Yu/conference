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
	$(function() {
		
		$(":submit").click(function(){
			var old = $("#oldPassword").val();
			var new1 = $("#newPassword1").val();
			var new2 = $("#newPassword2").val();
			if (new1 != new2) {
				alert("两次密码不一致");
			}else if(old == null|| old == ""){
				alert("原密码不为空")
			} else{
				$.post("changeP",{oldPassword:old,newPassword:new1},function(data){
					if(data!=null){
						if(data=="修改成功"){
							alert(data);
							parent.$(".changeP").css("display","none")
						}else{
							alert(data);
						}
					}					
				})
			}
			
			
		});
	})
	
</script>