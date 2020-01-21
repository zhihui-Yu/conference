<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="top.jsp" />
<script type="text/javascript">
	function getPeoNum() {
		var peoNum;
		$.ajax({
			type : "post",
			url : "selPeoNum",
			async : false,
			success : function(data) {
				peoNum = data;
			}
		})
		return peoNum;
	}
	//填充peoNum
	$(	
			function() {
				var peoNum = getPeoNum();
				var peo = '';
				for (var j = 0; j < peoNum.length; j++) {
					peo += '<option value="'+peoNum[j].peoCount+'">'
							+ peoNum[j].peoCount + '</option>';
				}
				$("#peoNum").append(peo);

				layui.use('form', function() {
					var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
					form.render();
				});
			})
</script>
<!--添加会议室-->
<div class="layui-body">
	<div style="padding: 15px;">
		<div class="addC" style="padding: 15px 20px 0 20px;">
			<form class="layui-form" action="addConfer" method="post"
				id="addConfer" enctype="multipart/form-data">
				<div class="layui-form-item">
					<label class="layui-form-label">会议室名称</label>
					<div class="layui-input-inline" style="width: 260px">
						<input type="text" name="conferName" required
							lay-verify="required" placeholder="请输入会议室名称" autocomplete="off"
							class="layui-input" style="width: 260px">
					</div>
					<label class="layui-form-label">会议室大小(m2)</label>
					<div class="layui-input-inline">
						<input type="text" name="size" required lay-verify="required"
							placeholder="请输入会议室大小" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">可容纳人数</label>
					<div class="layui-input-inline" style="width: 260px">
						<select name="peoNum" id="peoNum">
						</select>
					</div>
					<label class="layui-form-label">会议室价格 (元/天)</label>
					<div class="layui-input-inline">
						<input type="text" name="price" required lay-verify="required"
							placeholder="请输入会议室价格" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">地址</label>
					<div class="layui-input-inline" style="width: 260px">
						<input type="text" name="address" required lay-verify="required"
							placeholder="请输入会议室地址" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
					<label class="layui-form-label">联系人</label>
					<div class="layui-input-inline">
						<input type="text" name="people" required lay-verify="required"
							placeholder="请输入联系人姓名" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top: 40px;">
					<label class="layui-form-label">电话</label>
					<div class="layui-input-inline" style="width: 260px">
						<input type="text" name="tel" required lay-verify="required"
							placeholder="请输入联系人电话" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
					<label class="layui-form-label">备注</label>
					<div class="layui-input-inline">
						<textarea placeholder="请输入内容(50字符内)" name="comm"
							class="layui-textarea" style="width: 260px; resize: none;"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">会议室图片</label>
					<div class="layui-input-inline" style="width: 260px">
						<input type="text" name="file" onfocus="changeType(this)" required
							lay-verify="required" placeholder="上传会议室图片" autocomplete="off"
							class="layui-input" style="width: 260px" multiple
							accept='image/gif,image/jpeg,image/jpg,image/png'>
						<button type="button" class="layui-btn" onclick="addImg(this,1)" style="margin-top:15px;">添加</button>
					</div>
				</div>
				<div class="layui-form-item"
					style="position: absolute; bottom: 25px; left: 300px; width: 230px;">
					<input type="submit" value="提交" class="layui-btn"
						style="width: 100px" /> <input type="reset" value="重置"
						class="layui-btn" style="width: 100px" />
				</div>
			</form>
		</div>
	</div>
</div>