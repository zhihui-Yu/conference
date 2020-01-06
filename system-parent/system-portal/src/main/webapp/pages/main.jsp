<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"/>
	<div class="content">
		<!--左边的搜索内容-->
		<div class="left">
			<!--提示字-->
			<div class="text">
				<h2>查询选择</h2>
				根据需要进行查询
			</div>
			<div class="select">
				<span class="selectWords"> 可容纳人数 </span>
				<div class="selectText">
					<select name="peopleNum">
						<option value="10">0-10</option>
					</select>
				</div>
			</div>
			<div class="select">
				<span class="selectWords"> 价格 </span>
				<div class="selectText">
					<select name="price">
						<option value="1000以内">1000以内</option>
					</select>
				</div>
			</div>
			<div class="select">
				<span class="selectWords"> 日期 </span>
				<div class="time">
					<input type="date" name="date" id="date" value="会议日期选择" />
				</div>
			</div>
			<div class="sub">
				<button onclick="">查询</button>
			</div>
		</div>

		<!--右边的图文详情-->
		<div class="right">
			<ul>
				<c:forEach items="${list }" var="s">
					<li>
						<div class="right_content" onclick="">
							<!--上面图片-->
							<div class="topImg">
								<img src="img/350.jpg" width="333px" height="255px" />
							</div>
							<!--下面说明文字-->
							<div class="downText">
								<div class="cnfer_name">
									<!--会议室名称-->
									<strong>&nbsp;&nbsp;会议室1</strong>
								</div>
								<div class="confer_price">
									<!--会议室价格-->
									<strong>¥3,200/全天&nbsp;&nbsp;</strong>
								</div>
								<div class="confer_infor">
									<!--会议室简介-->
									&nbsp;&nbsp; 最多容纳：99人 &nbsp;&nbsp; 会场面积：99㎡
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
<jsp:include page="bottom.jsp"/>