$(function() {
		$(":submit").click(function(){
			var old = $("#oldPassword").val();
			var new1 = $("#newPassword1").val();
			var new2 = $("#newPassword2").val();
			if (new1 != new2) {
				alert("两次密码不一致");
			}else if(old == null|| old == ""){
				alert("原密码不为空")
			} else{
				$.post("changeP",{oldPassword:old,newPassword:new1},function(data){
					if(data!=null){
						if(data=="修改成功"){
							alert(data);
							parent.$(".changeP").css("display","none")
						}else{
							alert(data);
						}
					}					
				})
			}
			
			
		});
	})