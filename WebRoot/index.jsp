<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>医院OA办公管理系统</title>
<link href="images/index_title.png" rel="icon" type="image/x-ico">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=path%>/css/css.css" type="text/css" rel="stylesheet" />
</head>
<frameset rows="65,*,0" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="<%=path%>/top.jsp" name="topframe" scrolling="no" noresize id="topframe"
		title="topframe" />
	<frameset id="attachucp" framespacing="0" border="0" frameborder="no" cols="194,12,*" rows="*">
		<c:choose>
			<c:when test="${role eq '管理员' }">
				<frame scrolling="auto" noresize="" frameborder="no" name="leftFrame"
					src="<%=path%>/menu_admin.jsp"></frame>
			</c:when>
			<c:otherwise>
				<frame scrolling="auto" noresize="" frameborder="no" name="leftFrame" src="<%=path%>/menu.jsp"></frame>
			</c:otherwise>
		</c:choose>
		<frame id="leftbar" scrolling="no" noresize="" name="switchFrame" src="<%=path%>/swich.html"></frame>
		<frame scrolling="auto" noresize="" border="0" name="mainFrame" src="<%=path%>/main.jsp"></frame>
	</frameset>
</frameset>
<noframes></noframes>
</html>