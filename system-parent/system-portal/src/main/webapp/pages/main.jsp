<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = application.getContextPath()+"/pages";
	String cssPath = basePath+"/css/";
	String jsPath = basePath+"/js/";
	String imgPath = basePath+"/img/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8" />
		<title>main</title>
		<link rel="stylesheet" href=<%=cssPath + "bootstrap.min.css"%>>
		<script src='<%=jsPath + "jquery-1.9.1.js"%>'></script>
		<script src=<%=jsPath + "bootstrap.min.js"%>></script>
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
		</style>
		<script type="text/javascript">
		 $(function() {
			// 修改个人信息
			$("#submitUserInfo").click(function() {
				// 获取信息
				var username = $("#username").val();
				var birth = $("#birth").val();
				var address = $("#address").val();
				var tel = $("#tel").val();
				var sex = $("#sex").val();
				var comm = $("#comm").html();
				// 获取爱好
				var fav = [];
				$("input:checkbox:checked").each(function() {
					fav.push($(this).val());
				})
				alert(fav);
				// 提交信息
				/*
				 * $.post("changeUserInfo"),{
				 * 
				 * },function(data){ if (data == "修改成功") { window.location.href =
				 * $("#jspPath").val() + "main"; } else { alert(data); } }
				 */
			});
			// 修改密码
			$("#change").click(function() {
				$.post("changePassword", {
					old : $("#old").val(),
					new1 : $("#new1").val(),
					new2 : $("#new2").val()
				}, function(data) {
					if (data == "修改成功") {
						window.location.href = $("#jspPath").val() + "logout";
					} else {
						alert(data);
					}
				})
			})

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
	<input type="hidden" value=<%=basePath %> id="jspPath"/>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column" style="margin-top:20px">
					<div class="tabbable" id="tabs-788781">
						<ul class="nav nav-tabs">
							<li class="active">
								<a href="#panel-1" data-toggle="tab">主页 </a>
							</li>
							<c:if test="${empty user }">
								<li style="float:right">
									<a href="login" >登入</a>
								</li>
							</c:if>
							<c:if test="${!empty user }">
							<li>
								<a href="#panel-2" data-toggle="tab">我的会议</a>
							</li>
							<li>
								<a href="#panel-3" data-toggle="tab">会议审核</a>
							</li>
							<li><label style="margin: 11px 0 0 740px ;" >欢迎&nbsp;${user.username }</label><li>
							<li class="dropdown pull-right">
								<a data-toggle="dropdown" class="dropdown-toggle">个人信息<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li>
										<a id="#panel-5" href="javascript:void(0);" onclick="show(this);">查看信息</a>
									</li>
									<li>
										<a id="#panel-6" href="javascript:void(0);" onclick="show(this);">修改密码</a>
									</li>
									<li>
										<a id="#panel-7" href="javascript:void(0);" onclick="show(this);">充值</a>
									</li>
									<li class="divider"></li>
									<li>
										<a id="#panel-8" href="javascript:void(0);" onclick="show(this);">意见反馈</a>
									</li>
									<li>
										<a href="logout">退出</a>
									</li>
								</ul>
							</li>
							</c:if>
						</ul>
						<div class="tab-content" style="margin-top: 20px;">
							<!--选项卡1的内容   主页-->
							<div class="tab-pane active" id="panel-1">
								<!--幻灯片-->
								<div class="carousel slide" id="carousel-627585">
									<ol class="carousel-indicators">
										<li class="active" data-slide-to="0" data-target="#carousel-627585">
										</li>
										<li data-slide-to="1" data-target="#carousel-627585">
										</li>
										<li data-slide-to="2" data-target="#carousel-627585">
										</li>
									</ol>
									<div class="carousel-inner">
										<div class="item active">
											<img alt="" src=<%=imgPath+ "part-1.jpg"%> style="width: 100%; height: 400px;" />
											<div class="carousel-caption">
												<h4>First Thumbnail label</h4>
												<p>
													Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
												</p>
											</div>
										</div>
										<div class="item">
											<img alt="" src=<%=imgPath+ "part-2.jpg"%> style="width: 100%; height: 400px;" />
											<div class="carousel-caption">
												<h4>Second Thumbnail label</h4>
												<p>
													Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
												</p>
											</div>
										</div>
										<div class="item">
											<img alt="" src=<%=imgPath+ "part-3.jpg"%> style="width: 100%; height: 400px;" />
											<div class="carousel-caption">
												<h4>Third Thumbnail label</h4>
												<p>
													Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
												</p>
											</div>
										</div>
									</div> <a class="left carousel-control" href="#carousel-627585" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-627585" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
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
													<button type="submit" class="btn btn-default" style="width: 120px;">查找</button>
												</div>
											</div>
										</form>
									</div>
									<!--右侧图片显示 可以使用循环除以4求余的办法fore显示-->
									<div class="col-md-2 column">
										<a id="#panel-10" href="javascript:void(0);" onclick="show(this);">
											<img alt="140x140" src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
											<p>描述：
												<br /> 地点
												<br /> 价格
											</p>
										</a>

										<img alt="140x140" src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
										<p>123456</p>
									</div>
									<div class="col-md-2 column">
										<img alt="140x140" src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
										<p>123456</p>
										<img alt="140x140" src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
										<p>123456</p>
									</div>
									<div class="col-md-2 column">
										<img alt="140x140" src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
										<p>123456</p>
										<img alt="140x140" src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
										<p>123456</p>
									</div>
									<div class="col-md-2 column">
										<img alt="140x140" src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
										<p>123456</p>
										<img alt="140x140" src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
										<p>123456</p>
									</div>
									<div class="col-md-12 column" style="text-align: center;">
										<ul class="pagination">
											<!--fore循环 ajax异步传输-->
											<li>
												<a href="#">Prev</a>
											</li>
											<li>
												<a href="#">1</a>
											</li>
											<li>
												<a href="#">2</a>
											</li>
											<li>
												<a href="#">3</a>
											</li>
											<li>
												<a href="#">4</a>
											</li>
											<li>
												<a href="#">5</a>
											</li>
											<li>
												<a href="#">Next</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!--选项卡2的内容  自己的会议信息-->
							<div class="tab-pane" id="panel-2" style="margin-top: 20px;">
								<div class="col-md-12 column">
									<nav class="navbar navbar-default" role="navigation">
										<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
								<div class="col-md-12 column"  style="text-align: center;">
									<ul class="pagination">
										<!--fore循环 ajax异步传输-->
										<li>
											<a href="#">Prev</a>
										</li>
										<li>
											<a href="#">1</a>
										</li>
										<li>
											<a href="#">2</a>
										</li>
										<li>
											<a href="#">3</a>
										</li>
										<li>
											<a href="#">4</a>
										</li>
										<li>
											<a href="#">5</a>
										</li>
										<li>
											<a href="#">Next</a>
										</li>
									</ul>
								</div>
							</div>
							<!--选项卡3的内容 会议审核的信息-->
							<div class="tab-pane" id="panel-3" style="margin-top: 20px;">
								<div class="col-md-12 column">
									<nav class="navbar navbar-default" role="navigation">
										<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
												<td>
													<a id="#panel-4" href="javascript:void(0);" onclick="show(this);">修改</a>
													<a href="javascript:void(0);" onclick="del(this);">删除</a>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!--选项卡4的内容 修改会议申请的操作-->
							<div class="tab-pane" id="panel-4" style="margin-top: 20px;">
								<div class="col-md-12 column">
									修改会议申请
								</div>
							</div>
							<!--选项卡5的内容 查看自己的个人信息-->
							<div class="tab-pane" id="panel-5" style="margin: 60px 0 0 0;">
								<div class="col-md-5 column" style="line-height: 30px; text-align: right ;">
									<label>用户名 ：</label>
									<br />
									<label>联系电话 ：</label>
									<br />
									<label>账户余额 ：</label>
									<br />
									<label>居住地址 ：</label>
									<br />
									<label>生日 ：</label>
									<br />
									<label>性别 ：</label>
									<br />
									<label>爱好 ：</label>
									<br />
									<label>个人简介 ：</label>
									<br />
								</div>
								<div class="col-md-7 column" style="line-height: 34.3px; text-align: left ;">
									${ user.username }
									<br /> ${ user.tel }
									<br /> ${ user.money }&nbsp;元
									<br /> ${ user.address }
									<br /> ${ user.birth }
									<br />
									<c:if test="${ user.sex == 1 }">
										女
									</c:if>
									<c:if test="${ user.sex == 0 }">
										男
									</c:if>
									<br /> 
									<br /> ${ user.comm }
									<br />
									<span style="margin-left: 180px;">
										<a id="#panel-9" href="javascript:void(0);" onclick="show(this);">修改 >></a>
									</span>
								</div>
							</div>
							<!--选项卡6的内容 修改密码操作-->
							<div class="tab-pane" id="panel-6" style="margin-top: 20px;">
								<div class="col-md-12 column" style="padding: 60px 0 0 430px;">
									<div class="form-group">
										<label>原密码</label>
										<input type="password" class="form-control" id="old" />
									</div>
									<div class="form-group">
										<label>新密码</label>
										<input type="password" class="form-control" id="new1" />
									</div>
									<div class="form-group">
										<label>确认新密码</label>
										<input type="password" class="form-control" id="new2" />
									</div>
									<button type="button" class="btn btn-default" id="change">确认修改</button>
								</div>
							</div>
							<!--选项卡7的内容 用户充值金额界面-->
							<div class="tab-pane" id="panel-7" style="margin-top: 20px;">
								<div class="col-md-12 column">
									panel-7
								</div>
							</div>
							<!--选项卡8的内容 留言反馈界面-->
							<div class="tab-pane " id="panel-8" style="margin-top: 20px;">
								<div class="col-md-12 column" style="padding: 80px 400px 0 350px;">
									<label>反馈信息</label>
									<textarea class="form-control" placeholder="请控制在50字内..." id="backMsg" rows="5" cols="20" style="min-width: 90%;resize: none;"></textarea>
									<button type="button" class="btn btn-default" id="submitBackMsg" style="width: 120px;margin: 25px 0 0 130px;">提交</button>
								</div>
							</div>
							<!--选项卡9的内容 修改个人信息-->
							<div class="tab-pane" id="panel-9" style="margin-top: 20px;">
								<div class="col-md-12 column" style="padding: 35px 400px 15px 350px;">
									<div class="form-group">
										<label>用户名</label>
										<input type="text" id="username" class="form-control" value="${user.username }"/>
									</div>
									<div class="form-group">
										<label>性别</label>
										<select id="sex" class="form-control" autocomplete="off">
											<c:if test="${user.sex == 0 }">
												<option value="0" selected="selected">男</option>
												<option value="1">女</option>
											</c:if>
											<c:if test="${user.sex == 1 }">
												<option value="0" >男</option>
												<option value="1" selected="selected">女</option>
											</c:if>
										</select>
									</div>
									<div class="form-group">
										<label>联系电话</label>
										<input type="text" id="tel" class="form-control"  value="${user.tel }"/>
									</div>
									<div class="form-group">
										<label>居住地址</label>
										<input type="text" id="address" class="form-control"  value="${user.address }"/>
									</div>
									<div class="form-group">
										<label>生日</label>
										<input type="date" id="birth" class="form-control"  value="${user.birth }"/>
									</div>
									<div class="form-group">
										<label>爱好</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" value="running" />跑步 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" value="running" />游泳 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" value="running" />打球
									</div>
									<div class="form-group">
										<label>简介</label>
										<textarea id="comm" class="form-control" placeholder="请控制在50字内..." id="backMsg" rows="5" cols="20" style="min-width: 90%;resize: none;"> ${user.comm }</textarea>
									</div>
									<button type="button" class="btn btn-default" id="submitUserInfo" style="width: 120px;margin: 25px 0 0 130px;">确认修改</button>
								</div>
							</div>
							<!--选项卡10的内容 会议室详情页面-->
							<div class="tab-pane " id="panel-10" style="margin-top: 20px;">
								<div class="col-md-12 column" style="padding: 80px 400px 0 350px;">
									<label>会议室详情界面 准备下订单</label>
									<button type="button" class="btn btn-default" id="submitBackMsg" style="width: 120px;margin: 25px 0 0 130px;">预定</button>
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