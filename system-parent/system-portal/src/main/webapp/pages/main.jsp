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
<script src=<%=jsPath + "showAllApprove.js"%>></script>
<script src=<%=jsPath + "onload.js"%>></script>
<script src=<%=jsPath + "paging.js"%>></script>
<script src=<%=jsPath + "selApp.js"%>></script>
<script src=<%=jsPath + "self.js"%>></script>
<script src=<%=jsPath + "mail.js"%>></script>
<script src=<%=jsPath + "approvePaging.js"%>></script>
<script src=<%=jsPath + "selConferInfo.js"%>></script>
<!-- datepicker -->
<%-- <link rel="stylesheet" href=<%=cssPath + "bootstrap-datepicker.css"%>>
<script src='<%=jsPath + "bootstrap-datepicker.js"%>'></script>
<script src='<%=jsPath + "bootstrap-datepicker.zh-CN.js"%>'></script> --%>
<!-- end -->

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

.on {
	color: black;
}
</style>
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
							<li><a id="#panel-2" href="javascript:void(0)"
								onclick="showAllApprove(this)" data-toggle="tab">我的会议</a></li>
							<li><a id="#panel-3" href="javascript:void(0)"
								onclick="approveInfo(this);" data-toggle="tab">会议审核</a></li>
							<li><label style="margin: 11px 0 0 700px;">欢迎&nbsp;<span
									id="uname">${users.user.username }</span></label>
							<li>
							<li class="dropdown pull-right"><a data-toggle="dropdown"
								class="dropdown-toggle">个人信息<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a id="#panel-5" href="javascript:void(0)"
										onclick="showSel(this);">查看个人信息</a></li>
									<li><a id="#panel-6" href="javascript:void(0)"
										onclick="show(this);">修改密码</a></li>
									<li><a id="#panel-7" href="javascript:void(0)"
										onclick="show(this);">充值</a></li>
									<li class="divider"></li>
									<li><a id="#panel-8" href="javascript:void(0)"
										onclick="show(this);">意见反馈</a></li>
									<li><a id="#panel-4" href="javascript:void(0)"
										onclick="showMsg(this);">收件箱</a></li>
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
											<label class="col-sm-4 control-label">地点</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="add" />
											</div>
										</div>
										<div class="form-group">
											<label for="number" class="col-sm-4 control-label">大小</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="size" />
											</div>
										</div>
										<div class="form-group">
											<label for="date" class="col-sm-4 control-label">时间</label>
											<div class="col-sm-7" >
												<input type="date" class="form-control"  id="time"/>
											</div>
										</div>
										<div class="form-group">
											<label for="people" class="col-sm-4 control-label">可容纳人数</label>
											<div class="col-sm-7">
												<select class="form-control" id="peoCount">
													<c:forEach items="${peoNum }" var="p">
														<option value="${p.peoCount }">${p.peoCount }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-4 col-sm-7">
												<button type="button" class="btn btn-default" onclick="selConferInfo(0,8)"
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
												<input type="text" class="form-control" id="name2" placeholder="会议室名称" />
											</div>
											<button type="button" class="btn btn-default" onclick="selApp('selAllApp')">查询</button>
										</form>
									</div>

								</nav>
								<table class="table">
									<thead>
										<tr>
											<th>编号</th>
											<th>申请人</th>
											<th>会议室</th>
											<th>预约时间</th>
											<th>交易金额</th>
											<th>状态</th>
											<th>操作时间</th>
											<th>处理人</th>
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="panel-2-table">

									</tbody>
								</table>
							</div>
							<div class="col-md-12 column" style="text-align: center;">
								<ul class="pagination" id="appSize">
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
												<input type="text" id="name3" class="form-control" placeholder="会议室名称" />
											</div>
											<button type="button" class="btn btn-default" onclick="selApp('selInApp')">查询订单</button>
										</form>
									</div>

								</nav>
								<table class="table">
									<thead>
										<tr>
											<th>编号</th>
											<th>申请人</th>
											<th>会议室</th>
											<th>预约时间</th>
											<th>交易金额</th>
											<th>申请状态</th>
											<th>操作时间</th>
											<th>处理人</th>
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="approveInfo">
									</tbody>
								</table>
								<div class="col-md-12 column" style="text-align: center;">
								<ul class="pagination" id="appInSize">
								</ul>
							</div>
							</div>
							<!-- 模态框    修改时间 -->
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h4 class="modal-title" id="myModalLabel">修改预约时间</h4>
										</div>
										<div class="modal-body" id="modal1">
											
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="button" class="btn btn-primary" id="updApproveTime">提交更改</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
							<!-- 模态框付款） -->
							<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h4 class="modal-title" id="myModalLabel">缴费</h4>
										</div>
										<div class="modal-body" id="modal2">
											
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="button" class="btn btn-primary" onclick="updApproveTime()">缴费</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
						</div>
						<!--选项卡4的内容 收件箱的操作-->
						<div class="tab-pane" id="panel-4" style="margin-top: 20px;">
							<div class="col-md-12 column">
								<table class="table">
									<thead>
										<tr>
											<th>编号</th>
											<th>信息</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="msgTable">
									</tbody>
								</table>
								<div class="col-md-12 column" style="text-align: center;">
								<ul class="pagination" id="msgSize">
								</ul>
							</div>
							</div>
							<!-- 模态框    回复的详细信息 -->
							<div class="modal fade" id="msgModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h4 class="modal-title" id="myModalLabel">查看详细信息</h4>
										</div>
										<div class="modal-body" id="msgModalBody">
											<h4 id="msgInfo"></h4>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="button" class="btn btn-primary" id="delMsg">删除</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
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
								
								<span id="userName"></span><br />
								<span id="usertel"></span><br />
								<span id="usermoney"></span><br />
								<span id="useraddress"></span><br />
								<span id="userbirth"></span><br />
								<span id="usersex"></span><br />
								<span id="userfav"></span><br />
								<span id="usercomm"></span><br />
								<span
									style="margin-left: 180px;"> <a id="#panel-9"
									href="javascript:void(0)" onclick="show(this);">修改 >></a>
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
							<div class="col-md-12 column">
								<input type="text" placeholder="请输入充值金额" class="form-control" id="addmoney" style="width: 260px;margin:230px 0 0 430px;"/>
								<button type="button" class="btn btn-default" onclick="submitMoney()"
									style="width: 120px; margin: 25px 0 0 460px;">充值</button>
							</div>
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
									<label>性别</label> <select id="sex" class="form-control">
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
								<div class="carousel slide" id="carousel-627584">
									<ol class="carousel-indicators">
										<li data-slide-to="0"
											data-target="#carousel-627584" id="detailOl"></li>
									</ol>
									<div class="carousel-inner">
										<div class="item active" id="detailImg">
											<img style="width: 100%; height: 400px;" />
										</div>
									</div>
									<a class="left carousel-control" href="#carousel-627584"
										data-slide="prev"> <span
										class="glyphicon glyphicon-chevron-left"></span></a> <a
										class="right carousel-control" href="#carousel-627584"
										data-slide="next"> <span
										class="glyphicon glyphicon-chevron-right"></span></a>
								</div>
							</div>
							<div class="col-md-6 column">
								<div class="col-sm-2 column"
									style="line-height: 30px; text-align: right;">
								</div>
								<div id="info" class="col-sm-10 column"
									style="line-height: 34px; text-align: left;">
								</div>
								<button type="button" class="btn btn-default"
									id="orderConfer"
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