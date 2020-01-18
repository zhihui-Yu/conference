//获取总数  根据传入url得到不同数量
function getCount(url,cname) {
	var count;
	$.ajax({
		type : "post",
		url : url,
		data:{name:cname},
		async:false,
		success : function(data) {
			count = data;
		}
	});
	return count;
}
//次方法为显示页码的
//执行指定url查询count的值
//执行指定id位置添加标签
//执行指定名字的函数  显示内容
function display(url,id,name,cname){
	//传入url 获取数量
	var count = getCount(url,cname);
	//显示页码
	inShow(id,count,name);
	
}
function inShow(id,count,name){
	$(id).html("");
	//加载后显示可点击的页数
	
	alert(count)
	//一页不显示
	if(count/8 == 0){
		
	} else {
		$(id).append('<li><a href="javascript:void(0);" onclick="getNextOrPrev(this,'+name+');">Prev</a></li>');
		for (var i = 1; i < (count / 8 == 0 ? 1 : (count / 8) + 1); i++) {
			if (i == 1) {
				var str = '<li class="on"><a href="javascript:void(0);" onclick="getInfo(this,'+name+');">'
				+ i + '</a></li>';
			} else {
				var str = '<li><a href="javascript:void(0);" onclick="getInfo(this,'+name+');">'
				+ i + '</a></li>';
			}
			$(id).append(str);
		}
		$(id).append('<li><a href="javascript:void(0);" onclick="getNextOrPrev(this,'+name+');">Next</a></li>');
	}
}
