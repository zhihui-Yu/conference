<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp" />
<div style="padding: 15px;">
	<!--查找待审核-->
	<div class="updConfer">
		<div class="layui-item" style="padding: 20px 0px 15px 530px;">
			<div class="layui-input-inline">
				<input type="text" id="name" required
					lay-verify="required" placeholder="请输入" autocomplete="off"
					class="layui-input">
			</div>
			<button type="button" class="layui-btn" onclick="upd(this);">查询</button>
		</div>
		<div id="updConfer" style="padding-left: 400px;">
			<form class="layui-form" action="updConfer" method="post" >
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
							<input type="text" name="size" required lay-verify="required" id="size"
								placeholder="请输入会议室大小" autocomplete="off" class="layui-input"
								style="width: 260px">
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
							<input type="text" name="price" required lay-verify="required" id="price"
								placeholder="请输入会议室价格" autocomplete="off" class="layui-input"
								style="width: 260px">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">地址</label>
						<div class="layui-input-block">
							<input type="text" name="address" required lay-verify="required" id="address"
								placeholder="请输入会议室地址" autocomplete="off" class="layui-input"
								style="width: 260px">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">联系人</label>
						<div class="layui-input-block">
							<input type="text" name="people" required lay-verify="required" id="people"
								placeholder="请输入联系人姓名" autocomplete="off" class="layui-input"
								style="width: 260px">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">电话</label>
						<div class="layui-input-block">
							<input type="text" name="tel" required lay-verify="required" id="tel"
								placeholder="请输入联系人电话" autocomplete="off" class="layui-input"
								style="width: 260px">
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容(50字符内)" name="comm" class="layui-textarea" id="comm" style="width:260px"></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<input type="submit" value="修改" class="layui-btn" style="width: 100px" /> 
							<button onclick="delConfer()" type="button" class="layui-btn" style="width: 100px" >删除</button>
						</div>
					</div>
				</form>
		</div>
	</div>
</div>
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
	// Ajax请求删除会议室
	function delConfer() {
		var name = $("#conferName").val();
		$.post("delConfer",{name : name},
				function(data) {
					$("#updConfer").css("display","none");
					$("#name").val("");
					if (data != "") {
						// 显示信息
						alert("删除成功");
						} else {
						alert("无会议室信息");
					}
				});
	}
	//填充peoNum
	$(
			function() {
				$("#updConfer").css("display","none");
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
	function upd(obj){
		var name = $("#name").val();
		if(name == ""|| name == null){
			alert("不能为空");
		}else{
			$.post("selConfer",{ name : name},
				function(data) {
				if(data.msg=="没有相关信息"){
					alert("没有相关信息");
				}else{
					$("#updConfer").css("display","block");
					$("#conferName").val(data.ci.conferName);
					$("#size").val(data.ci.size);
					$("#price").val(data.ci.price);
					$("#people").val(data.ci.people);
					$("#tel").val(data.ci.tel);
					$("#address").val(data.ci.address);
					$("#comm").html(data.ci.comm);
					$("#status").val(data.ci.status);
				    $("#peoNum option").each(function(){ //遍历全部option
				        var txt = $(this).text(); //获取option的内容
				        if(txt == data.ci.peoCount) //如果不是“全部”
				            $(this).attr("selected","selectded")//添加到数组中
				    });	
				}
				});
		}
		}
		
</script>