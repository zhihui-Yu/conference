function showConfer(obj){
	openThis(obj);
	// 将数据置空
	$("#selConfer_tbody").html("");
	// 获取数据 第一次显示默认八条
	getConfer(0, 8);
	// 添加页码
	display("selConferCount", "#selConferPageing", "getConfer");
}
//不查询情况下分页查找
function getConfer(pageNum, pageSize) {
	// 将数据置空
	$("#selConfer_tbody").html("");
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
			button = "<button type='button' class='layui-btn  layui-btn-xs' onclick = 'change(this,"+confer[i].ci.cid+")'>修改</button>&nbsp;"
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
				+ '<td id='+confer[i].ci.cid+'>'+ button + '</td></tr>';
	}
	$("#selConfer_tbody").append(str);
}
//获取修改事件
function change(obj,id){
	tid = $(obj).parent().attr("id");
	$("#changeBase").attr("onclick","conferChange("+tid+","+id+")");
	$("#changeImg").attr("onclick","ImgChange("+tid+","+id+");");
	// 弹出modal框
	layui.use([ 'layer' ], function() {
		var layer = layui.layer, $ = layui.$;
		layer.open({
			type : 1,// 类型
			area : [ '280px', '140px' ],// 定义宽和高
			title : '选择修改内容',// 题目
			shade : 0,
			shadeClose : false,// 点击遮罩层关闭
			content : $('#selChangeModal')
		});
	});
}
//修改图片
function ImgChange(tid,id){
	closeThis();
	$('#box').html("");
	 $("#images").val("");
	$('.button').html("");
	/*//获取img 标签的src属性的值 在img中在显示，在标签下面添加一个添加键和删除键
	var butt = '<button type="button" class="layui-btn" onclick = "delImg(this)">删除</button>&nbsp;';
	$("#"+tid).parent().children().eq(9).children("img").each(function(i,n){
		var text = '<div class="OneThird" ><img src="'+$(this).attr("src")+'" class="imgShow"/><br/>'
					+'<button type="button" class="layui-btn" onclick = "addImg(this,'+(i+1)+')">添加</button>&nbsp;'
					+butt+"</div>";
		$('.img').append(text);
	})
	//删除前面的添加键
	$('.img').children("div").each(function(i,n){
		if(i>0){
			$(this).prev().children("button").each(function(i,n){
				if(i==0){
					$(this).remove();
				}
			})
		}
	})*/
	//底部按钮
	var button = "<button type='button' class='layui-btn ' onclick = 'changeImg(this,"+tid+")'>确定</button>&nbsp;"
				+"<button type='button' class='layui-btn ' onclick = 'closeThis()'>取消</button>";
	$('.button').append(button);
	// 弹出modal框
	layui.use([ 'layer' ], function() {
		var layer = layui.layer, $ = layui.$;
		layer.open({
			type : 1,// 类型
			area : [ '450px', '400px' ],// 定义宽和高
			title : '重新上传图片内容',// 题目
			shade : 0,
			shadeClose : false,// 点击遮罩层关闭
			content : $('#selImgModal')
		});
	});
}
//提交图片
function changeImg(obj,id){
	//获取图片数据
	//提交数据内容
	var a = new FormData();
	for(var i = 0; i < $("#images")[0].files.length;i++){
		a.append("file", $("#images")[0].files[i]);
	}
    a.append("id", id);
    console.log(a);
    $.ajax({
        url:"changeImgById",
        xhrFields:{
                withCredentials:true
        },
        type: "POST",
        cache: false,
        data: a,
        processData: false,
        contentType:false,
        async: false,
        success: function (data) {
        		alert(data);
        		closeThis();
        		//刷新页面
        		getConfer(0, 8);
        		// 添加页码
        		display("selConferCount", "#selConferPageing", "getConfer");
        	}
        });
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
	var trNode = $("#"+obj).parent();
	return trNode.children().eq(id).html();
	
}
//修改点击事件
function conferChange(obj,id){
	closeThis();
	$(".layui-input-inline").each(function(){
		$(this).css("width","260px");
	})
	$("#updconferName").val(getHtml(obj,1));
	$("#updsize").val(getHtml(obj,2));
	$("#updprice").val(getHtml(obj,3));
	$("#updpeople").val(getHtml(obj,6));
	$("#updtel").val(getHtml(obj,7));
	$("#updaddress").val(getHtml(obj,5));
	$("#updcomm").val(getHtml(obj,8));
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
//修改会议室信息
function updConfer(){
	var conferName = $("#updconferName").val();
	var size = $("#updsize").val();
	var price = $("#updprice").val();
	var people = $("#updpeople").val();
	var tel = $("#updtel").val();
	var address = $("#updaddress").val();
	var comm = $("#updcomm").val();
	var peoCount = $("#updpeoNum").val();
	
	if(conferName!="" && size!="" && price!="" && people!="" && tel!="" && address!="" && comm!="" && peoCount!="" ){
		var flag = true;
		//电话的regex
		var telPattern =/^1[3456789]\d{9}$/;
		if(!telPattern.test(tel)){
			flag = false;
			alert("电话格式不正确");
		}
		//大小的正则
		var sizePattern = /^[1-9][0-9]{1,4}$/;
		if(!sizePattern.test(size)){
			flag = false;
			alert("大小在10-9999之间");
		}
		//价格的正则
		var pricePattern = /(^[1-9]\d{0,4}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
		if(!pricePattern.test(price)){
			flag = false;
			alert("价格在1.00-99999.00");
		}
		
		if(flag){
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
		
	} else {
		alert("有些地方为空,请谨慎填写");
	}
}