function showUse(obj) {
	openThis(obj);
	// 将数据置空
	$("#useConfer_tbody").html("");
	// 获取数据 第一次显示默认八条
	getUse(0, 8);
	// 添加页码
	display("selUseCount", "#useConferPageing", "getUse");
}
// 不查询情况下分页查找
function getUse(pageNum, pageSize) {
	$.post("selUse", {
		pageSize : pageSize,
		pageNum : pageNum
	}, function(data) {
		if (data != "无申请记录") {
			insSelUse(data);
		} else {
			alert(data);
		}
	})
}
// 插入数据
function insSelUse(data) {

	// 将数据置空
	$("#useConfer_tbody").html("");
	// 类型转换
	var approves = JSON.parse(data);
	// 拼接数据
	var str = "";
	for (var i = 0; i < approves.length; i++) {
		button = "<td><button type='button' class='layui-btn layui-btn-xs' onclick = 'use("
				+ approves[i].id + ",0)'>确定使用</button>&nbsp;&nbsp;</td>"
		str += '<tr><td>' + (parseInt(i) + 1) + '</td>' + '<td>'
				+ approves[i].uname + '</td>' + '<td>' + approves[i].tel
				+ '</td>' + '<td>' + approves[i].time + '</td>' + '<td>'
				+ approves[i].cname + '</td>';
		str += button + '</tr>';
	}
	$("#useConfer_tbody").append(str);

}
// 点击使用
function use(id) {
	layer.confirm("确定使用吗?", {
		btn : [ '确定', '取消' ],
		title : '提示'
	}, function() {
		$.post("approveById", {
			id : id,
			status : '使用'
		}, function(data) {
			alert(data);
			closeThis();
			getUse(0, 8);
			// 添加页码
			display("selUseCount", "#useConferPageing", "getUse");
		})
	});
}

// 通过电话号码查询
function takeByTel() {
	var tel = $("#takeByTel").val();
	if (tel == "" || tel == null) {
		alert("请输入查询条件")
	} else {
		$.post("selUseByTel", {
			tel : tel,
		}, function(data) {
			if (data != "无申请记录") {
				insSelUse(data);
			} else {
				alert(data);
			}
		})
	}
}