function showSelA(obj) {
	// 将数据置空
	$("#selA_tbody").html("");
	openThis(obj);
	// 获取数据 第一次显示默认八条
	getData(0, 8);
	// 添加页码
	display("selAppCount", "#selAPageing", "getData");
}
// 不查询情况下分页查找
function getData(pageNum, pageSize) {
	$.post("selApp", {
		pageSize : pageSize,
		pageNum : pageNum
	}, function(data) {
		if (data != "无申请记录") {
			insSelA(data);
		} else {
			alert(data);
		}
	})
}
// 查询情况下分页查找
function getSelData(pageNum, pageSize) {
	var name = $("#selAbyname").val();
	if (name == "" || name == null) {
		alert("输入查询条件");
		return false;
	} else {
		$.post("selAppByName", {
			name : name,
			pageSize : pageSize,
			pageNum : pageNum
		}, function(data) {
			if (data != "无申请记录") {
				insSelA(data);
			} else {
				alert(data);
			}
		});
		return true;
	}
}
// 插入数据
function insSelA(data) {

	// 类型转换
	var approves = JSON.parse(data);
	// 拼接数据
	var str = "";
	for (var i = 0; i < approves.length; i++) {
		if (approves[i].comm == null) {
			approves[i].comm = "";
		}
		var oper = '<td>'
				+ "<button type='button' class='layui-btn layui-btn-primary layui-btn-xs' onclick = 'comm("
				+ approves[i].id + ")'>备注</button>&nbsp;&nbsp;";
		;
		if (approves[i].status == "待审核") {
			oper += "<button type='button' class='layui-btn layui-btn-xs' onclick = 'approve("
					+ approves[i].id
					+ ",0)'>通过</button>&nbsp;&nbsp;"
					+ "<button type='button' class='layui-btn layui-btn-danger layui-btn-xs' onclick = 'approve("
					+ approves[i].id + ",1)'>拒绝</button>&nbsp;&nbsp;";
		} else if (approves[i].status == "待通过") {
			oper += "<button type='button' class='layui-btn layui-btn-xs' onclick = 'approve("
				+ approves[i].id
					+ ",2)'>通过</button>&nbsp;&nbsp;"
				+ "<button type='button' class='layui-btn layui-btn-danger layui-btn-xs' onclick = 'approve("
				+ approves[i].id + ",1)'>拒绝</button>&nbsp;&nbsp;";
		}
		str += '<tr><td>' + (parseInt(i) + 1) + '</td>' + '<td>'
				+ approves[i].uname + '</td>' + '<td>' + approves[i].cname
				+ '</td>' + '<td>' + approves[i].time + '</td>'
				+ '<td>' + approves[i].money + '</td>' + '<td>'
				+ approves[i].status + '</td>';
		str += oper + '</tr>';
	}
	$("#selA_tbody").append(str);

}
// 点击备注触发事件
function comm(id) {
	$("#comm").val("");
	// 弹出modal框
	layui.use([ 'layer' ], function() {
		var layer = layui.layer, $ = layui.$;
		layer.open({
			type : 1,// 类型
			area : [ '400px', '300px' ],// 定义宽和高
			title : '查看详细信息',// 题目
			shade : 0,
			shadeClose : false,// 点击遮罩层关闭
			content : $('#commModal')
		// 打开的内容
		});
	});
	$("#yesComm").attr("onclick", "setComm(" + id + ")");
}

// 确定修改
function setComm(id) {
	// 获取信息
	var comm = $("#comm").val();
	// 获取修改内容
	$.post("approveCommById", {
		id : id,
		comm : comm
	}, function(data) {
		alert(data);
		closeThis();
		getData(0, 8);
		// 添加页码
		display("selAppCount", "#selAPageing", "getData");
	})
}

// 通过时
function approve(id, type) {
	var str;
	if (type == 0) {
		str = "通过审核";
	} else if (type == 1) {
		str = "拒绝";
	} else if (type == 2) {
		str = "完成";
	}
	// 修改记录
	$.post("approveById", {
		id : id,
		status : str
	}, function(data) {
		alert(data);
		getData(0, 8);
		display("selAppCount", "#selAPageing", "getData");
	});
}
// 通过名字查询
function selAByName() {
	var flag = getSelData(0, 8);
	if (flag) {
		display("selAppByNameCount", "#selAPageing", "getSelData", $(
				"#selAbyname").val());
	}
}