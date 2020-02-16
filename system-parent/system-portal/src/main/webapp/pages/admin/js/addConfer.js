function addConfer() {
	var conferName = $("#conferName").val();
	var size = $("#size").val();
	var price = $("#price").val();
	var people = $("#people").val();
	var tel = $("#tel").val();
	var address = $("#address").val();
	var comm = $("#comm").val();
	var peoCount = $("#peoNum").val();
	//alert(conferName+'--'+size+'--'+price+'--'+people+'--'+tel+'--'+address+'--'+comm+'--'+peoCount);
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
		//判断是不是有文件
		if($("#file")[0].files.length==0){
			flag = false;
			alert("必须添加会议室图片");
		}
		
		if(flag){
			var confer = new FormData();
			//获取图片信息
			for(var i = 0; i < $("#file")[0].files.length;i++){
				confer.append("file", $("#file")[0].files[i]);
			}
			confer.append("conferName",conferName);
			confer.append("size",size);
			confer.append("price",price);
			confer.append("people",people);
			confer.append("tel",tel);
			confer.append("address",address);
			confer.append("comm",comm);
			confer.append("peoCount",peoCount);
			$.ajax({
				url : "addConfer",
				xhrFields : {
					withCredentials : true
				},
				type : "POST",
				cache : false,
				data : confer,
				processData : false,
				contentType : false,
				async : false,
				success : function(data) {
					if(data == ""){
						alert("添加成功");
						$("#conferName").val('');
						$("#size").val('');
						$("#price").val('');
						$("#people").val('');
						$("#tel").val('');
						$("#address").val('');
						$("#comm").val('');
						$("#peoNum").val('');
						$("#file").after($("#file").clone().val(""));   
						$("#file").remove();  
						$("#box1").html("");
					} else {
						alert(data);
					}
				}
			});
		}
	} else {
		alert("有些地方为空,请谨慎填写");
	}
}

function showAddC(obj){
	$("#conferName").val('');
	$("#size").val('');
	$("#price").val('');
	$("#people").val('');
	$("#tel").val('');
	$("#address").val('');
	$("#comm").html('');
	$("#peoNum").val('');
	$("#file").after($("#file").clone().val("")); 
	$("#file").remove(); 
	$("#box1").html("");
	openThis(obj);
}