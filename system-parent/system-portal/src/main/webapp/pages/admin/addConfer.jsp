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

					//……

					//但是，如果你的HTML是动态生成的，自动渲染就会失效
					//因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
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
					<div class="layui-input-block">
						<input type="text" name="conferName" required
							lay-verify="required" placeholder="请输入会议室名称" autocomplete="off"
							class="layui-input" style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">会议室大小(m2)</label>
					<div class="layui-input-block">
						<input type="text" name="size" required lay-verify="required"
							placeholder="请输入会议室大小" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">可容纳人数</label>
					<div class="layui-input-block" style="width: 260px">
						<select name="peoNum" id="peoNum">
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">会议室价格 (元/天)</label>
					<div class="layui-input-block">
						<input type="text" name="price" required lay-verify="required"
							placeholder="请输入会议室价格" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">地址</label>
					<div class="layui-input-block">
						<input type="text" name="address" required lay-verify="required"
							placeholder="请输入会议室地址" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">联系人</label>
					<div class="layui-input-block">
						<input type="text" name="people" required lay-verify="required"
							placeholder="请输入联系人姓名" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">电话</label>
					<div class="layui-input-block">
						<input type="text" name="tel" required lay-verify="required"
							placeholder="请输入联系人电话" autocomplete="off" class="layui-input"
							style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">会议室图片</label>
					<div class="layui-input-block">
						<input type="text" name="file" onfocus="changeType(this)" required
							lay-verify="required" placeholder="上传会议室图片" autocomplete="off"
							class="layui-input" style="width: 260px" multiple
							accept='image/gif,image/jpeg,image/jpg,image/png'>
						<button type="button" class="layui-btn" onclick="addImg(this)">添加</button>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入内容(50字符内)" name="comm" class="layui-textarea" style="width:260px"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="submit" value="提交" class="layui-btn"
							style="width: 100px" /> <input type="reset" value="重置"
							class="layui-btn" style="width: 100px" />

					</div>
				</div>
			</form>
		</div>
	</div>
</div>