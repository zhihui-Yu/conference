<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp" />
<!--查找用户-->
<div class="selU">
	<div class="layui-item" style="padding: 20px 0px 15px 430px;">
		<div class="layui-input-inline">
			<input type="text" name="userName" id="userName" required
				lay-verify="required" placeholder="请输入用户名" autocomplete="off"
				class="layui-input">
		</div>
		<button type="button" class="layui-btn" data-type="reload">查询</button>
	</div>
	<table class="layui-table" id="table3"></table>
</div>
<script type="text/html" id="barDemo">
 	 <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript">
	// 查找用户
	layui.use('table', function() {
		var table = layui.table;
		// 第一个实例
		table.render({
			elem : '#table3',
			url : ' http://localhost/pages/admin/searchUser',
			height : 600,
			method : 'post',
			id : 'testReload',
			page : true,
			cols : [ [
					{
						field : 'uid',
						title : 'ID',
						width : 80,
						sort : true,
						fixed : 'left',
						templet : function(d) {
							return '<span style="color: #c00;">' + d.user.uid
									+ '</span>'
						}
					},
					{
						field : 'username',
						title : '用户名',
						width : 80,
						templet : function(d) {
							return '<span style="color: #c00;">'
									+ d.user.username + '</span>'
						}
					},
					{
						field : 'sex',
						title : '性别',
						width : 80,
						sort : true,
						templet : function(d) {
							if(d.user.sex == '0'){
								return '<span style="color: #c00;">' + '男'
										+ '</span>'
							}else{
								return '<span style="color: #c00;">' + '女'
								+ '</span>'
							}
						}
					},
					{
						field : 'tel',
						title : '电话',
						width : 120,
						sort : true,
						templet : function(d) {
							if (d.user.tel == null) {
								return '<span style="color: #c00;">'
										+ '</span>'
							} else {
								return '<span style="color: #c00;">'
										+ d.user.tel + '</span>'
							}
						}
					},
					{
						field : 'money',
						title : '余额',
						width : 80,
						sort : true,
						templet : function(d) {
							return '<span style="color: #c00;">' + d.user.money
									+ '</span>'
						}
					},
					{
						field : 'fname',
						title : '爱好',
						width : 120,
						templet : function(d) {
							var rs = ""
							for ( var i in d.fav) {
								rs += '<span style="color: #c00;">'
										+ d.fav[i].fname
										+ '</span>&nbsp;&nbsp;';
							}
							return rs;
						}
					},
					{
						field : 'comm',
						title : '备注',
						width : 177,
						templet : function(d) {
							if (d.user.comm == null) {
								return '<span style="color: #c00;">'
										+ '</span>'
							} else {
								return '<span style="color: #c00;">'
										+ d.user.comm + '</span>'
							}
						}
					},
					{
						field : 'status',
						title : '权限',
						width : 80,
						sort : true,
						templet : function(d) {
							if (d.user.status == 1) {
								return '<span style="color: #c00;">'
										+ '已禁用</span>'
							} else {
								return '<span style="color: #c00;">'
										+ '可使用</span>'
							}

						}
					} ] ]

		});
		var $ = layui.$, active = {
			reload : function() {
				var demoReload = $('#userName').val();
				// 执行重载
				table.reload('testReload', {
					page : {
						curr : 1
					// 重新从第 1 页开始
					},
					where : {
						"name" : demoReload
					}
				}, 'data');
			}
		};
		$('.selU .layui-item .layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
</script>
