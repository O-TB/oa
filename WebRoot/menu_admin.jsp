<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左侧导航menu</title>
<link href="<%=path%>/css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/js/sdmenu.js"></script>
<script type="text/javascript">
	// <![CDATA[
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
	// ]]>
</script>
<style type=text/css>
html {
	SCROLLBAR-FACE-COLOR: #538ec6;
	SCROLLBAR-HIGHLIGHT-COLOR: #dce5f0;
	SCROLLBAR-SHADOW-COLOR: #2c6daa;
	SCROLLBAR-3DLIGHT-COLOR: #dce5f0;
	SCROLLBAR-ARROW-COLOR: #2c6daa;
	SCROLLBAR-TRACK-COLOR: #dce5f0;
	SCROLLBAR-DARKSHADOW-COLOR: #dce5f0;
	overflow-x: hidden;
}

body {
	overflow-x: hidden;
	background: url(<%=path%>/images/main/leftbg.jpg) left top repeat-y
		#f2f0f5;
	width: 194px;
}
/*  a {
   display:inline;
 } */

</style>
</head>
<body onselectstart="return false;" ondragstart="return false;" oncontextmenu="return false;">
	<div id="left-top">
		<div>
			<img src="<%=path%>/images/main/tou_admin.jpg" width="44" height="44" />
		</div>
		<c:choose>
			<c:when test="${role eq '管理员' }">
				<span>
					用户：${loginUser[1] }
					<br>角色：${role }
				</span>
			</c:when>
		</c:choose>
	</div>
	<div style="float: left" id="my_menu" class="sdmenu">
		<div class="collapsed">
			<span>部门管理</span>
			<a href="<%=path %>/dept/add.jsp" target="mainFrame" onFocus="this.blur()">部门添加</a>
			<a href="<%=path %>/dept/show.do" target="mainFrame" onFocus="this.blur()">部门信息</a>
		</div>


		<div class="collapsed">
			<span>员工管理</span>
			<a href="<%=path %>/emp/add.jsp" target="mainFrame" onFocus="this.blur()">员工添加</a>
			<a href="<%=path %>/emp/show.do" target="mainFrame" onFocus="this.blur()">员工信息</a>
			<a href="<%=path %>/emp/view.do" target="mainFrame" onFocus="this.blur()" id="b1" onclick="dianji2();">员工通讯录</a>
		</div>


		<div>
			<span>公告管理</span>
			<a href="<%=path %>/notice/add.jsp" target="mainFrame" onFocus="this.blur()" id="a1" onclick="dianji();">发布公告</a>
			<a href="<%=path %>/notice/show.do" target="mainFrame" onFocus="this.blur()" >公告信息</a>
		</div>


	</div>
	<!-- <script type="text/javascript">
		function dianji() {
			var a = document.getElementById("a1");
			if ("a1"){
				a.style.backgroundColor = "blue";
				return;
			}
			
		}
		function dianji2() {
			var b = document.getElementById("b1");
			if ("b1"){
				b.style.backgroundColor = "red";
				return;
			}
			
		}
	</script> -->
</body>
</html>