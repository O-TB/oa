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

#searchmain {
	font-size: 12px;
}

#search {
	font-size: 12px;
	background: #548fc9;
	margin: 10px 10px 0 0;
	display: inline;
	width: 100%;
	color: #FFF;
	float: left
}

#search form span {
	height: 40px;
	line-height: 40px;
	padding: 0 0px 0 10px;
	float: left;
}

#search form input.text-word {
	height: 24px;
	line-height: 24px;
	width: 180px;
	margin: 8px 0 6px 0;
	padding: 0 0px 0 10px;
	float: left;
	border: 1px solid #FFF;
}

#search form input.text-but {
	height: 24px;
	line-height: 24px;
	width: 55px;
	background: url(<%=path%>/images/main/list_input.jpg) no-repeat left top;
	border: none;
	cursor: pointer;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	color: #666;
	float: left;
	margin: 8px 0 0 6px;
	display: inline;
}

#search a.add {
	background: url(<%=path%>/images/main/add.jpg) no-repeat -3px 7px
		#548fc9;
	padding: 0 10px 0 26px;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	font-weight: bold;
	color: #FFF;
	float: right
}

#search a:hover.add {
	text-decoration: underline;
	color: #d2e9ff;
}

#main-tab {
	border: 1px solid #eaeaea;
	background: #FFF;
	font-size: 12px;
}

#main-tab th {
	font-size: 12px;
	background: url(<%=path%>/images/main/list_bg.jpg) repeat-x;
	height: 32px;
	line-height: 32px;
}

#main-tab td {
	font-size: 12px;
	line-height: 40px;
}

#main-tab td a {
	font-size: 12px;
	color: #548fc9;
}

#main-tab td a:hover {
	color: #565656;
	text-decoration: underline;
}

.bordertop {
	border-top: 1px solid #ebebeb
}

.borderright {
	border-right: 1px solid #ebebeb
}

.borderbottom {
	border-bottom: 1px solid #ebebeb
}

.borderleft {
	border-left: 1px solid #ebebeb
}

.gray {
	color: #dbdbdb;
}

td.fenye {
	padding: 10px 0 0 0;
	text-align: right;
}

.bggray {
	background: #f9f9f9
}
#addinfo a {
	font-size: 14px;
	font-weight: bold;
	background: url(<%=path%>/images/main/bool.jpg) no-repeat 0 1px;
	padding: 0px 0 0px 20px;
	line-height: 20px;
}

#addinfo a:hover {
	background: url(<%=path%>/images/main/boolon.jpg) no-repeat 0 1px;
}
</style>
</head>
<body>
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
<tr>
				<td align="left" valign="top" id="addinfo">
					<a href="#" target="mainFrame" onFocus="this.blur()" class="add">销假信息</a>
				</td>
			</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action=""></form>
						</td>

					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">

				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
					<tr>
						<th align="center" valign="middle" class="borderright">工号</th>
						<th align="center" valign="middle" class="borderright">姓名</th>
						<th align="center" valign="middle" class="borderright">销假日期</th>
						<th align="center" valign="middle" class="borderright">原请假原因</th>
						<th align="center" valign="middle" class="borderright">审核状态</th>

						<th align="center" valign="middle">操作</th>

					</tr>
					<c:forEach var="data" items="${all }">
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="center" valign="middle" class="borderright borderbottom">${data[1]}</td>
							<td align="center" valign="middle" class="borderright borderbottom">${data[2]}</td>
							<td align="center" valign="middle" class="borderright borderbottom">${data[3]}</td>
							<td align="center" valign="middle" class="borderright borderbottom">${data[5]}</td>
							<td align="center" valign="middle" class="borderright borderbottom">${data[4]}</td>
							<td align="center" valign="middle" class="borderbottom">
								<a href="<%=path %>/offsApply/del.do?id=${data[0]}" target="mainFrame" onFocus="this.blur()"
									class="add" onClick="return fun()">删除</a>
								<c:if test="${role eq '主任' }">
								<span class="gray">&nbsp;|&nbsp;</span>
									<a href="<%=path %>/offsApply/yes.do?id=${data[0]}&offsId=${data[6]}" target="mainFrame"
										onFocus="this.blur()" class="add">通过</a>
									<span class="gray">&nbsp;|&nbsp;</span>
									<a href="<%=path %>/offsApply/no.do?id=${data[0]}" target="mainFrame" onFocus="this.blur()"
										class="add">驳回</a>
								</c:if>
							</td>

						</tr>
					</c:forEach>

				</table>
			</td>
		</tr>

	</table>
	<script>
		function fun() {
			if (confirm("确定删除？")) {
				return true;
			}
			return false;
		}
	</script>
</body>
</html>