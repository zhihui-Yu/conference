//查询预约会议室信息的js
function approveInfo(obj){
	getApp(0,8)
	display("getCount","#appInSize","getApp");
	//调至该页面
	show(obj);
}
//获取数据 拼接数据
function getApp(pageNum,pageSize){
	//获取值插入表中
	$.post("approveInfo",
			{
		uname:$("#uname").html(),
		pageNum:pageNum,
		pageSize:pageSize
			},function(data){
				if(data == "无预约信息"){
					alert(data);
				} else {
					insetApprove(data);
				}
			})
}
//插入预约数据在表里
function insetApprove(data){
	//先将内容置空
	$("#approveInfo").html("");
	//将字符串转换为对象
	var approves = JSON.parse(data);
	//添加字符串拼接
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
			+'<td><a href="javascript:void(0);" onclick="alter(this,'+approves[i].id+')">修改</a>'
			+'&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="del('+approves[i].id+');">取消</a></td>';	 
		+"</tr>";
	}
	$("#approveInfo").append(str);
}

//修改modal
function alter(obj,id){
	//获取当前选择的框的时间
	var time = $(obj).parent().parent().children().eq(3).html();
	//调至该页面
	$("#modal1").html('<input type="hidden" id="appid" /><input type = "date" id="updTime" value = "'+time+'"/>')
	$("#appid").val(id);
	$('#myModal').modal('show');
}
//点击确认修改触发事件
function updApproveTime(){

	//获取修改完的时间
	var time = $("#updTime").val();
	//请求修改
	$.ajax({
        type: "post",
        url: "updApprove",
        async:false,
        data: {appid:$("#appid").val() ,date:$("#updTime").val()},
        success: function(data){
        	alert(data);
        }
    });
	$('#myModal').modal('hide');
	approveInfo();
	$("#panel-3").addClass("active");
}	

//删除出发事件
function del(obj){
	//ajax请求删除
	$.post("delApprove",{appid:obj},function(data){
		alert(data);
		approveInfo();
		$("#panel-3").addClass("active");
	})
}
//触发缴费事件
function showPay(){
	var money = $("#money").html();
	var needMoney = $("#needMoney").html();
	if(money < needMoney){
		alert("余额不足请充值");
	} else {
		//请求支付
	}
	
}