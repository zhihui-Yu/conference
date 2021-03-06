//查询预约会议室信息的js
function approveInfo(obj){
	getApp(0,8)
	display("getCount","#appInSize","getApp");
	//先将内容置空
	$("#approveInfo").html("");
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
		var sb="";
		if(approves[i].status == "待审核"){
			sb ='<a href="javascript:void(0);" onclick="alter(this,'+approves[i].id+','+approves[i].usedid+')" >修改</a>';
		}
		
		str += "<tr>"
			+'<td>'+(parseInt(i)+1)+'</td>'
			+'<td>'+approves[i].uname+'</td>'
			+'<td>'+approves[i].cname+'</td>'
			+'<td>'+approves[i].time+'</td>'
			+'<td>'+approves[i].money+'</td>'
			+'<td>'+approves[i].status+'</td>'
			+'<td>'+approves[i].dealtime+'</td>'
			+'<td>'+approves[i].aname+'</td>'
			+'<td>'+approves[i].comm+'</td>'
			+'<td>'+sb
			+'&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="del(this,'+approves[i].id+','+approves[i].usedid+');">取消</a></td>'
		+"</tr>";
	}
	$("#approveInfo").append(str);
}

//修改modal
function alter(obj,id,usedid){
	//获取当前选择的框的时间
	var time = $(obj).parent().parent().children().eq(3).html();
	//调至该页面
	$("#modal1").html('<input type="hidden" id="appid" /><input type = "date" id="updTime" value = "'+time+'"/>');
	timeSet();
	$("#appid").val(id);
	$("#updApproveTime").attr("onclick","updApproveTime("+usedid+")");
	$('#myModal').modal('show');
}
//点击确认修改触发事件
function updApproveTime(usedid){

	//获取修改完的时间
	var time = $("#updTime").val();
	//请求修改
	$.ajax({
        type: "post",
        url: "updApprove",
        async:false,
        data: {appid:$("#appid").val() ,date:$("#updTime").val(),usedid:usedid},
        success: function(data){
        	alert(data);
        }
    });
	$('#myModal').modal('hide');
	approveInfo();
	$("#panel-3").addClass("active");
}	

//删除出发事件
function del(bu,obj,useid){
	//ajax请求删除
	$.post("delApprove",{
			appid:obj,
			uid: $("#uid").val(),
			money: $(bu).parent().parent().children().eq(4).html(),
			usedid: useid
		},function(data){
		alert(data);
		approveInfo();
		$("#panel-3").addClass("active");
	})
}
//触发缴费事件
function showPay(obj,id){
	//账户自己的余额
	var money = $("#money").html();
	//需要的余额
	
	$.post("payMoney",{
		needMoney:obj,
		id:id
	},function(data){
		if(data == ""){
			alert("余额不足请充值");
		} else {
			alert(data);
			getApp(0,8)
			display("getCount","#appInSize","getApp");
		}
	});
	
}