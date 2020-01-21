function showSel(obj){
	//添加数据
	$.post("selUser",{},function(data){
		var users = JSON.parse(data);
		$("#userName").html(users.user.username);
		$("#usertel").html(users.user.tel);
		$("#usermoney").html(users.user.money);
		$("#useraddress").html(users.user.address);
		$("#userbirth").html(users.user.birth);
		if(users.user.sex == 1){
			$("#usersex").html("男");
		} else if(users.user.sex == 0){
			$("#usersex").html("女");
		}
		var str = "";
		for(var i = 0; i < users.fav.length; i++){
			str += users.fav[i].fname+" &nbsp;&nbsp;&nbsp;";
		}
		$("#userfav").html(str);
		$("#usercomm").html(users.user.comm);
	});
	show(obj);
}
