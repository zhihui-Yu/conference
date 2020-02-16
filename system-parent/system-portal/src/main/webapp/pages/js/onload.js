
//充值
function submitMoney(){
	var money = $("#addmoney").val();
	if(money == ""){
		alert("不能为空")
	} else {
		$.post("addMoney",{money:money},function(data){
			alert(data);
			$("#addmoney").val("");
		});
	}
}
//time set
function timeSet(){
	$("input[type='date']").each(function(){
		if($(this).attr("id")!="birth"){
			//得到当前时间
			var date_now = new Date();
			//得到当前年份
			var year = date_now.getFullYear();
			//得到当前月份
			//注：
			//  1：js中获取Date中的month时，会比当前月份少一个月，所以这里需要先加一
			//  2: 判断当前月份是否小于10，如果小于，那么就在月份的前面加一个 '0' ， 如果大于，就显示当前月份
			var month = date_now.getMonth()+1 < 10 ? "0"+(date_now.getMonth()+1) : (date_now.getMonth()+1);
			//得到当前日子（多少号）
			var date = date_now.getDate() < 10 ? "0"+date_now.getDate() : date_now.getDate();
			//设置input标签的min属性
			$(this).attr("min",year+"-"+month+"-"+date);
			if(month == '12'){
				month = '01';
				year = year + 1;
			} else {
				month = date_now.getMonth()+2 < 10 ? "0"+(date_now.getMonth()+2) : (date_now.getMonth()+2);
			}
			//设置input标签的max属性 后一个月天
			$(this).attr("max",year+"-"+month+"-"+date);
		}
	});
}
	$(function() {
		timeSet();
		
		//页面加载后显示的会议室信息
		var count = getConfer(0, 8);
		var name = "getConfer";
		//加载后显示可点击的页数
		$("#conferSize")
				.append(
						'<li><a href="javascript:void(0);" onclick="getNextOrPrev(this,'+name+');">Prev</a></li>');
		for (var i = 1; i < (count / 8 == 0 ? 1 : (count / 8) + 1); i++) {
			if (i == 1) {
				var str = '<li class="on"><a href="javascript:void(0);" onclick="getInfo(this);">'
						+ i + '</a></li>';
			} else {
				var str = '<li><a href="javascript:void(0);" onclick="getInfo(this,'+name+');">'
						+ i + '</a></li>';
			}
			$("#conferSize").append(str);
		}
		$("#conferSize")
				.append(
						'<li><a href="javascript:void(0);" onclick="getNextOrPrev(this,'+name+');">Next</a></li>');

		//修改信息之爱好默认选定
		$("input:checkbox").each(function() {
			var value = $(this).val();
			var flag = false;
			$(".fav").each(function() {
				if (value == $(this).val()) {
					flag = true;
				}
			})
			if (flag) {
				$(this).attr("checked", "checked");
			}
		})

		// 修改个人信息
		$("#submitUserInfo").click(function() {
			// 获取信息
			var uid = $("#uid").val();
			var username = $("#username").val();
			var birth = $("#birth").val();
			var address = $("#address").val();
			var tel = $("#tel").val();
			var sex = $("#sex").val();
			var comm = $("#comm").val();
			// 获取爱好
			var fav = [];
			$("input:checkbox:checked").each(function() {
				fav.push($(this).val());
			})
			var favs = $.makeArray(fav);
			// 提交信息
			$.ajax({
				type : "post",
				url : "changeUserInfo",
				data : {
					uid : uid,
					username : username,
					birth : birth,
					address : address,
					tel : tel,
					sex : sex,
					comm : comm,
					favs : favs
				},
				traditional : true,
				success : function(data) {
					if (data == "修改成功") {
						alert(data)
						window.location.href = $("#jspPath").val() + "/main";
					} else {
						alert(data);
					}
				}
			});
		});

		// 提交反馈信息
		$("#submitBackMsg").click(function() {
			$.post("BackMsg", {
				msg : $("#backMsg").val(),
				uname : $("#uname").html()
			}, function(data) {
				if (data == "提交成功") {
					alert(data);
					window.location.href = $("#jspPath").val() + "/main";
				} else {
					alert(data);
				}
			});
		});
		// 修改密码
		$("#change").click(function() {
			$.post("changeUserPassword", {
				old : $("#old").val(),
				new1 : $("#new1").val(),
				new2 : $("#new2").val()
			}, function(data) {
				if (data == "修改成功") {
					window.location.href = $("#jspPath").val() + "/logout";
				} else {
					alert(data);
				}
			})
		});

		// 初始时候给某些标签一些样式或属性
		$(" :password").css("width", "260px");
	});
	// 标签跳转
	function show(obj) {
		$("li").each(function(i, e) {
			if ($(e).hasClass("active")) {
				$(e).removeAttr("class");
			}
		})
		var href = $(obj).attr("id");
		$(".tab-pane").each(function(i, e) {
			var flag = $(e).hasClass("active");
			if (flag) {
				$(e).removeAttr("class", "active");
				$(e).addClass("tab-pane");
			}
		})
		$("" + href).addClass("active");
	}