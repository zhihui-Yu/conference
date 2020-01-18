//点击页标签事件
	//主界面会议室信息分页


	//获取当前页数
	function getNextOrPrev(obj,fun) {
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
				fun(pages * 8,8)
				//eval(fun+"("+pages * 8+","+ 8+")")
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
				fun((pages - 2) * 8,8)
				//eval(fun+"("+(pages - 1 - 1) * 8+","+ 8+")");
			} else {
				alert("已经到头了");
			}
		}
	}
	//跳转
	function getInfo(obj,fun) {
		//清除所有on 
		$(obj).parent().parent().children(".on").each(function() {
			$(this).removeAttr("class");
		})
		//在添加在当前选的
		$(obj).parent().addClass("on");
		//获取当前页数
		var page = $(obj).html();
		//点击数字则跳转相应位置 否则
		fun((page - 1) * 8, 8)
	}
	
	//分页查询 会议室信息
	function getConfer(first, last) {
		var count;
		//请求会议室数据
		$.ajax({
					url : "conferInfo",
					async : false,//改为同步方式
					type : "POST",
					data : {
						first : first,
						last : last
					},
					success : function(data) {
						showContent(data)
						var confer = JSON.parse(data);
						count = confer[0].msg;
					}
				});
		return count;
	}
	
	//给内容填充
	function showContent(data){
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
	}