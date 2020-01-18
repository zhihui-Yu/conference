//<script type="text/javascript">
/* 	//获取当前页数
	function getNextOrPrev(obj) {
		//获取单前选中的节点
		var node = $(obj).parent().parent().children(".on");
		//获取当前页数
		var pages = node.children("a").html();
		//获取最后一页的页数
		var html = node.parent().children().last().prev().children("a").html();
		//在查询范围内则执行查询
		if ($(obj).html() == "Next") {
			//判断是不是 小于最大值
			if (node.children("a").html() != html) {
				//将on移到下一个
				node.next().addClass("on");
				//删除原来的on
				node.removeAttr("class");
				//查找信息
				getConfer(pages * 8, 8);
			} else {
				alert("已经到尾巴了");
			}
		} else {
			//判断是不是大于最小值
			if (node.children("a").html() != 1) {
				//将on移到上一个
				node.prev().addClass("on");
				//删除原来的on
				node.removeAttr("class");
				//查找信息
				getConfer((pages - 1 - 1) * 8, 8);
			} else {
				alert("已经到头了");
			}
		}

	}
	function getInfo(obj) {
		//清除所有on 在添加在当前选的
		$(obj).parent().parent().children(".on").each(function() {
			$(this).removeAttr("class");
		})
		$(obj).parent().addClass("on");

		var page = $(obj).html();
		//点击数字则跳转相应位置 否则
		getConfer((page - 1) * 8, 8);
	}
	function getConfer(first, last) {
		var count;
		//请求会议室数据
		$
				.ajax({
					url : "conferInfo",
					async : false,//改为同步方式
					type : "POST",
					data : {
						first : first,
						last : last
					},
					success : function(data) {
						//将json字符串转换为json数组
						var confer = JSON.parse(data);
						var str = "";
						$("#content1").html(str);
						$("#content2").html(str);
						$("#content3").html(str);
						$("#content4").html(str);
						for (var i = 0; i < confer.length; i++) {
							str = '<a id="#panel-10" href="javascript:void(0);" onclick="Jump(this);">'
									+ '<img alt="140x140" style="width:140px;height:140px" src="'+confer[i].img[0].path +'" /> '
									+ '<br />描述 '
									+ confer[i].ci.conferName
									+ '<br />可容纳人数 '
									+ confer[i].ci.peoCount
									+ '<br />价格 '
									+ confer[i].ci.price
									+ '<input type="hidden" value='+confer[i].ci.cid+' />'
									+ '</a>';
							if (i % 4 == 0) {
								$("#content1").append(str + "<br/><br/>");
							} else if (i % 4 == 1) {
								$("#content2").append(str + "<br/><br/>");
							} else if (i % 4 == 2) {
								$("#content3").append(str + "<br/><br/>");
							} else if (i % 4 == 3) {
								$("#content4").append(str + "<br/><br/>");
							}
						}
						count = confer[0].msg;
					}
				});
		return count;
	} */
	/* $(function() {
		//页面加载后显示的会议室信息
		var count = getConfer(0, 8);
		//加载后显示可点击的页数
		$("#conferSize")
				.append(
						'<li><a href="javascript:void(0);" onclick="getNextOrPrev(this);">Prev</a></li>');
		for (var i = 1; i < (count / 8 == 0 ? 1 : (count / 8) + 1); i++) {
			if (i == 1) {
				var str = '<li class="on"><a href="javascript:void(0);" onclick="getInfo(this);">'
						+ i + '</a></li>';
			} else {
				var str = '<li><a href="javascript:void(0);" onclick="getInfo(this);">'
						+ i + '</a></li>';
			}
			$("#conferSize").append(str);
		}
		$("#conferSize")
				.append(
						'<li><a href="javascript:void(0);" onclick="getNextOrPrev(this);">Next</a></li>');

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
	} */
//</script>