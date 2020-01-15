<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="top.jsp" />
<div class="content">
	<!--显示查询出该用户已经存在的会议包含已经已完成的和完成的-->
	<div class="confer">
		<div class="table">
			<form class="layui-form" action="updConfer" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">会议室名称</label>
					<div class="layui-input-block">
						<input type="text" name="conferName" required id="conferName"
							lay-verify="required" placeholder="请输入会议室名称" autocomplete="off"
							class="layui-input" style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">会议室大小(m2)</label>
					<div class="layui-input-block">
						<input type="text" name="size" required lay-verify="required"
							id="size" placeholder="请输入会议室大小" autocomplete="off"
							class="layui-input" style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">可容纳人数</label>
					<div class="layui-input-inline" style="width: 260px">
						<select name="peoCount" id="peoNum" lay-search="">

						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">会议室价格 (元/天)</label>
					<div class="layui-input-block">
						<input type="text" name="price" required lay-verify="required"
							id="price" placeholder="请输入会议室价格" autocomplete="off"
							class="layui-input" style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">地址</label>
					<div class="layui-input-block">
						<input type="text" name="address" required lay-verify="required"
							id="address" placeholder="请输入会议室地址" autocomplete="off"
							class="layui-input" style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">联系人</label>
					<div class="layui-input-block">
						<input type="text" name="people" required lay-verify="required"
							id="people" placeholder="请输入联系人姓名" autocomplete="off"
							class="layui-input" style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">电话</label>
					<div class="layui-input-block">
						<input type="text" name="tel" required lay-verify="required"
							id="tel" placeholder="请输入联系人电话" autocomplete="off"
							class="layui-input" style="width: 260px">
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入内容(50字符内)" name="comm"
							class="layui-textarea" id="comm" style="width: 260px"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="submit" value="修改" class="layui-btn"
							style="width: 100px" />
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="footer">
	<div class="footer_2">
		<span> 版权所有&copy;2019-2020 &nbsp;&nbsp;LISTNER <br /> Chengyi
			University College,Jimei University
		</span>
	</div>
</div>
</body>
<script src=<%=application.getContextPath() + "/pages/layui.all.js"%>></script>
<script
	src=<%=application.getContextPath() + "/pages/js/jquery-1.9.1.js"%>></script>
<script>
	//JavaScript代码区域
	layui.use('element', function() {
		var element = layui.element;
	});
</script>
</html>
