<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主要内容区main</title>
<link href="<%=path%>/css/css.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/css/main.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="<%=path%>/images/main/favicon.ico" />
<style>
body {
	overflow-x: hidden;
	background: #f2f0f5;
	padding: 15px 0px 10px 5px;
}

#main {
	font-size: 12px;
}

#main span.time {
	font-size: 14px;
	color: #528dc5;
	width: 100%;
	padding-bottom: 10px;
	float: left
}

#main div.top {
	width: 100%;
	background: url(<%=path%>/images/main/main_r2_c2.jpg) no-repeat 0 10px;
	padding: 0 0 0 15px;
	line-height: 35px;
	float: left
}

#main div.sec {
	width: 100%;
	background: url(<%=path%>/images/main/main_r2_c2.jpg) no-repeat 0 15px;
	padding: 0 0 0 15px;
	line-height: 35px;
	float: left
}

.left {
	float: left
}

#main div a {
	float: left
}

#main span.num {
	font-size: 30px;
	color: #538ec6;
	font-family: "Georgia", "Tahoma", "Arial";
}

.left {
	float: left
}

div.main-tit {
	font-size: 14px;
	font-weight: bold;
	color: #4e4e4e;
	background: url(<%=path%>/images/main/main_r4_c2.jpg) no-repeat 0 33px;
	width: 100%;
	padding: 30px 0 0 20px;
	float: left
}

div.main-con {
	width: 100%;
	float: left;
	padding: 10px 0 0 20px;
	line-height: 36px;
}

div.main-corpy {
	font-size: 14px;
	font-weight: bold;
	color: #4e4e4e;
	background: url(<%=path%>/images/main/main_r6_c2.jpg) no-repeat 0 33px;
	width: 100%;
	padding: 30px 0 0 20px;
	float: left
}

div.main-order {
	line-height: 30px;
	padding: 10px 0 0 0;
}
</style>
</head>
<body class="body">
	<!-- count:线条数量  zindex:层级   opacity:透明度  color:线条颜色 -->
	<script src="js/canvas-nest.min.js" count="150" zindex="-2" opacity="0.6" color="47,135,193"
		type="text/javascript">
		
	</script>
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		/* 鼠标特效 */
		var a_idx = 0;
		jQuery(document)
				.ready(
						function($) {
							$("body")
									.click(
											function(e) {
												var a = new Array("❤要努力❤",
														"❤爱学习❤", "❤进步❤",
														"❤热爱❤", "❤坚持❤", "❤加油❤",
														"❤你可以的❤", "❤阳光❤",
														"❤向上❤", "❤积极❤", "❤稳重❤",
														"❤学会孤独❤");
												var $i = $("<span></span>")
														.text(a[a_idx]);
												a_idx = (a_idx + 1) % a.length;
												var x = e.pageX, y = e.pageY;
												$i
														.css({
															"z-index" : 999999999999999999999999999999999999999999999999999999999999999999999,
															"top" : y - 20,
															"left" : x,
															"position" : "absolute",
															"font-weight" : "bold",
															"color" : "rgb("
																	+ ~~(255 * Math
																			.random())
																	+ ","
																	+ ~~(255 * Math
																			.random())
																	+ ","
																	+ ~~(255 * Math
																			.random())
																	+ ")"
														});
												$("body").append($i);
												$i.animate({
													"top" : y - 180,
													"opacity" : 0
												}, 1500, function() {
													$i.remove();
												});
											});
						});
	</script>

	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="main">
		<%-- <tr>
			<td colspan="2" align="left" valign="top">
				<span class="time" style="font-size: 25">
					<strong>您好！${loginUser[2] }~</strong>
				</span>

			</td>
		</tr> --%>
		<tr>
			<td align="left" valign="top" width="50%">
				<div class="main-tit">系统信息</div>
				<div class="main-con">
					操作系统：Windows 10教育版
					<br />
					系统架构: JavaEE分布式架构
					<br />
					开发技术: JavaWeb开发技术
					<br />
					程序编码：UTF-8
					<br />
				</div>
			</td>
			<td align="left" valign="top" width="50%">
				<div class="main-tit">服务器信息</div>
				<div class="main-con">
					<!-- 服务器IP：10.6.2.55
					<br /> -->
					服务器版本：Apache-Tomcat-7.0.11
					<br />
					JDK版本：1.7.0.0_80
					<br />
					MySQL版本：5.1.40-community
					<br />
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left" valign="top">
				<div class="main-tit">温馨提示</div>
				<div class="main-con">
					新一天的工作开始啦~
					<br />
					记得上下班要打卡哦~
				</div>
			</td>
		</tr>
	</table>
</body>

</html>
