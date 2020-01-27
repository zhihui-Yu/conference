//在内存中的数据拿出来显示
function readImage(fileObj, box, max) {
	if (max && fileObj.files.length > max) {
		fileObj.value = '';
		alert('文件数量超过上限,请重新选取')
	}
	var reg=/(.*)\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$/; 
	for(var j = 0; j< fileObj.files.length ; j++){
		if(!reg.test(fileObj.files[j].name))            //判断获取的是否为图片文件
		{
			fileObj.value = '';
			alert("请确保文件为图像文件");
		}
	}
	document.querySelector(box).innerHTML = '';
	var i = 0;

	var frObj = new FileReader();

	frObj.onload = function() {
		var img = document.createElement("img");
		img.src = frObj.result;
		document.querySelector(box).appendChild(img);
		if (i < fileObj.files.length) {
			i++;
			frObj.readAsDataURL(fileObj.files[i]);
		}
	}
	frObj.readAsDataURL(fileObj.files[i]);
}