
//预定会议室操作
function orderConfer(cid){
	if($("#uid").val()== "" || $("#uid").val() == null){
		alert("请先登入");
	} else {
		if($("#date").val() == ""){
			alert("请选择使用时间");
		}else{
			$("#cid").html();
			$.post("orderConfer",
					{
						id:$("#cid").html(),
						money:$("#money2").html(),
						uid:$("#uid").val(),
						date:$("#date").val(),
						cid:cid
					},function(data){
						alert(data);
						if(data == "预约成功"){
							window.location.href = $("#jspPath").val()+"/main";
						}
			})
		}
	}
}