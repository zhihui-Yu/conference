$(function() {
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
				uid : $("#uid").val()
			}, function(data) {
				alert(1111);
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