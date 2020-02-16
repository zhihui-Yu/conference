function showSelAD(obj) {
	openThis(obj);
	// 将数据置空
	$("#selAD_tbody").html("");
	// 获取数据 第一次显示默认八条
	getDataAD(0, 8);
	// 添加页码
	display("selAllAppCount", "#selADPageing", "getDataAD");
}
// 不查询情况下分页查找
function getDataAD(pageNum, pageSize) {
	$.post("selAllApp", {
		pageSize : pageSize,
		pageNum : pageNum
	}, function(data) {
		if (data != "无申请记录") {
			insSelAD(data);
		} else {
			alert(data);
		}
	})
}
// 查询情况下分页查找
function getSelDataAD(pageNum, pageSize) {
	var name = $("#AD_name").val();
	if (name == "" || name == null) {
		alert("输入查询条件");
		return false;
	} else {
		$.post("selAllAppByName", {
			name : name,
			pageSize : pageSize,
			pageNum : pageNum
		}, function(data) {
			if (data != "无申请记录") {
				insSelAD(data);
			} else {
				alert(data);
			}
		});
		return true;
	}
}
// 插入数据
function insSelAD(data) {

	// 将数据置空
	$("#selAD_tbody").html("");
	// 类型转换
	var approves = JSON.parse(data);
	// 拼接数据
	var str = "";
	for (var i = 0; i < approves.length; i++) {
		if (approves[i].comm == null) {
			approves[i].comm = "";
		}
		var button = "";
		if(approves[i].status!="待使用"){
			button = "<button type='button' class='layui-btn layui-btn-danger layui-btn-xs' onclick = 'del("
				+ approves[i].id + ")'>删除记录</button>";
		}
		str += '<tr><td>'
				+ (parseInt(i) + 1)
				+ '</td>'
				+ '<td>'
				+ approves[i].uname
				+ '</td>'
				+ '<td>'
				+ approves[i].cname
				+ '</td>'
				+ '<td>'
				+ approves[i].time
				+ '</td>'
				+ '<td id="needMoney">'
				+ approves[i].money
				+ '</td>'
				+ '<td>'
				+ approves[i].status
				+ '</td>'
				+ '<td>'
				+ approves[i].aname
				+ '</td>'
				+ '<td>'
				+ approves[i].comm
				+ '</td>'
				+ '<td>'
				+ button
				+ '</td>'
				+'</tr>';
	}
	$("#selAD_tbody").append(str);

}

// 通过名字查询
function selADByName() {
	var flag = getSelDataAD(0, 8);
	if (flag) {
		display("selALLAppByNameCount", "#selADPageing", "getSelDataAD", $(
				"#AD_name").val());
	}
}