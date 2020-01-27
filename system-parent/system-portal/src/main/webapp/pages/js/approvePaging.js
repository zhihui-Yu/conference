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
	//tdTitle();
}
function inShow(id,count,name){
	$(id).html("");
	//一页不显示
	if( ((count)/8)<1 || ((count)/8)==1 ){
		
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
//解决表格超出问题
function tdTitle(){
    $('td').each(function(index,element){
        $(element).attr('title',$(element).text());
    });
};