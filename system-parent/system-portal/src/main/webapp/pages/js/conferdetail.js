//跳转会议室预约界面前的准备
function Jump(obj){
	//给页面填充数据
	$.post("detailConfer",{id:$(obj).children("input").val()},function(data){
		$("#info").html("");
		if(data!="无会议室信息"){
			var confer = JSON.parse(data);
			//添加文字数据
			var str = '<span style="display:none" id="cid">'+confer.ci.cid+'</span><br/>'
					  +'<label>会议室名 ：</label><span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+confer.ci.conferName+'</span><br/>'
					  +'<label>大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;小  ：</label><span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+confer.ci.size+'</span><br/>'
					  +'<label>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格 ：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red">¥</span><span style="color:red" id="money2">'+confer.ci.price+'</span><br/>'
					  +'<label>容纳人数 ：</label><span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+confer.ci.peoCount+'</span><br/>'
					  +'<label>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址 ：</label><span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+confer.ci.address+'</span><br/>'
					  +'<label>联&nbsp;&nbsp;系&nbsp;&nbsp;人 ：</label><span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+confer.ci.people+'</span><br/>'
					  +'<label>联系电话 ：</label><span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+confer.ci.tel+'</span><br/>'
					  +'<label>简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;介 ：</label><span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+confer.ci.comm+'</span><br/>'
					  +'<label>申请时间 ：</label><span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="date" id="date"/></span><br/>'
					  +'<span style="margin-left: 320px;"> <a href="main">返回>></a></span>';
			$("#info").append(str);
			//判断图片是不是多张  不是则去掉左右移动的
			if(confer.img.length == 1){
				$("#detailOl").parent().parent().children("a").each(function(){
					$(this).css("display","none");
				})
			}
			//添加图片数据
			for(var i = 0;i < confer.img.length;i++){
				if(i < 1){
					//给一个img 赋值
					$("#detailImg").children("img").attr("src",""+confer.img[i].path);
				} else {
					//有多个情况  
					//多一个li
					$("#detailOl").parent().append('<li data-slide-to="'+i+'" data-target="#carousel-627584"></li>');
					//多一个图片有值
					$("#detailImg").parent().append('<div class="item"><img src="'+confer.img[i].path+'" style="width: 100%; height: 400px;" /></div>');
				}
			}
		} else {
			alert(data);
		}
		timeSet();
	});
	//跳转页面
	show(obj);
}