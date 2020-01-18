function selApp(url){
	//判断执行的url
	if(url == "selAllApp"){
		getAllApproveByName(0,8);
		//更换页码
		display("selAllCount","#appSize","getAllApproveByName",$("#name2").val());
	}
	if(url == "selInApp"){
		getApproveByName(0,8);
		//更换页码
		display("selCount","#appInSize","getApproveByName",$("#name3").val());
	}
}

//获取AllApproveByName的数据 并插入
function getAllApproveByName(pageNum,pageSize){
	$.post("selAllAppByName",{
		pageNum:pageNum,
		pageSize:pageSize,
		name:$("#name2").val()
	},function(data){
		insertAllApprove(data);
	})
}
//获取ApproveByName的数据
function getApproveByName(pageNum,pageSize){
	$.post("selAppByName",{
		pageNum:pageNum,
		pageSize:pageSize,
		name:$("#name3").val()
	},function(data){
		insetApprove(data);
	})
}