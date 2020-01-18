//查询显示结果
function selConferInfo(pageNum,pageSize){
	//获取信息
	var add = $("#add").val();
	var size = $("#size").val();
	var time = $("#time").val();
	var peoCount = $("#peoCount").val();
	add == null?"":add;
	size == null?"":size;
	time == null?"":time;
	peoCount == null?"":peoCount;
	//返回查询结果的长度
	$.post("selConferInfoCount",{
		address:add,
		size: size,
		time: time,
		peoCount:peoCount
	},function(data){
		//显示页数
		inShow("#conferSize", data, "getSelConferInfo");
	});
	
	getSelConferInfo(pageNum,pageSize);
}
function getSelConferInfo(pageNum,pageSize){
	//获取信息
	var add = $("#add").val();
	var size = $("#size").val();
	var time = $("#time").val();
	var peoCount = $("#peoCount").val();
	add == null?"":add;
	size == null?"":size;
	time == null?"":time;
	peoCount == null?"":peoCount;
	//传递信息
	$.ajax({
		type:"post",
		url:"selConferInfo",
		//async:false,
		data:{
			address:add,
			size: size,
			time: time,
			peoCount:peoCount,
			pageNum:pageNum,
			pageSize:pageSize
		},
		success: function(data){
			if(data!= "无符合信息" ){
				showContent(data);
			}else{
				alert(data);
			}
		}
	});
}