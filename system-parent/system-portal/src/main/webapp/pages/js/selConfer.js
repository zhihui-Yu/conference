$(function() {
	$("td").css("white-space", "nowrap");
	$("td").css("text-overflow", "ellipsis");
	$("td").css("overflow", "hidden");
})

// 搜索会议室
function searchConfer() {
	var param = $("#conferName").val();
	$("#table2").empty();
	$
			.post(
					"searchConfer",
					{
						"conferName" : param
					},
					function(data) {
						if (data != "") {
							alert(data[0].msg);
							if (data[0].msg != "没有相关信息") {
								displayConfer(data);
								$("td").css("white-space", "nowrap");
								$("td").css("text-overflow", "ellipsis");
								$("td").css("overflow", "hidden");
							}else {
							alert("无会议室信息");
							}
						}
					});
}
// 获取会议室名称
function getName(obj) {
	return $(obj).parent().parent().children().eq(1).html();
}
// 在table2显示会议室信息
function displayConfer(data){
	var top = '<tr><th>ID</th><th>会议室名</th><th>大小</th><th>可容纳人数</th><th>价格</th><th>地址</th><th>联系人</th><th>电话</th><th>状态</th colspan="3"><th>操作</th></tr>';
	$("#table2").append(top);
	for (var i = 0; i < data.length; i++) {
		var result = "";
		result += "<tr>";

		result += "<td>";
		result += data[i].ci.cid;
		result += "</td>";

		result += "<td>";
		result += data[i].ci.conferName;
		result += "</td>";

		result += "<td>";
		result += data[i].ci.size;
		result += "</td>";

		result += "<td>";
		result += data[i].ci.peoCount;
		result += "</td>";

		result += "<td>";
		result += data[i].ci.price;
		result += "</td>";

		result += "<td>";
		result += data[i].ci.address;
		result += "</td>";

		result += "<td>";
		result += data[i].ci.people;
		result += "</td>";

		result += "<td>";
		result += data[i].ci.tel;
		result += "</td>";

		result += "<td>";
		result += data[i].ci.status;
		result += "</td>";

		result += "<td>";
		result += '<button onclick="selConfer(this)" type="button">详情</button> ';
		result += "</td>";

		result += "<td>";
		result += '<button type="button" onclick="descConfer(this);">修改</button>';
		result += "</td>";

		result += "<td>";
		result += '<button type="button" onclick="delConfer(this);">删除</button>';
		result += "</td>";

		result += "</tr>";
		$("#table2").append(result);
	}
}

// Ajax请求删除会议室
function delConfer(obj) {
	var name = getName(obj);
	$("#table2").empty();
	$("#table3").empty();
	$.post("delConfer",{"name" : name},
			function(data) {
				if (data != "") {
					// 显示信息
					alert("删除成功");
					// 删除完了 显示所有的会议室
					displayConfer(data);
					} else {
					alert("无会议室信息");
				}
			});
}
//跳转详细信息
function selConfer(obj) {
	var name = getName(obj);
	$("#table2").empty();
	$("#table3").empty();
	// 前端通过发送名字查找会议室详细信息
	$.post("selConfer",{"name" : name},
					function(data) {
						var top = '<tr><th>会议室名称</th><th>会议室大小</th><th>会议室可容纳人数</th><th>价格</th><th>图片</th><th colspan="2">操作</th></tr>';
						$("#table2").append(top);
						var result = "";
						result += "<tr>";

						result += "<td>";
						result += data.ci.conferName;
						result += "</td>";

						result += "<td>";
						result += data.ci.size;
						result += "</td>";

						result += "<td>";
						result += data.ci.peoCount;
						result += "</td>";

						result += "<td>";
						result += data.ci.price;
						result += "</td>";

						result += "<td>";
						for (var j = 0; j < data.img.length; j++) {
							result += data.img[j].path + "<br/>";
						}
						result += "</td>";

						result += "<td>";
						result += '<button  onclick="descConfer(this);">修改</button>';
						result += "</td>";

						result += "<td>";
						result += '<button  onclick="delConfer(this);">删除</button>';
						result += "</td>";

						result += "</tr>";
						$("#table2").append(result);
					});
}