<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = application.getContextPath() + "/pages";
	String cssPath = basePath + "/css/";
	String jsPath = basePath + "/js/";
	String imgPath = basePath + "/img/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>后台管理</title>
<link rel="stylesheet" href=<%=cssPath + "layui.css"%>>
<link rel="stylesheet" href=<%=basePath + "/admin/css/adminPage.css"%>>
<script src='<%=jsPath + "jquery-1.9.1.js"%>'></script>
<script src=<%=basePath + "/admin/js/selA.js"%>></script>
<script src=<%=basePath + "/admin/js/takeMsg.js"%>></script>
<script src=<%=basePath + "/admin/js/selAD.js"%>></script>
<script src=<%=basePath + "/admin/js/selUser.js"%>></script>
<script src=<%=basePath + "/admin/js/selConfer.js"%>></script>
<script src=<%=basePath + "/admin/js/changePassword.js"%>></script>
<script src=<%=basePath + "/admin/js/useConfer.js"%>></script>
<script src=<%=basePath + "/admin/js/addConfer.js"%>></script>
<script src=<%=basePath + "/admin/js/imgCount.js"%>></script>
<script src=<%=jsPath + "approvePaging.js"%>></script>
<script src=<%=jsPath + "menu.js"%>></script>
</head>

<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">会议管理系统后台</div>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"
					id="adminName">${admin.adminName } </a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:void(0);" onclick="openThis(this)"
								id="changeP">修改密码</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="adminlogout">注销</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:void(0);;">会议室信息管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:void(0);" onclick="showAddC(this)" id="addC">添加会议室信息</a>
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="showConfer(this)"
									id="selC">查询会议室信息</a>
							</dd>
							<!-- <dd>
								<a href="javascript:void(0);" onclick="openThis(this)"
									id="updConferImg">修改会议室图片</a>
							</dd> -->
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:void(0);">用户管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:void(0);" onclick="showUser(this)" id="selU">查询用户</a>
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="showUse(this)" id="updU">使用登记</a>
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="showTakeMsg(this)"
									id="takeMsg">反馈信息</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:void(0);">会议室审核管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:void(0);" onclick="showSelA(this)" id="selA">待审核</a>
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="showSelAD(this)"
									id="selAD">已审核</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<!--添加会议室-->
				<div class="addC"
					style="display: none; width: 100%; height: 580px; border: none; padding: 15px 20px 0 235px;">
					<form class="layui-form" id="addConfer" onsubmit="return false;">
						<div class="layui-form-item">
							<label class="layui-form-label">会议室名称</label>
							<div class="layui-input-inline" style="width: 260px">
								<input type="text" id="conferName" required
									 placeholder="请输入会议室名称" autocomplete="off"
									class="layui-input" style="width: 260px">
							</div>
							<label class="layui-form-label">会议室大小(m2)</label>
							<div class="layui-input-inline">
								<input type="text" id="size" required 
									placeholder="请输入会议室大小" autocomplete="off" class="layui-input"
									style="width: 260px">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">可容纳人数</label>
							<div class="layui-input-inline" style="width: 260px">
								<select id="peoNum">
								</select>
							</div>
							<label class="layui-form-label">会议室价格 (元/天)</label>
							<div class="layui-input-inline">
								<input type="text" id="price" required 
									placeholder="请输入会议室价格" autocomplete="off" class="layui-input"
									style="width: 260px">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">地址</label>
							<div class="layui-input-inline" style="width: 260px">
								<input type="text" id="address" required 
									placeholder="请输入会议室地址" autocomplete="off" class="layui-input"
									style="width: 260px">
							</div>
							<label class="layui-form-label">联系人</label>
							<div class="layui-input-inline">
								<input type="text" id="people" required 
									placeholder="请输入联系人姓名" autocomplete="off" class="layui-input"
									style="width: 260px">
							</div>
						</div>
						<div class="layui-form-item" style="margin-top: 40px;">
							<label class="layui-form-label">电话</label>
							<div class="layui-input-inline" style="width: 260px">
								<input type="text" id="tel" required 
									placeholder="请输入联系人电话" autocomplete="off" class="layui-input"
									style="width: 260px">
							</div>
							<label class="layui-form-label">备注</label>
							<div class="layui-input-inline">
								<textarea placeholder="请输入内容(50字符内)" id="comm"
									class="layui-textarea" style="width: 260px; resize: none;"></textarea>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">会议室图片</label> <input type="file"
								id="file" multiple="multiple"
								onchange='readImage(this,"#box1",3)' class="layui-input"
								style="width: 260px; margin: 20px 0 0 60px;"
								accept='image/gif,image/jpeg,image/jpg,image/png'>
							<div id="box1">
								<br>
							</div>
						</div>
						<div class="layui-form-item"
							style="position: absolute; bottom: 25px; left: 500px; width: 230px;">
							<button type="button" onclick="addConfer();" class="layui-btn"
								style="width: 100px" >提交</button> <input type="reset" value="重置"
								class="layui-btn" style="width: 100px" />
						</div>
					</form>
				</div>
				<!-- 查找会议室 -->
				<div class="selC"
					style="display: none; width: 100%; height: 580px; border: none">
					<div style="padding: 15px;">
						<div class="layui-item" style="padding: 20px 0px 15px 430px;">
							<div class="layui-input-inline">
								<input type="text" id="selConferByname" required
									 placeholder="请输入会议室名" autocomplete="off"
									class="layui-input">
							</div>
							<button type="button" class="layui-btn"
								onclick="selConferByname()">查询</button>
						</div>
						<table class="layui-table" lay-skin="line">
							<colgroup>
								<col width="80">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="120">
								<col width="160">
								<col width="80">
								<col width="80">
								<col width="120">
								<col width="180">
								<col width="160">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>编号</th>
									<th>会议室名</th>
									<th>大小</th>
									<th>价格(元)</th>
									<th>可容纳人数</th>
									<th>地址</th>
									<th>联系人</th>
									<th>电话</th>
									<th>简介</th>
									<th>图片</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="selConfer_tbody">
							</tbody>
						</table>
						<div class="pagingdiv">
							<ul class="pagination-digg" id="selConferPageing">
							</ul>
						</div>
						<!-- 修改图片信息 模拟框 -->
						<div id="selChangeModal" style="display: none;">
							<br /> <br /> &nbsp;&nbsp;&nbsp;&nbsp;
							<button type='button' class='layui-btn' id="changeImg">修改图片</button>
							&nbsp;&nbsp;
							<button type='button' class='layui-btn' id="changeBase">修改基本信息</button>
						</div>
						<!-- 修改图片信息 模拟框 -->
						<div id="selImgModal" style="display: none;">
							<div class="img">
								<input type="file" name="file" multiple="multiple" id="images"
									onchange='readImage(this,"#box",3)' class="layui-input"
									style="width: 260px; margin: 20px 0 0 60px;"
									accept='image/gif,image/jpeg,image/jpg,image/png'>
								<div id="box">
									<br>
								</div>
							</div>
							<div class="button"
								style="position: absolute; bottom: 25px; left: 160px; text-align: center;">
							</div>
						</div>
						<!-- 修改会议室信息 模拟框 -->
						<div id="selconferModal" style="display: none;">
							<br /> <br />
							<form class="layui-form">
								<div class="layui-form-item">
									<label class="layui-form-label">会议室名称</label>
									<div class="layui-input-inline">
										<input type="text" required id="updconferName"
											 placeholder="请输入会议室名称"
											autocomplete="off" class="layui-input" style="width: 260px">
									</div>
									<label class="layui-form-label">会议室大小(m2)</label>
									<div class="layui-input-inline">
										<input type="text" required  id="updsize"
											placeholder="请输入会议室大小" autocomplete="off" class="layui-input"
											style="width: 260px">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">可容纳人数</label>
									<div class="layui-input-inline" style="width: 260px">
										<select id="updpeoNum">
										</select>
									</div>
									<label class="layui-form-label">会议室价格 (元/天)</label>
									<div class="layui-input-inline">
										<input type="text" required 
											id="updprice" placeholder="请输入会议室价格" autocomplete="off"
											class="layui-input" style="width: 260px">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">地址</label>
									<div class="layui-input-inline">
										<input type="text" required 
											id="updaddress" placeholder="请输入会议室地址" autocomplete="off"
											class="layui-input" style="width: 260px">
									</div>
									<label class="layui-form-label">联系人</label>
									<div class="layui-input-inline">
										<input type="text" required 
											id="updpeople" placeholder="请输入联系人姓名" autocomplete="off"
											class="layui-input" style="width: 260px">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">电话</label>
									<div class="layui-input-inline">
										<input type="text" required  id="updtel"
											placeholder="请输入联系人电话" autocomplete="off" class="layui-input"
											style="width: 260px">
									</div>
									<label class="layui-form-label">备注</label>
									<div class="layui-input-inline">
										<textarea placeholder="请输入内容(50字符内)" class="layui-textarea"
											id="updcomm" style="width: 260px; resize: none;"></textarea>
									</div>
								</div>
								<button type="button" style="margin: 15px 0 0 330px;"
									onclick="updConfer()" class="layui-btn">修改</button>
								<button type="button" style="margin: 15px 0 0 15px;"
									class="layui-btn" onclick="closeThis()">关闭</button>
							</form>
						</div>
					</div>
				</div>
				<!-- 修改会议室图片 -->
				<div class="updConferImg"
					style="display: none; width: 100%; height: 580px; border: none">
				</div>
				<!-- 查找用户 -->
				<div class="selU"
					style="display: none; width: 100%; height: 580px; border: none">
					<div class="layui-item" style="padding: 20px 0px 15px 430px;">
						<div class="layui-input-inline">
							<input type="text" id="takeUserName" required
								 placeholder="请输入用户名" autocomplete="off"
								class="layui-input">
						</div>
						<button type="button" class="layui-btn" onclick="selUserByName()">查询</button>
					</div>
					<table class="layui-table" lay-skin="line">
						<colgroup>
							<col width="80">
							<col width="80">
							<col width="80">
							<col width="80">
							<col width="80">
							<col width="160">
							<col width="160">
							<col width="160">
							<col width="80">
							<col width="160">
						</colgroup>
						<thead>
							<tr>
								<th>编号</th>
								<th>用户名</th>
								<th>性别</th>
								<th>电话</th>
								<th>生日</th>
								<th>住址</th>
								<th>爱好</th>
								<th>简介</th>
								<th>账户余额</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="takeUser_tbody">
						</tbody>
					</table>
					<div class="pagingdiv">
						<ul class="pagination-digg" id="takeUserPageing">
						</ul>
					</div>
					<!-- 模拟框 -->
					<div id="userModal" style="display: none;">
						<br /> <br /> <br /> <br />
						<button type="button" style="margin: 15px 0 0 130px;"
							class="layui-btn" id="yesUser">确定</button>
						<button type="button" style="margin: 15px 0 0 15px;"
							class="layui-btn" onclick="closeThis()">取消</button>
					</div>

				</div>
				<!-- 使用登记 -->
				<div class="updU"
					style="display: none; width: 100%; height: 580px; border: none">
					<div class="layui-item" style="padding: 20px 0px 15px 430px;">
						<div class="layui-input-inline">
							<input type="text" id="takeByTel" required 
								placeholder="请输入电话号码" autocomplete="off" class="layui-input">
						</div>
						<button type="button" class="layui-btn" onclick="takeByTel()">查询</button>
					</div>
					<table class="layui-table" lay-skin="line">
						<colgroup>
							<col width="80">
							<col width="100">
							<col width="120">
							<col width="120">
							<col width="120">
							<col width="200">
						</colgroup>
						<thead>
							<tr>
								<th>编号</th>
								<th>用户名</th>
								<th>电话号码</th>
								<th>使用时间</th>
								<th>会议室名</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="useConfer_tbody">
						</tbody>
					</table>
					<div class="pagingdiv">
						<ul class="pagination-digg" id="useConferPageing">
						</ul>
					</div>
				</div>
				<!-- 用户反馈 -->
				<div class="takeMsg"
					style="display: none; width: 100%; height: 580px; border: none">
					<div class="layui-item" style="padding: 20px 0px 15px 430px;">
						<div class="layui-input-inline">
							<input type="text" id="takeMsgByName" required
								 placeholder="请输入用户名" autocomplete="off"
								class="layui-input">
						</div>
						<button type="button" class="layui-btn" onclick="selMsgByName()">查询</button>
					</div>
					<table class="layui-table" lay-skin="line">
						<colgroup>
							<col width="150">
							<col width="150">
							<col width="200">
							<col width="200">
						</colgroup>
						<thead>
							<tr>
								<th>编号</th>
								<th>用户名</th>
								<th>信息</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="takeMsg_tbody">
						</tbody>
					</table>
					<div class="pagingdiv">
						<ul class="pagination-digg" id="takeMsgPageing">
						</ul>
					</div>
					<!-- 模拟框 -->
					<div id="motaikunag" style="display: none;">
						<br /> <br />
						<div class="layui-row">
							<div class="layui-col-md3">&nbsp;&nbsp;&nbsp;用户信息：</div>
							<div class="layui-col-md9">
								<span id="userSay"></span>
							</div>
						</div>
						<br /> <br />
						<div class="layui-row">
							<div class="layui-col-md3">&nbsp;&nbsp;&nbsp;回复</div>
							<div class="layui-col-md9">
								<textarea rows="5" cols="30" style="resize: none;" id="asay"></textarea>
							</div>
						</div>
						<button type="button" style="margin: 15px 0 0 130px;"
							class="layui-btn" id="yes">确定</button>
						<button type="button" style="margin: 15px 0 0 15px;"
							class="layui-btn" onclick="closeThis()">关闭</button>
					</div>
				</div>
				<!-- 查找待审核 -->
				<div class="selA"
					style="display: none; width: 100%; height: 580px; border: none">
					<div style="padding: 15px;">
						<div class="layui-item" style="padding: 20px 0px 15px 430px;">
							<div class="layui-input-inline">
								<input type="text" id="selAbyname" required
									 placeholder="请输入会议室名" autocomplete="off"
									class="layui-input">
							</div>
							<button type="button" class="layui-btn" onclick="selAByName()">查询</button>
						</div>
						<table class="layui-table" lay-skin="line">
							<colgroup>
								<col width="150">
								<col width="150">
								<col width="200">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>编号</th>
									<th>申请人</th>
									<th>会议室</th>
									<th>预约时间</th>
									<th>交易金额</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="selA_tbody">
							</tbody>
						</table>
						<div class="pagingdiv">
							<ul class="pagination-digg" id="selAPageing">
							</ul>
						</div>
						<!-- 模拟框 -->
						<div id="commModal" style="display: none;">
							<br /> <br />
							<div class="layui-row">
								<div class="layui-col-md3">&nbsp;&nbsp;&nbsp;备注</div>
								<div class="layui-col-md9">
									<textarea rows="5" cols="30" style="resize: none;" id="comm"></textarea>
								</div>
							</div>
							<button type="button" style="margin: 15px 0 0 130px;"
								class="layui-btn" id="yesComm">确定</button>
							<button type="button" style="margin: 15px 0 0 15px;"
								class="layui-btn" onclick="closeThis()">关闭</button>
						</div>
					</div>
				</div>
				<!-- 查找已审核 -->
				<div class="selAD"
					style="display: none; width: 100%; height: 580px; border: none">
					<div style="padding: 15px;">
						<div class="layui-item" style="padding: 20px 0px 15px 430px;">
							<div class="layui-input-inline">
								<input type="text" id="AD_name" required 
									placeholder="请输入会议室名" autocomplete="off" class="layui-input">
							</div>
							<button type="button" class="layui-btn" onclick="selADByName()">查询</button>
						</div>
						<table class="layui-table" lay-skin="line">
							<colgroup>
								<col width="150">
								<col width="150">
								<col width="200">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>编号</th>
									<th>申请人</th>
									<th>会议室</th>
									<th>预约时间</th>
									<th>交易金额</th>
									<th>状态</th>
									<th>处理人</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="selAD_tbody">
							</tbody>
						</table>
						<div class="pagingdiv">
							<ul class="pagination-digg" id="selADPageing">
							</ul>
						</div>
					</div>
				</div>
				<!-- 修改密码 -->
				<div class="changeP"
					style="display: none; width: 100%; height: 580px; border: none">
					<div class="layui-item" style="padding: 200px 0px 15px 400px;">
						<div class="layui-form-item">
							<label class="layui-form-label">原密码</label>
							<div class="layui-input-block">
								<input type="password" name="oldPassword" required
									id="oldPassword"  placeholder="请输入原密码"
									autocomplete="off" class="layui-input" style="width: 260px">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">新密码</label>
							<div class="layui-input-block">
								<input type="password" name="newPassword1" required
									id="newPassword1"  placeholder="请输入新密码"
									autocomplete="off" class="layui-input" style="width: 260px">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">确认新密码</label>
							<div class="layui-input-block">
								<input type="password" name="newPassword2" required
									id="newPassword2"  placeholder="请再次输入新密码"
									autocomplete="off" class="layui-input" style="width: 260px">
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<input id="changePass" type="submit" value="修改" class="layui-btn"
									style="width: 100px" /> <input type="reset" value="重置"
									class="layui-btn" style="width: 100px" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-footer">
			<!-- 底部固定区域 -->
			&nbsp;&nbsp;版权所有&nbsp;&nbsp;&copy;2019-2020 &nbsp;&nbsp;LISTNER
		</div>
	</div>
	<script src=<%=application.getContextPath() + "/pages/layui.all.js"%>></script>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;
		});
	</script>
</body>
</html>