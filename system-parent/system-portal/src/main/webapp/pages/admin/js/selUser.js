function showUser(obj) {
	openThis(obj);
	// 将数据置空
	$("#takeUser_tbody").html("");
	// 获取数据 第一次显示默认八条
	getUser(0, 8);
	// 添加页码
	display("selUserCount", "#takeUserPageing", "getUser");
}
// 不查询情况下分页查找
function getUser(pageNum, pageSize) {
	// 将数据置空
	$("#takeUser_tbody").html("");
	$.post("selUser", {
		pageSize : pageSize,
		pageNum : pageNum
	}, function(data) {
		if (data != "无用户") {
			insSelUser(data);
		} else {
			alert(data);
		}
	})
}
// 通过名字查询
function selUserByName() {
	var flag = getSelUser(0, 8);
	if (flag) {
		display("selUserCountByName", "#takeUserPageing", "getSelUser", $(
				"#takeUserName").val());
	}
}
// 查询情况下分页查找
function getSelUser(pageNum, pageSize) {
	var name = $("#takeUserName").val();
	if (name == "" || name == null) {
		alert("输入查询条件");
		return false;
	} else {
		$.post("selUserByName", {
			name : name,
			pageSize : pageSize,
			pageNum : pageNum
		}, function(data) {
			if (data != "无该用户") {
				insSelUser(data);
			} else {
				alert(data);
			}
		});
		return true;
	}
}
// 插入数据
function insSelUser(data) {
	// 将数据置空
	$("#takeUser_tbody").html("");
	// 类型转换
	var users = JSON.parse(data);
	// 拼接数据
	var str = "";
	for (var i = 0; i < users.length; i++) {
		// 判断性别
		if (users[i].user.sex == "0") {
			users[i].user.sex = "男";
		} else {
			users[i].user.sex = "女";
		}
		// 获取爱好
		var fav = "";
		for (var j = 0; j < users[i].fav.length; j++) {
			fav += users[i].fav[j].fname + "&nbsp;&nbsp;";
		}
		// 判断状态
		var button = "";
		if (users[i].user.status == "0") {
			button = "<button type='button' class='layui-btn layui-btn-danger layui-btn-xs' onclick = 'status("
					+ users[i].user.status
					+ ","
					+ users[i].user.uid
					+ ")'>禁用</button>";
		} else {
			button = "<button type='button' class='layui-btn layui-btn-xs' onclick = 'status("
					+ users[i].user.status
					+ ","
					+ users[i].user.uid
					+ ")'>启用</button>";
		}
		str += '<tr><td>' + (parseInt(i) + 1) + '</td>' + '<td>'
				+ users[i].user.username + '</td>' + '<td>' + users[i].user.sex
				+ '</td>' + '<td>' + users[i].user.tel + '</td>' + '<td>'
				+ users[i].user.birth + '</td>' + '<td>'
				+ users[i].user.address + '</td>' + '<td>' + fav + '</td>'
				+ '<td>' + users[i].user.comm + '</td>' + '<td>'
				+ users[i].user.money + '</td>' + '<td>' + button
				+ '</td></tr>';
	}
	$("#takeUser_tbody").append(str);
}
function status(status, id) {
	var url;
	if (status == 0) {
		url = 'offUser';
	} else {
		url = 'onUser';
	}
	$.post(url, {
		uid : id
	}, function(data) {
		alert(data);
		getUser(0, 8);
		display("selUserCount", "#takeUserPageing", "getUser");
	});
}