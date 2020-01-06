<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp" />
<div style="padding: 15px;">
	<!--查找会议室-->
	<div class="selC">
		<div class="layui-item" style="padding: 20px 0px 15px 430px;">
			<div class="layui-input-inline">
				<input type="text" name="conferName" id="conferName" required
					lay-verify="required" placeholder="请输入会议室名" autocomplete="off"
					class="layui-input">
			</div>
			<!-- onclick="searchConfer()" -->
			<button type="button" class="layui-btn" data-type="reload">查询</button>
		</div>
		<table class="layui-table" id="table2"></table>
	</div>
</div>
<script type="text/javascript">
	// 查找用户
	layui.use('table', function() {
		var table = layui.table;
		// 第一个实例
		table.render({
			elem : '#table2',
			url : ' http://localhost/pages/admin/searchConfer',
			height : 600,
			method : 'post',
			id : 'testReload',
			page : true,
			cols : [ [
					{
						field : 'cid',
						title : 'ID',
						width : 80,
						sort : true,
						fixed : 'left',
						templet : function(d) {
							return '<span style="color: #c00;">'
									+ d.ci.cid + '</span>'
						}
					},
					{
						field : 'conferName',
						title : '会议室名',
						width : 120,
						templet : function(d) {
							return '<span style="color: #c00;">'
									+ d.ci.conferName + '</span>'
						}
					},
					{
						field : 'size',
						title : '大小',
						width : 100,
						sort : true,
						templet : function(d) {
							return '<span style="color: #c00;">' + d.ci.size
									+ '</span>'
						}
					},
					{
						field : 'price',
						title : '价格',
						width : 120,
						sort : true,
						templet : function(d) {
							return '<span style="color: #c00;">' + d.ci.price
									+ '</span>'
						}
					},
					{
						field : 'peoCount',
						title : '可容纳人数',
						width : 80,
						sort : true,
						templet : function(d) {
							return '<span style="color: #c00;">'
									+ d.ci.peoCount + '</span>'
						}
					},
					{
						field : 'address',
						title : '地址',
						width : 120,
						templet : function(d) {
							return '<span style="color: #c00;">' + d.ci.address
									+ '</span>'
						}
					},
					{
						field : 'people',
						title : '联系人',
						width : 177,
						templet : function(d) {
							return '<span style="color: #c00;">' + d.ci.people
									+ '</span>'
						}
					},
					{
						field : 'tel',
						title : '电话',
						width : 120,
						sort : true,
						templet : function(d) {
							return '<span style="color: #c00;">' + d.ci.tel
									+ '</span>'
						}
					},
					{
						field : 'status',
						title : '状态',
						width : 80,
						sort : true,
						templet : function(d) {
							return '<span style="color: #c00;">' + d.ci.status
									+ '</span>'
						}
					},
					{
						field : 'comm',
						title : '备注',
						width : 80,
						sort : true,
						templet : function(d) {
							if (d.ci.comm == null) {
								return '<span style="color: #c00;">'
										+ '</span>'
							} else {
								return '<span style="color: #c00;">'
										+ d.ci.comm + '</span>'
							}
						}
					},
					{
						field : 'img',
						title : '图片',
						width : 160,
						sort : true,
						templet : function(d) {
							var rs = ""
								for ( var i in d.img) {
									rs += '<span style="color: #c00;"><a href="/upload/'
											+ d.img[i].path+'">图片'+i+'</a>'
											+ '</span>&nbsp;&nbsp;';
								}
								return rs;
						}
					}

			] ]

		});
		var $ = layui.$, active = {
			reload : function() {
				var demoReload = $("#conferName").val();
				// 执行重载
				table.reload('testReload', {
					page : {
						curr : 1
					// 重新从第 1 页开始
					},
					where : {
						"conferName" : demoReload
					}
				}, 'data');
			}
		};
		$('.selC .layui-item .layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
</script>
