<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp" />
<div class="content">
	<!--显示查询出该用户已经存在的会议包含已经已完成的和完成的-->
	<div class="confer">
		<div class="table">
			<form action="" # method="post">
				<table border="1" cellspacing="35px" cellpadding="35px">
					<tr>
						<th>会议室使用人</th>
						<td>张三</td>
					</tr>
					<tr>
						<th>会议室名称</th>
						<td>会议室1</td>
					</tr>
					<tr>
						<th>会议室价格</th>
						<td>¥5999</td>
					</tr>
					<tr>
						<th>会议室使用时间</th>
						<td><input type="date" name="date" id="date"
							value="2019-11-12" /></td>
					</tr>
				</table>
				<input type="submit" value="提交" />
			</form>

		</div>
	</div>
</div>
<jsp:include page="bottomAll.jsp" />
