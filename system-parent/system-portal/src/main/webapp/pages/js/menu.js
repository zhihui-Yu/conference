function openThis(obj) {
	var id = '.' + $(obj).attr("id");
	$(id).parent().children().each(function(i, n) {
		$(n).css("display", "none");
	});
	$(id).css("display", "block");
}
/*// 添加图片
function addImg(obj, i) {
	var imgCount = parseInt(i);
	if (imgCount < 3) {
		imgCount++;
		var rs = '<div class="layui-input-inline" style="width: 260px">'
				+ '<input type="text" onfocus="changeType(this)" name="file" required lay-verify="required" placeholder="上传会议室图片" autocomplete="off" class="layui-input" />'
				+ '<button type="button" class="layui-btn" style="margin-top:15px;" onclick="addImg(this,'
				+ imgCount
				+ ')">添加</button>'
				+ '<button type="button" class="layui-btn" style="margin-top:15px;" onclick="delImg(this)">删除</button>'
				+ '</div>';
		$(obj).parent().parent().append(rs);
		$("input:text").css("width", "260px")
		$(obj).remove();
	} else {
		alert("最多三张图片")
	}
}
// 删除图片
function delImg(obj) {
	$(obj).parent().remove();
}*/
// 改变input框type 为file
function changeType(obj) {
	$(obj).attr("type", "file");
}
