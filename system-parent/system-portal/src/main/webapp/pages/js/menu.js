function openThis(obj) {
	var id = '.' + $(obj).attr("id");
	$(id).parent().children().each(function(i, n) {
		$(n).css("display", "none");
	});
	$(id).css("display", "block");
}
// 添加图片
function addImg(obj) {
	var rs ='<div class="layui-form-item">'+
				'<div class="layui-input-block">'+
					'<input type="text" onfocus="changeType(this)" name="file" required lay-verify="required" placeholder="上传会议室图片" autocomplete="off" class="layui-input" />'+            
					'<button type="button" class="layui-btn" onclick="addImg(this)">添加</button>'+
					'<button type="button" class="layui-btn" onclick="delImg(this)">删除</button>'+
				'</div>'+
			'</div>';
	$(obj)
		.parent()
		.parent()
		.after(rs);
	$("input:text").css("width","260px")
}
// 删除图片
function delImg(obj) {
	$(obj).parent().parent().remove();
}
//改变input框type 为file
function changeType(obj){
	$(obj).attr("type","file");
}