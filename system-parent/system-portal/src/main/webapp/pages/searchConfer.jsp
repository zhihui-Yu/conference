<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp" />
<div class="content">
	<!--显示查询出该用户已经存在的会议包含已经已完成的和完成的-->
	<div class="confer">
		<div class="search">
			<form action="#" method="post">
				<input type="text" name="conferName" id="conferName" value="会议室名称" />
				<input type="date" name="conferDate" id="conferDate" value="会议室使用时间" />

				<input type="submit" value="查询" />
			</form>
		</div>
		<div class="table">
			<table border="1" cellspacing="" cellpadding="">
				<tr>
					<th>会议室使用人</th>
					<th>会议室名称</th>
					<th>会议室价格</th>
					<th>会议室使用时间</th>
					<th>会议室审核状态</th>
					<th>是否已使用</th>
					<th>操作</th>
				</tr>
				<tr>
					<td>张三</td>
					<td>会议室1</td>
					<td>¥5999</td>
					<td>2019-11-12</td>
					<td>通过</td>
					<td>是</td>
					<th><input type="button" name="upd" id="upd" value="修改"
						ondblclick="updConfer.html" /></th>
				</tr>
			</table>

		</div>
	</div>
</div>
<jsp:include page="bottomAll.jsp"/>