$(function() {
	//填充peoNum
	$(	
			function() {
				var peoNum = getPeoNum();
				var peo = '';
				for (var j = 0; j < peoNum.length; j++) {
					peo += '<option value="'+peoNum[j].peoCount+'">'
							+ peoNum[j].peoCount + '</option>';
				}
				$("#peoNum").append(peo);

				layui.use('form', function() {
					var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
					form.render();
				});
			})
		$("#changePass").click(function(){
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