function showTakeMsg(obj) {
	openThis(obj);
	// 将数据置空
	$("#takeMsg_tbody").html("");
	// 获取数据 第一次显示默认八条
	getMsg(0, 8);
	// 添加页码
	display("selMsgCount", "#takeMsgPageing", "getMsg");
}
// 不查询情况下分页查找
function getMsg(pageNum, pageSize) {
	$.post("selMsg", {
		pageSize : pageSize,
		pageNum : pageNum
	}, function(data) {
		if (data != "无反馈信息") {
			insMsg(data);
		} else {
			alert(data);
		}
	})
}
// 查询情况下分页查找
function getSelMsg(pageNum, pageSize) {
	var name = $("#takeMsgByName").val();
	if (name == "" || name == null) {
		alert("输入查询条件");
		return false;
	} else {
		$.post("selMsgByName", {
			name : name,
			pageSize : pageSize,
			pageNum : pageNum
		}, function(data) {
			if (data != "无该用户的反馈信息") {
				insMsg(data);
			} else {
				alert(data);
			}
		});
		return true;
	}
}
// 通过名字查询
function selMsgByName() {
	var flag = getSelMsg(0, 8);
	if (flag) {
		display("selMsgCountByName", "#takeMsgPageing", "getSelMsg", $(
				"#takeMsgByName").val());
	}

}
// 插入数据
function insMsg(data) {

	// 将数据置空
	$("#takeMsg_tbody").html("");
	// 类型转换
	var msg = JSON.parse(data);
	// 拼接数据
	var str = "";
	for (var i = 0; i < msg.length; i++) {
		str += '<tr><td>'
				+ (parseInt(i) + 1)
				+ '</td>'
				+ '<td>'
				+ msg[i].uname
				+ '</td>'
				+ '<td>'
				+ msg[i].usay
				+ '</td>'
				+ "<td><button type='button' class='layui-btn layui-btn-xs' onclick = 'responseUser(this,"
				+ msg[i].id + ")'>回复</button></td>" + '</tr>';
	}
	$("#takeMsg_tbody").append(str);

}
// 回复
function responseUser(obj, id) {
	$("#asay").val("");
	// 获取用户信息
	var str = $(obj).parent().prev().html();
	$("#userSay").html(str);
	// 给按钮添加事件
	$("#yes").attr("onclick", "res(" + id + ");");
	// 弹出modal框
	layui.use([ 'layer' ], function() {
		var layer = layui.layer, $ = layui.$;
		layer.open({
			type : 1,// 类型
			area : [ '400px', '300px' ],// 定义宽和高
			title : '查看详细信息',// 题目
			shade : 0,
			shadeClose : false,// 点击遮罩层关闭
			content : $('#motaikunag')
		// 打开的内容
		});
	});
}
// 确认回复
function res(id) {
	// 获取修改内容
	var asay = $("#asay").val();
	if (asay == "" || asay == null) {
		alert("不可为空");
	} else {
		// 回复
		$.post("responseUser", {
			id : id,
			asay : $("#asay").val()
		}, function(data) {
			alert(data);
			closeThis();
			getMsg(0, 8);
			// 添加页码
			display("selMsgCount", "#takeMsgPageing", "getMsg");
		})
	}
}
// 关闭窗口
function closeThis() {
	layer.closeAll();
}
