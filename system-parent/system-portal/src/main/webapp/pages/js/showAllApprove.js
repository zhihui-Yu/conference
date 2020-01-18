
//数据填充后跳转
function showAllApprove(obj){
	//先将内容置空
	$("#panel-2-table").html("");
	getApprove(0,8);
	display("getAllCount","#appSize","getApprove");
	//调至该页面
	show(obj);
}
//显示所有会议记录
function getApprove(pageNum,pageSize){
	$.post("showAllApprove",
			{
		uname:$("#uname").html(),
		pageNum:pageNum,
		pageSize:pageSize
			},function(data){
				if(data == "无记录"){
					alert(data);
				} else {
					insertAllApprove(data);
				}
			})
}
function insertAllApprove(data){
	//将数据置空
	$("#panel-2-table").html("");
	//类型转换
	var approves = JSON.parse(data);
	//拼接数据
	var str = "<tr>";
	for(var i = 0; i < approves.length;i++){
		if(approves[i].comm == null){
			approves[i].comm ="";
		}
		if(approves[i].aname == null){
			approves[i].aname ="";
		}
		if(approves[i].status == "待缴费"){
			approves[i].status ='<a href="javascript:void(0);" onclick="showPay(); >待缴费</a>';
		}
		
		str += "<tr>"
			+'<td>'+(parseInt(i)+1)+'</td>'
			+'<td>'+approves[i].uname+'</td>'
			+'<td>'+approves[i].cname+'</td>'
			+'<td>'+approves[i].time+'</td>'
			+'<td id="needMoney">'+approves[i].money+'</td>'
			+'<td>'+approves[i].status+'</td>'
			+'<td>'+approves[i].dealtime+'</td>'
			+'<td>'+approves[i].aname+'</td>'
			+'<td>'+approves[i].comm+'</td>'
			+'<td><a href="javascript:void(0);" onclick="delRecord('+approves[i].id+')">删除记录</a>'
		+"</tr>";
	}
	$("#panel-2-table").append(str);
}
//删除记录
function delRecord(id){
	//ajax请求删除
	$.post("delApprove",{appid:id},function(data){
		if(data=="取消成功"){
			alert("删除成功");
		}
		showAllApprove();
		$("#panel-2").addClass("active");
	})
}