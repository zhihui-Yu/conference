// 查找用户
layui.use('table', function() {
	var table = layui.table;
	// 第一个实例
	table.render({
		elem : '#table3',
		url : ' http://localhost/pages/admin/searchUser',
		height : 312,
		method : 'post',
		id : 'testReload',
		page : true,
		cols : [ [
				{
					field : 'd.user.uid',
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
					field : 'd.user.username',
					title : '用户名',
					width : 80,
					templet : function(d) {
						return '<span style="color: #c00;">' + d.user.username
								+ '</span>'
					}
				},
				{
					field : 'sex',
					title : '性别',
					width : 80,
					sort : true,
					templet : function(d) {
						return '<span style="color: #c00;">' + d.user.sex
								+ '</span>'
					}
				},
				{
					field : 'fname',
					title : '爱好',
					width : 80,
					templet : function(d) {
						return '<span style="color: #c00;">' + d.fav
								+ '</span>'
					}
				},
				{
					field : 'comm',
					title : '备注',
					width : 177,
					templet : function(d) {
						return '<span style="color: #c00;">' + d.user.comm
								+ '</span>'
					}
				} ] ]

	});
	var $ = layui.$, active = {
		reload : function() {
			var demoReload = $('#userName').val();
			alert(demoReload)
			// 执行重载
			table.reload('testReload', {
				page : {
					curr : 1
				// 重新从第 1 页开始
				},
				where : {
					"name":demoReload
				}
			}, 'data');
		}
	};
	$('.selU .layui-item .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});
