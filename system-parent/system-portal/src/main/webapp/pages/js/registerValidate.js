function check(){
	var name = $("#username").val();
	var tel = $("#tel").val();
	var pass = $("#password").val();
	var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
	var pPattern = /^.*(?=.{6,16})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
	var mPattern = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/;
	//判断表达式是不是正确
	if(uPattern.test(name)){
		if(pPattern.test(pass)){
			if(mPattern.test(tel)){
				//判断电话号码和用户名是不是重复
				$.post("checkNameAndTel",{name:name,tel:tel},function(data){
					if(data==""){
						return true;
					} else {
						alert(data);
						return false;
					}
				});
				return false;
			} else {
				alert("手机号格式不正确");
				return false;
			}
		} else {
			alert("密码:   6到16位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
			return false;
		}
	} else {
		alert("用户名:  4到16位（字母，数字，下划线，减号）");
		return false;
	}
}