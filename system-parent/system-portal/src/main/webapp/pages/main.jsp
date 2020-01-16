<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = application.getContextPath() + "/pages";
	String cssPath = basePath + "/css/";
	String jsPath = basePath + "/js/";
	String imgPath = basePath + "/img/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<title>main</title>
<link rel="stylesheet" href=<%=cssPath + "bootstrap.min.css"%>>
<script src='<%=jsPath + "jquery-1.9.1.js"%>'></script>
<script src=<%=jsPath + "bootstrap.min.js"%>></script>
<script src=<%=jsPath + "conferdetail.js"%>></script>
<script src=<%=jsPath + "orderConfer.js"%>></script>
<script src=<%=jsPath + "approveInfo.js"%>></script>
<style type="text/css">
html {
	position: relative;
	min-height: 100%;
}

body {
	/* Margin bottom by footer height */
	margin-bottom: 60px;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	/* Set the fixed height of the footer here */
	height: 50px;
	background-color: #f5f5f5;
}
.on{
	color : black;
}
</style>
<script type="text/javascript">
	
		//获取当前页数
		function getNextOrPrev(obj){
			//获取单前选中的节点
			var node = $(obj).parent().parent().children(".on");
			//获取当前页数
			var pages = node.children("a").html();
			//获取最后一页的页数
			var html = node.parent().children().last().prev().children("a").html();
			//在查询范围内则执行查询
			if($(obj).html() == "Next"){
				//判断是不是 小于最大值
				if(node.children("a").html() !=  html){
					//将on移到下一个
					node.next().addClass("on");
					//删除原来的on
					node.removeAttr("class");
					//查找信息
					getConfer(pages*8,8);
				} else {
					alert("已经到尾巴了");
				}
			} else {
				//判断是不是大于最小值
				if(node.children("a").html() != 1){
					//将on移到上一个
					node.prev().addClass("on");
					//删除原来的on
					node.removeAttr("class");
					//查找信息
					getConfer((pages-1-1)*8,8);
				} else {
					alert("已经到头了");
				}
			}
			
		}
		function getInfo(obj){
			//清除所有on 在添加在当前选的
			$(obj).parent().parent().children(".on").each(function(){
				$(this).removeAttr("class");
			})
			$(obj).parent().addClass("on");
			
			var page = $(obj).html();
			//点击数字则跳转相应位置 否则
			getConfer((page-1)*8,8);
		}
		function getConfer(first,last){
			var count;
			//请求会议室数据
			$.ajax({ url: "conferInfo",
				async: false,//改为同步方式
				type: "POST",
				data: {first:first,last:last},
				success: function (data) {
						//将json字符串转换为json数组
						var confer = JSON.parse(data);
						var str = "";
						$("#content1").html(str);$("#content2").html(str);$("#content3").html(str);$("#content4").html(str);
						for(var i = 0; i < confer.length; i++){
							str = '<a id="#panel-10" href="javascript:void(0);" onclick="Jump(this);">'
								+'<img alt="140x140" style="width:140px;height:140px" src="'+confer[i].img[0].path +'" /> ' 
								+'<br />描述 '+ confer[i].ci.conferName
								+'<br />可容纳人数 '+ confer[i].ci.peoCount
								+'<br />价格 '+ confer[i].ci.price
								+'<input type="hidden" value='+confer[i].ci.cid+' />'
								+'</a>';
							if(i%4 == 0){
								$("#content1").append(str+"<br/><br/>");
							} else if(i%4 == 1) {
								$("#content2").append(str+"<br/><br/>");
							} else if(i%4 == 2) {
								$("#content3").append(str+"<br/><br/>");
							} else if(i%4 == 3) {
								$("#content4").append(str+"<br/><br/>");
							}
						}
						count = confer[0].msg;
					} 
				});
			return count;
		}
	$(function() {
		//页面加载后显示的会议室信息
		var count = getConfer(0,8);
		//加载后显示可点击的页数
		$("#conferSize").append('<li><a href="javascript:void(0);" onclick="getNextOrPrev(this);">Prev</a></li>');
		for(var i = 1;i < (count/8 == 0?1:(count/8)+1); i++){
			if( i == 1){
				var str = '<li class="on"><a href="javascript:void(0);" onclick="getInfo(this);">'+i+'</a></li>';
			} else {
				var str = '<li><a href="javascript:void(0);" onclick="getInfo(this);">'+i+'</a></li>';
			}
			$("#conferSize").append(str);
		}
		$("#conferSize").append('<li><a href="javascript:void(0);" onclick="getNextOrPrev(this);">Next</a></li>');
		
		
		//修改信息之爱好默认选定
		$("input:checkbox").each(function() {
			var value = $(this).val();
			var flag = false;
			$(".fav").each(function(){
				if (value == $(this).val()) {
					flag = true;
				}
			})
			if(flag){
				$(this).attr("checked", "checked");
			}
		})

		// 修改个人信息
		$("#submitUserInfo").click(function() {
			// 获取信息
			var uid = $("#uid").val();
			var username = $("#username").val();
			var birth = $("#birth").val();
			var address = $("#address").val();
			var tel = $("#tel").val();
			var sex = $("#sex").val();
			var comm = $("#comm").val();
			alert(sex);
			// 获取爱好
			var fav = [];
			$("input:checkbox:checked").each(function() {
				fav.push($(this).val());
			})
			var favs = $.makeArray(fav);
			// 提交信息
			$.ajax({
				type : "post",
				url : "changeUserInfo",
				data : {
					uid : uid,
					username : username,
					birth : birth,
					address : address,
					tel : tel,
					sex : sex,
					comm : comm,
					favs : favs
				},
				traditional : true,
				success : function(data) {
					if (data == "修改成功") {
						alert(data)
						window.location.href = $("#jspPath").val() + "/main";
					} else {
						alert(data);
					}
				}
			});
		});

		// 提交反馈信息
		$("#submitBackMsg").click(function() {
			$.post("BackMsg", {
				msg : $("#backMsg").val(),
				uid : $("#uid").val()
			}, function(data) {
				alert(1111);
				if (data == "提交成功") {
					alert(data);
					window.location.href = $("#jspPath").val() + "/main";
				} else {
					alert(data);
				}
			});
		});
		// 修改密码
		$("#change").click(function() {
			$.post("changeUserPassword", {
				old : $("#old").val(),
				new1 : $("#new1").val(),
				new2 : $("#new2").val()
			}, function(data) {
				if (data == "修改成功") {
					window.location.href = $("#jspPath").val() + "/logout";
				} else {
					alert(data);
				}
			})
		});

		// 初始时候给某些标签一些样式或属性
		$(" :password").css("width", "260px");
	});
	// 标签跳转
	function show(obj) {
		$("a").each(function(i, e) {
			if ($(e).attr("aria-expanded")) {
				$(e).attr("aria-expanded", "false");
			}
		})
		$("li").each(function(i, e) {
			if ($(e).hasClass("active")) {
				$(e).removeAttr("class");
			}
		})
		var href = $(obj).attr("id");
		$(".tab-pane").each(function(i, e) {
			var flag = $(e).hasClass("active");
			if (flag) {
				$(e).removeAttr("class", "active");
				$(e).addClass("tab-pane");
			}
		})
		$("" + href).addClass("active");
	}
