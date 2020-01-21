function showConfer(obj){
	openThis(obj);
	// 获取数据 第一次显示默认八条
	getConfer(0, 8);
	// 添加页码
	display("selConferCount", "#selConferPageing", "getConfer");
}
//不查询情况下分页查找
function getConfer(pageNum, pageSize) {
	$.post("selConfer", {
		pageSize : pageSize,
		pageNum : pageNum,
		conferName:""
	}, function(data) {
		if (data != "没有相关信息") {
			insSelConfer(data);
		} else {
			alert(data);
		}
	})
}
//通过名字查询
function selConferByname() {
	var flag = getSelConfer(0, 8);
	if(flag){
		display("selConferCountByName", "#selConferPageing", "getSelConfer", $("#selConferByname")
				.val());
	}
}
//查询情况下分页查找
function getSelConfer(pageNum, pageSize) {
	var name = $("#selConferByname").val();
	if (name == "" || name == null) {
		alert("输入查询条件")
		return false;
	} else {
		$.post("selConfer", {
			conferName : name,
			pageSize : pageSize,
			pageNum : pageNum
		}, function(data) {
			if (data != "没有相关信息") {
				insSelConfer(data);
			} else {
				alert(data);
			}
		});
		return true;
	}
}
//插入数据
function insSelConfer(data) {

	// 将数据置空
	$("#selConfer_tbody").html("");
	// 类型转换
	var confer = JSON.parse(data);
	// 拼接数据
	var str ="";
	for (var i = 0; i < confer.length; i++) {
		//获取图片
		var img ="";
		for(var j = 0; j < confer[i].img.length; j++) {
			img += '<img src='+confer[i].img[j].path+' class="conferImg" />&nbsp;&nbsp;';
		}
		var button = "";
			button = "<button type='button' class='layui-btn  layui-btn-xs' onclick = 'conferChange(this,"+confer[i].ci.cid+")'>修改</button>"
					 +"<button type='button' class='layui-btn layui-btn-danger layui-btn-xs' onclick = 'delconfer("+confer[i].ci.cid+")'>删除</button>";
		str += '<tr><td>' + (parseInt(i) + 1) + '</td>' 
				+ '<td>'+ confer[i].ci.conferName + '</td>'
				+ '<td>'+ confer[i].ci.size + '</td>'
				+ '<td>'+ confer[i].ci.price + '</td>'
				+ '<td>'+ confer[i].ci.peoCount + '</td>'
				+ '<td>'+ confer[i].ci.address + '</td>'
				+ '<td>'+ confer[i].ci.people + '</td>'
				+ '<td>'+ confer[i].ci.tel + '</td>'
				+ '<td>'+ confer[i].ci.comm + '</td>'
				+ '<td>'+ img + '</td>'
				+ '<td>'+ button + '</td></tr>';
	}
	$("#selConfer_tbody").append(str);
}
//删除会议室
function delconfer(id){
	$.post("delConfer",{cid:id},function(data){
		alert(data);
		getConfer(0, 8);
		// 添加页码
		display("selConferCount", "#selConferPageing", "getConfer");
	})
}
function getHtml(obj,id){
	var trNode = $(obj).parent().parent();
	return trNode.children().eq(id).html();
	
}
//修改点击事件
function conferChange(obj,id){
	$(".layui-input-inline").each(function(){
		$(this).css("width","260px");
	})
	$("#updconferName").val(getHtml(obj,1));
	$("#updsize").val(getHtml(obj,2));
	$("#updprice").val(getHtml(obj,3));
	$("#updpeople").val(getHtml(obj,6));
	$("#updtel").val(getHtml(obj,7));
	$("#updaddress").val(getHtml(obj,5));
	$("#updcomm").html(getHtml(obj,8));
	// 弹出modal框
	layui.use([ 'layer' ], function() {
		var layer = layui.layer, $ = layui.$;
		layer.open({
			type : 1,// 类型
			area : [ '800px', '480px' ],// 定义宽和高
			title : '查看详细信息',// 题目
			shade : 0,
			shadeClose : false,// 点击遮罩层关闭
			content : $('#selconferModal')
		});
	});
	renderForm(getHtml(obj,4));
}

function getPeoNum() {
	var peoNum;
	$.ajax({
		type : "post",
		url : "selPeoNum",
		async : false,
		success : function(data) {
			peoNum = data;
		}
	})
	return peoNum;
}
//填充peoNum
function renderForm(data) {
			var peoNum = getPeoNum();
			$("#updpeoNum").html("");
			var peo = '';
			for (var j = 0; j < peoNum.length; j++) {
				peo += '<option value="'+peoNum[j].peoCount+'">'
						+ peoNum[j].peoCount + '</option>';
			}
			$("#updpeoNum").append(peo);
			//默认执行一次点击事件
			var select = 'dd[lay-value=' + data + ']';
			$('#updpeoNum').siblings("div.layui-form-select").find('dl').find(select).click();
			
			layui.use('form', function() {
				var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
				form.render();
			});
}

function updConfer(){
	var conferName = $("#updconferName").val();
	var size = $("#updsize").val();
	var price = $("#updprice").val();
	var people = $("#updpeople").val();
	var tel = $("#updtel").val();
	var address = $("#updaddress").val();
	var comm = $("#updcomm").html();
	var peoCount = $("#updpeoNum").val();
	$.post("updConfer",{
		conferName:conferName,
		size:size,
		price:price,
		people:people,
		tel:tel,
		address:address,
		comm:comm,
		peoCount:peoCount
	},function(data){
		alert(data);
		closeThis();
		getConfer(0, 8);
		// 添加页码
		display("selConferCount", "#selConferPageing", "getConfer");
	});
}