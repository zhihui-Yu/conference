//管理员登入
function adminLogin(){
	$.post("toadminLogin",{
		username:$("#adminname").val(),
		password:$("#adminP").val(),
	},function (data) {
		if(data=="true"){
			//跳转页面
			window.location.href=$("#jspPath").val()+"/adminPage";
		} else {
			alert(data);
		}
	});
}
//用户登入
function userLogin(){
	$.post("tologin",{
		username:$("#username").val(),
		password:$("#password").val(),
		code:$("#code").val()
	},function (data) {
		if(data=="main"){
			//跳转页面
			window.location.href=$("#jspPath").val()+"/main";
		} else {
			alert(data);
		}
	});
}