</script>
</head>

<body>
	<input type="hidden" value=<%=basePath%> id="jspPath" />
	<input type="hidden" value="${users.user.uid }" id="uid" />
	<c:forEach items="${users.fav }" var="fav">
		<input type="hidden" value="${fav.fname }" class="fav" />
	</c:forEach>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column" style="margin-top: 20px">
				<div class="tabbable" id="tabs-788781">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-1" data-toggle="tab">主页
						</a></li>
						<c:if test="${empty users }">
							<li style="float: right"><a href="login">登入</a></li>
						</c:if>
						<c:if test="${!empty users }">
							<li><a id="#panel-2" href="javascript:void(0);"
										onclick="show(this);" data-toggle="tab">我的会议</a></li>
							<li><a id="#panel-3" href="javascript:void(0);"
										onclick="approveInfo(this);" data-toggle="tab">会议审核</a></li>
							<li><label style="margin: 11px 0 0 740px;">欢迎&nbsp;<span id="uname">${users.user.username }</span></label>
							<li>
							<li class="dropdown pull-right"><a data-toggle="dropdown"
								class="dropdown-toggle">个人信息<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a id="#panel-5" href="javascript:void(0);"
										onclick="show(this);">查看信息</a></li>
									<li><a id="#panel-6" href="javascript:void(0);"
										onclick="show(this);">修改密码</a></li>
									<li><a id="#panel-7" href="javascript:void(0);"
										onclick="show(this);">充值</a></li>
									<li class="divider"></li>
									<li><a id="#panel-8" href="javascript:void(0);"
										onclick="show(this);">意见反馈</a></li>
									<li><a href="logout">退出</a></li>
								</ul></li>
						</c:if>
					</ul>
					<div class="tab-content" style="margin-top: 20px;">
						<!--选项卡1的内容   主页-->
						<div class="tab-pane active" id="panel-1">
							<!--幻灯片-->
							<div class="carousel slide" id="carousel-627585">
								<ol class="carousel-indicators">
									<li class="active" data-slide-to="0"
										data-target="#carousel-627585"></li>
									<li data-slide-to="1" data-target="#carousel-627585"></li>
									<li data-slide-to="2" data-target="#carousel-627585"></li>
								</ol>
								<div class="carousel-inner">
									<div class="item active">
										<img alt="" src=<%=imgPath + "part-1.jpg"%>
											style="width: 100%; height: 400px;" />
										<div class="carousel-caption">
											<h4>First Thumbnail label</h4>
											<p>Cras justo odio, dapibus ac facilisis in, egestas eget
												quam. Donec id elit non mi porta gravida at eget metus.
												Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
										</div>
									</div>
									<div class="item">
										<img alt="" src=<%=imgPath + "part-2.jpg"%>
											style="width: 100%; height: 400px;" />
										<div class="carousel-caption">
											<h4>Second Thumbnail label</h4>
											<p>Cras justo odio, dapibus ac facilisis in, egestas eget
												quam. Donec id elit non mi porta gravida at eget metus.
												Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
										</div>
									</div>
									<div class="item">
										<img alt="" src=<%=imgPath + "part-3.jpg"%>
											style="width: 100%; height: 400px;" />
										<div class="carousel-caption">
											<h4>Third Thumbnail label</h4>
											<p>Cras justo odio, dapibus ac facilisis in, egestas eget
												quam. Donec id elit non mi porta gravida at eget metus.
												Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
										</div>
									</div>
								</div>
								<a class="left carousel-control" href="#carousel-627585"
									data-slide="prev"><span
									class="glyphicon glyphicon-chevron-left"></span></a> <a
									class="right carousel-control" href="#carousel-627585"
									data-slide="next"><span
									class="glyphicon glyphicon-chevron-right"></span></a>
							</div>
							<!--中间查询的内容-->
							<div class="row clearfix" style="margin-top: 20px;">
								<!--左侧查询-->
								<div class="col-md-4 column">
									<form class="form-horizontal" role="form">
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-4 control-label">地点</label>
											<div class="col-sm-7">
												<input type="email" class="form-control" id="inputEmail3" />
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-4 control-label">大小</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="inputPassword3" />
											</div>
										</div>
										<div class="form-group">
											<label for="time" class="col-sm-4 control-label">时间</label>
											<div class="col-sm-7">
												<input type="date" class="form-control" id="time" />
											</div>
										</div>
										<div class="form-group">
											<label for="people" class="col-sm-4 control-label">可容纳人数</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="inputPassword2" />
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-4 col-sm-7">
												<button type="submit" class="btn btn-default"
													style="width: 120px;">查找</button>
											</div>
										</div>
									</form>
								</div>
								<!--右侧图片显示 可以使用循环除以4求余的办法fore显示-->
								<div class="col-md-2 column" id="content1"></div>
								<div class="col-md-2 column" id="content2"></div>
								<div class="col-md-2 column" id="content3"></div>
								<div class="col-md-2 column" id="content4"></div>
								<div class="col-md-12 column" style="text-align: center;">
									<ul class="pagination" id="conferSize">
										<!--fore循环 ajax异步传输-->
									</ul>
								</div>
							</div>
						</div>
						<!--选项卡2的内容  自己的会议信息-->
						<div class="tab-pane" id="panel-2" style="margin-top: 20px;">
							<div class="col-md-12 column">
								<nav class="navbar navbar-default" role="navigation">
									<div class="collapse navbar-collapse"
										id="bs-example-navbar-collapse-1">
										<form class="navbar-form navbar-left" role="search">
											<div class="form-group">
												<input type="text" class="form-control" placeholder="会议室名称" />
											</div>
											<button type="submit" class="btn btn-default">查询</button>
										</form>
									</div>

								</nav>
								<table class="table">
									<thead>
										<tr>
											<th>编号</th>
											<th>申请人</th>
											<th>会议室</th>
											<th>使用时间</th>
											<th>交易金额</th>
											<th>状态</th>
											<th>处理人</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
										</tr>
										<tr class="success">
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
										</tr>
										<tr class="error">
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
										</tr>
										<tr class="warning">
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
										</tr>
										<tr class="info">
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
											<td>1</td>
											<td>TB - Monthly</td>
											<td>01/04/2012</td>
											<td>Default</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-md-12 column" style="text-align: center;">
								<ul class="pagination">
									<!--fore循环 ajax异步传输-->
									<li><a href="#">Prev</a></li>
									<li><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">Next</a></li>
								</ul>
							</div>
						</div>
						<!--选项卡3的内容 会议审核的信息-->
						<div class="tab-pane" id="panel-3" style="margin-top: 20px;">
							<div class="col-md-12 column">
								<nav class="navbar navbar-default" role="navigation">
									<div class="collapse navbar-collapse"
										id="bs-example-navbar-collapse-1">
										<form class="navbar-form navbar-left" role="search">
											<div class="form-group">
												<input type="text" class="form-control" placeholder="会议室名称" />
											</div>
											<button type="submit" class="btn btn-default">查询订单</button>
										</form>
									</div>

								</nav>
								<table class="table">
									<thead>
										<tr>
											<th>编号</th>
											<th>申请人</th>
											<th>会议室</th>
											<th>申请时间</th>
											<th>交易金额</th>
											<th>申请状态</th>
											<th>处理人</th>
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="approveInfo">
									</tbody>
								</table>
							</div>
						</div>
						<!--选项卡4的内容 修改会议申请的操作-->
						<div class="tab-pane" id="panel-4" style="margin-top: 20px;">
							<div class="col-md-12 column">修改会议申请</div>
						</div>
						<!--选项卡5的内容 查看自己的个人信息-->
						<div class="tab-pane" id="panel-5" style="margin: 60px 0 0 0;">
							<div class="col-md-5 column"
								style="line-height: 30px; text-align: right;">
								<label>用户名 ：</label> <br /> <label>联系电话 ：</label> <br /> <label>账户余额
									：</label> <br /> <label>居住地址 ：</label> <br /> <label>生日 ：</label> <br />
								<label>性别 ：</label> <br /> <label>爱好 ：</label> <br /> <label>个人简介
									：</label> <br />
							</div>
							<div class="col-md-7 column"
								style="line-height: 34.3px; text-align: left;">
								${ users.user.username } <br /> ${ users.user.tel } <br /> ${ users.user.money }&nbsp;元
								<br /> ${ users.user.address } <br /> ${ users.user.birth } <br />
								<c:if test="${ users.user.sex == 1 }">
										女
									</c:if>
								<c:if test="${ users.user.sex == 0 }">
										男
									</c:if>
								<br />
								<c:forEach items="${users.fav }" var="fav">
										${fav.fname } &nbsp;&nbsp;&nbsp;
									</c:forEach>
								<br /> ${ users.user.comm } <br /> <span
									style="margin-left: 180px;"> <a id="#panel-9"
									href="javascript:void(0);" onclick="show(this);">修改 >></a>
								</span>
							</div>
						</div>
						<!--选项卡6的内容 修改密码操作-->
						<div class="tab-pane" id="panel-6" style="margin-top: 20px;">
							<div class="col-md-12 column" style="padding: 60px 0 0 430px;">
								<div class="form-group">
									<label>原密码</label> <input type="password" class="form-control"
										id="old" />
								</div>
								<div class="form-group">
									<label>新密码</label> <input type="password" class="form-control"
										id="new1" />
								</div>
								<div class="form-group">
									<label>确认新密码</label> <input type="password"
										class="form-control" id="new2" />
								</div>
								<button type="button" class="btn btn-default" id="change">确认修改</button>
							</div>
						</div>
						<!--选项卡7的内容 用户充值金额界面-->
						<div class="tab-pane" id="panel-7" style="margin-top: 20px;">
							<div class="col-md-12 column">panel-7</div>
						</div>
						<!--选项卡8的内容 留言反馈界面-->
						<div class="tab-pane " id="panel-8" style="margin-top: 20px;">
							<div class="col-md-12 column"
								style="padding: 80px 400px 0 350px;">
								<label>反馈信息</label>
								<textarea class="form-control" placeholder="请控制在50字内..."
									id="backMsg" rows="5" cols="20"
									style="min-width: 90%; resize: none;"></textarea>
								<button type="button" class="btn btn-default" id="submitBackMsg"
									style="width: 120px; margin: 25px 0 0 130px;">提交</button>
							</div>
						</div>
						<!--选项卡9的内容 修改个人信息-->
						<div class="tab-pane" id="panel-9" style="margin-top: 20px;">
							<div class="col-md-12 column"
								style="padding: 35px 400px 15px 350px;">
								<div class="form-group">
									<label>用户名</label> <input type="text" id="username"
										class="form-control" value="${users.user.username }" />
								</div>
								<div class="form-group">
									<label>性别</label> <select id="sex" class="form-control"
										autocomplete="off">
										<c:if test="${users.user.sex == 0 }">
											<option value="0" selected="selected">男</option>
											<option value="1">女</option>
										</c:if>
										<c:if test="${users.user.sex == 1 }">
											<option value="0">男</option>
											<option value="1" selected="selected">女</option>
										</c:if>
									</select>
								</div>
								<div class="form-group">
									<label>联系电话</label> <input type="text" id="tel"
										class="form-control" value="${users.user.tel }" />
								</div>
								<div class="form-group">
									<label>居住地址</label> <input type="text" id="address"
										class="form-control" value="${users.user.address }" />
								</div>
								<div class="form-group">
									<label>生日</label> <input type="date" id="birth"
										class="form-control" value="${users.user.birth }" />
								</div>
								<div class="form-group">
									<label>爱好</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<c:forEach items="${favorite }" var="f">
										<input type="checkbox" value="${f.name }" />${f.name } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
								</div>
								<div class="form-group">
									<label>简介</label>
									<textarea id="comm" class="form-control"
										placeholder="请控制在50字内..." id="backMsg" rows="5" cols="20"
										style="min-width: 90%; resize: none;"> ${users.user.comm }</textarea>
								</div>
								<button type="button" class="btn btn-default"
									id="submitUserInfo"
									style="width: 120px; margin: 25px 0 0 130px;">确认修改</button>
							</div>
						</div>
						<!--选项卡10的内容 会议室详情页面-->
						<div class="tab-pane" id="panel-10" style="margin-top: 20px;">
							<div class="col-md-6 column">
							<!--幻灯片-->
							<div class="carousel slide" id="carousel-627585">
								<ol class="carousel-indicators">
									<li class="active" data-slide-to="0" data-target="#carousel-627584" id="detailOl"></li>
								</ol>
								<div class="carousel-inner">
									<div class="item active" id="detailImg">
										<img style="width: 100%; height: 400px;" />
										<!-- 图片上添加文字 -->
										<!-- <div class="carousel-caption">
											<h4>First Thumbnail label</h4>
											<p>Cras justo odio, dapibus ac facilisis in, egestas eget
												quam. Donec id elit non mi porta gravida at eget metus.
												Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
										</div> -->
									</div>
								</div>
								<a class="left carousel-control" href="#carousel-627584"data-slide="prev">
								<span class="glyphicon glyphicon-chevron-left"></span></a>
								<a class="right carousel-control" href="#carousel-627584" data-slide="next">
								<span class="glyphicon glyphicon-chevron-right"></span></a>
							</div>
							</div>
							<div class="col-md-6 column" >
								<div class="col-sm-2 column" style="line-height: 30px; text-align: right;">
									<!-- <br/>
									<label>会议室名 ：</label> <br /> <label>大小 ：</label> <br /> <label>价格
										：</label> <br /> <label>可容纳人数 ：</label> <br /> <label>地址 ：</label> <br />
									<label>联系人 ：</label> <br /> <label>联系电话 ：</label> <br /> <label>状态
										：</label> <br /><label>简介 :</label> <br /> -->
								</div>
								<div id="info" class="col-sm-10 column" style="line-height: 34px; text-align: left;">
									<span
										style="margin-left: 320px;"> <a href="main">返回 >></a>
									</span>
								</div>
								<button type="button" class="btn btn-default" onclick="orderConfer();"
									style="width: 120px; margin: 25px 0 0 130px;">预定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--固定底部-->
	<div class="row clearfix">
		<footer class="footer">
			<div class="container" style="text-align: center; margin-top: 15px">
				&copy;2020-01-8&nbsp;我就什么都不写</div>
		</footer>
	</div>
</body>

</html>