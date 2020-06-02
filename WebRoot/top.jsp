<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<c:if test="${role eq null}">
	<script>
    window.parent.parent.location.href="<%=path%>login.jsp";
	</script>
</c:if>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台页面头部</title>
<link href="<%=path%>/css/css.css" type="text/css" rel="stylesheet" />
</head>
<body onselectstart="return false" oncontextmenu=return(false) style="overflow-x: hidden;">
	<!--禁止网页另存为-->
	<noscript>
		<iframe scr="*.htm"></iframe>
	</noscript>
	<!--禁止网页另存为-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="header">
		<tr>
			<td rowspan="2" align="left" valign="middle" id="logo">
				<img src="<%=path%>/images/main/logo.png" width="56" height="54">
			</td>
			<td align="left" valign="middle">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="left" valign="bottom" id="header-name">医院OA办公管理系统</td>
						<!-- <td valign="bottom" id="kscz">
							<a href="#" target="_parent" onFocus="this.blur()" class="ks" onClick="return fun()">快速查找</a>
						</td> -->
						<td align="right" valign="bottom" id="header-right">
							<a href="<%=path%>/login.jsp" target="_parent" onFocus="this.blur()" class="admin-out">注销</a>
							<a href="<%=path%>/index.jsp" target="_parent" onFocus="this.blur()" class="admin-index">系统首页</a>
							<span>
								<!-- 日历 -->
								<SCRIPT type=text/javascript src="js/clock.js"></SCRIPT>
								<SCRIPT type=text/javascript>
									showcal();
								</SCRIPT>
							</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<script>
		function fun() {
			if (prompt("请选择你要查找的！")) {
				return true;
			}
			return false;
		}
	</script>
	
</body>
</html>