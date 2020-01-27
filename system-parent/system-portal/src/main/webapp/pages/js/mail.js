//查询预约会议室信息的js
function showMsg(obj){
	getMsg(0,8)
	display("getMsgCount","#msgSize","getMsg");
	//调至该页面
	show(obj);
}
//获取数据 拼接数据
function getMsg(pageNum,pageSize){
	//先将内容置空
	$("#msgTable").html("");
	//获取值插入表中
	$.post("getMsg",
			{
				name:$("#uname").html(),
				pageNum:pageNum,
				pageSize:pageSize
			},function(data){
				if(data == "暂无信息"){
					alert(data);
				} else {
					insetMsg(data);
				}
			})
}
//插入预约数据在表里
function insetMsg(data){
	//将字符串转换为对象
	var msg = JSON.parse(data);
	//添加字符串拼接
	var str = "<tr>";
	for(var i = 0; i < msg.length;i++){
		str += "<tr>"
			+'<td>'+(parseInt(i)+1)+'</td>'
			+'<td id="msg'+msg[i].id+'">'+msg[i].asay+'</td>'
			+'<td>'+'<a href="javascript:void(0);" onclick="showItMsg('+msg[i].id+');">查看</a>'
			+'</td>';	 
		+"</tr>";
	}
	$("#msgTable").append(str);
}
//查看详细
function showItMsg(obj){
	var text = $("#msg"+obj).html();
	//调至该页面
	$("#msgInfo").html(text);
	$("#delMsg").attr("onclick","delMsg("+obj+")");
	$('#msgModal').modal('show');
}
function delMsg(id){
	$.post("delMsg",{id:id},function(data){
		alert(data);
		$('#msgModal').modal('hide');
		getMsg(0,8)
		display("getMsgCount","#msgSize","getMsg");
	});
}