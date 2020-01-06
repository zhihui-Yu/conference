/*function change(obj) {
	var id = $(obj).attr("id");
	var className = "." + id
	display();
	$(className).css("display", "block");
}
function display() {
	$(".content_right").children().each(function(i, n) {
		$(n).css("display", "none");
	})
}
// 查找用户
function searchUser() {
	var params = $("#searchUser").serialize();

	$
			.post(
					"searchUser",
					params,
					function(data) {
						user = data;
						$("#table5").empty();
						if (data != "") {
							var result = "<tr><th>用户名</th><th>密码</th><th>性别</th><th>爱好</th><th>备注</th></tr>";
							for (var i = 0; i < data.length; i++) {
								result += "<tr>";
								result += "<td>";
								result += data[i].user.username;
								result += "</td>";
								result += "<td>";
								result += data[i].user.password;
								result += "</td>";
								result += "<td>";
								result += data[i].user.sex;
								result += "</td>";
								result += "<td>";
								for (var j = 0; j < data[i].fav.length; j++) {
									result += data[i].fav[j].fname;
								}
								result += "</td>";
								result += "<td>";
								result += data[i].user.comm;
								result += "</td>";
								result += "</tr>";
								$("#table5").append(result);
							}
						} else {
							alert("无此用户");
						}
					})
}
// 添加图片
function addImg(obj) {
	$(obj)
			.parent()
			.parent()
			.after(
					'<tr>'
							+ '<th>img</th>'
							+ '<td>'
							+ '<input type="text" onfocus="changeType(this)" name="file" id="file" value="上传图片"/>'
							+ '<button type="button" onclick="addImg(this);">添加</button>'
							+ '<button type="button" onclick="delImg(this);">删除</button>'
							+ '</td>' + '</tr>');
}
// 删除图片
function delImg(obj) {
	$(obj).parent().parent().remove();
}*/
// 搜索会议室
/*
function searchConfer() {
	var param = $("#searchConfer").serialize();
	$("#table2").empty();
	$("#table3").empty();
	$
			.post(
					"searchConfer",
					param,
					function(data) {
						if (data != "") {
							alert(data[0].msg);
							if(data[0].msg!="没有相关信息"){
								var top = '<tr><th>会议室名称</th><th>会议室大小</th><th>会议室可容纳人数</th><th>价格</th><th colspan="3">操作</th></tr>';
								$("#table2").append(top);
								for (var i = 0; i < data.length; i++) {
									var result = "";
									result += "<tr>";
									
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
						} else {
							alert("无会议室信息");
						}
					})
}

// 获取会议室名称
function getName(obj) {
	return $(obj).parent().parent().children().eq(0).html();
}
// 跳转详细信息
function selConfer(obj) {
	var name = getName(obj);
	$("#table2").empty();
	$("#table3").empty();
	// 前端通过发送名字查找会议室详细信息
	$
			.post(
					"selConfer",
					{
						"name" : name
					},
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
function getPeoNum(){
	var peoNum;
	$.ajax({
		type:"post",
		contentType:"application/json; charset=utf-8",
		url:"selPeoNum",
		async:false,
		success:function(data){
			peoNum = data;
		}
	})
	return peoNum;
}
// 跳转修改页面
function descConfer(obj) {
	var name = getName(obj);
	var peoN = getPeoNum();
	$("#table2").empty();
	$("#table3").empty();
	display();
	$(".upd").css("display","block");
	$.post("selConfer", {"name":name}, function(data) {
		var top =
			'<tr>'+
			'<th>会议室名称</th>'+
			'<td>'+
				'<input type="text" name="conferName"id="conferName" value=" ' +data.ci.conferName+ ' " />'+
			'</td>'+
		'</tr>'+
		'<tr>'+
			'<th>会议室大小(m2)</th>'+
			'<td>'+
				'<input type="text" name="conferArea" id="conferArea" value="'+data.ci.size+'" />'+
			'</td>'+
		'</tr>'+
		'<tr>'+
		'	<th>会议室价格</th>'+
			'<td>'+
				' <input type="text" name="price" id="price" value="'+data.ci.price+'" />'+
			'</td>'+
		'</tr>'+
		'<tr>'+
			'<th>会议室容纳人数</th>'+
			'<td>'+
				'<select name="peoNum">'+
					'<option value="">'+data.ci.peoCount+'</option>';
//		显示其余选项
		var peo = '';
		for (var j = 0; j < peoN.length; j++){
			peo += '<option value="'+peoN[j].peoCount+'">'+peoN[j].peoCount +'</option>';
		}
		top += peo;
		top +=	'</select>'+
			'</td>'+
		'</tr>';
		$("#table3").append(top);
		for (var j = 0; j < data.img.length; j++) {
			var img = '<tr>'+
			'<th>img</th>'+
			'<td>'+
				'<input type="text" name="file" id="flie" onfocus="changeType(this)" value="'+data.img[j].path+'"/>'+
				'<button type="button" onclick="addImg(this);">添加</button>'+
			'</td>'+
		'</tr>';
			$("#table3").append(img);
		}
		$("#table3").append('<tr>'+
			'<td colspan ="2" style="text-align: center;">'+
				'<button type="button" onclick="updConfer();" >'+
				'确定修改</button>'+'</td>'+'</tr>');
	});
}

function updConfer(){
	var param = $("#updConfer").serialize();
	alert(param)
	$("#table2").empty();
	$("#table3").empty();
	display();
	$(".sel").css("display","-------");
	$.post("updConfer",param,function(data){
		alert(data[0].msg);
		var top = '<tr><th>会议室名称</th><th>会议室大小</th><th>会议室可容纳人数</th><th>价格</th><th colspan="3">操作</th></tr>';
		$("#table2").append(top);
		for (var i = 0; i < data.length; i++) {
			var result = "";
			result += "<tr>";

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
			result += '<button onclick="selConfer(this)" type="button">详情</button> ';
			result += "</td>";

			result += "<td>";
			result += '<button type="button" onclick="updConfer(this);">修改</button>';
			result += "</td>";

			result += "<td>";
			result += '<button type="button" onclick="delConfer(this);">删除</button>';
			result += "</td>";

			result += "</tr>";
			$("#table2").append(result);
		}
	})
}
*/
/*// Ajax请求删除会议室
function delConfer(obj) {
	var name = getName(obj);
	$("#table2").empty();
	$("#table3").empty();
	$
			.post(
					"delConfer",
					{
						"name" : name
					},
					function(data) {
						if (data != "") {
							// 显示信息
							alert(data[0].msg);
							// 删除完了 显示所有的会议室
							var top = '<tr><th>会议室名称</th><th>会议室大小</th><th>会议室可容纳人数</th><th>价格</th><th colspan="3">操作</th></tr>';
							$("#table2").append(top);
							for (var i = 0; i < data.length; i++) {
								var result = "";
								result += "<tr>";

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
								result += '<a onclick="selConfer()" href="#">详情</a> ';
								result += "</td>";

								result += "<td>";
								result += '<button  onclick="updConfer(this);">修改</button>';
								result += "</td>";

								result += "<td>";
								result += '<button  onclick="delConfer(this);">删除</button>';
								result += "</td>";

								result += "</tr>";
								$("#table2").append(result);
							}
						} else {
							alert("无会议室信息");
						}
					});
}*/