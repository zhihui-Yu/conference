//点击页标签事件
	//主界面会议室信息分页
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