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
</style>
</head>

<body onselectstart="return false;" ondragstart="return false;" oncontextmenu="return false;">
	<div id="left-top">
		<div>
			<img src="<%=path%>/images/main/tou.jpg" width="44" height="44" />
		</div>
		<c:choose>
			<c:when test="${role eq '主任' }">
				<span>
					<p>
						角色：${role }
						<p />
						<p>用户：${loginUser[2] }</p>
				</span>
			</c:when>
			<c:otherwise>
				<span>
					<p>科室：${deptName }</p>
					<p>用户：${loginUser[2] }</p>
				</span>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="float: left" id="my_menu" class="sdmenu">
		<div class="collapsed">
			<span>部门信息</span>
			<c:if test="${role eq '管理员' }">
				<a href="<%=path%>/dept/add.jsp" target="mainFrame" onFocus="this.blur()">部门添加</a>
			</c:if>
			<a href="<%=path%>/dept/show.do" target="mainFrame" onFocus="this.blur()">部门信息</a>
		</div>


		<div class="collapsed">
			<span>员工信息</span>
			<c:if test="${role eq '管理员' }">
				<a href="<%=path%>/emp/add.jsp" target="mainFrame" onFocus="this.blur()">员工添加</a>
				<a href="<%=path%>/emp/show.do" target="mainFrame" onFocus="this.blur()">员工信息</a>
			</c:if>
			<c:if test="${role != '管理员'  }">
				<a href="<%=path%>/emp/show.do" target="mainFrame" onFocus="this.blur()">个人信息</a>
			</c:if>
			<a href="<%=path%>/emp/view.do" target="mainFrame" onFocus="this.blur()">员工通讯录</a>
		</div>

		<div>
			<span>请假管理</span>
			<c:if test="${role eq '普通员工' }">
				<a href="<%=path%>/offs/add.jsp" target="mainFrame" onFocus="this.blur()">请假登记</a>
			</c:if>
			<a href="<%=path%>/offs/show.do" target="mainFrame" onFocus="this.blur()">请假信息</a>
			<a href="<%=path%>/offsApply/show.do" target="mainFrame" onFocus="this.blur()">销假信息</a>
		</div>

		<div>
			<c:if test="${role eq '主任' }">
				<span>考勤管理</span>
			</c:if>
			<c:if test="${role eq '普通员工' }">
				<span>考勤查看</span>
				<a href="<%=path%>/emp/load.do" target="mainFrame" onFocus="this.blur()">考勤登记</a>
			</c:if>
			<a href="<%=path%>/duty/show.do" target="mainFrame" onFocus="this.blur()">考勤信息</a>
			<c:if test="${role eq '普通员工' }">
				<a href="<%=path%>/works/add.jsp" target="mainFrame" onFocus="this.blur()">异常提交</a>
			</c:if>
			<a href="<%=path%>/works/show.do" target="mainFrame" onFocus="this.blur()">异常信息</a>
		</div>

		<div>
			<c:if test="${role eq '主任' }">
				<span>薪资管理</span>
				<a href="<%=path%>/wages/add.jsp" target="mainFrame" onFocus="this.blur()">薪资登记</a>
			</c:if>
			<c:if test="${role eq '普通员工' }">
				<span>薪资查看</span>
			</c:if>
			<a href="<%=path%>/wages/show.do" target="mainFrame" onFocus="this.blur()">薪资信息</a>
		</div>

		<div>
			<c:if test="${role eq '主任' }">
				<span>会议邀请</span>
				<a href="<%=path%>/meet/add.jsp" target="mainFrame" onFocus="this.blur()">会议发布</a>
			</c:if>
			<c:if test="${role eq '普通员工' }">
				<span>会议查看</span>
			</c:if>
			<a href="<%=path%>/meet/show.do" target="mainFrame" onFocus="this.blur()">会议信息</a>
		</div>

		<div>
			<span>公告查看</span>
			<a href="<%=path%>/notice/show.do" target="mainFrame" onFocus="this.blur()">公告信息</a>
		</div>

		<div>
			<c:if test="${role eq '主任' }">
				<span>文档共享管理</span>
				<a href="<%=path%>/report/add.jsp" target="mainFrame" onFocus="this.blur()">文档上传</a>
				<a href="<%=path%>/report/show.do" target="mainFrame" onFocus="this.blur()">文档管理</a>
			</c:if>
			<c:if test="${role eq '普通员工' }">
				<span>文档共享</span>
				<a href="<%=path%>/report/add.jsp" target="mainFrame" onFocus="this.blur()">文档上传</a>
				<a href="<%=path%>/report/show.do" target="mainFrame" onFocus="this.blur()">文档下载</a>
			</c:if>
			<a href="<%=path%>/report/showmy.do" target="mainFrame" onFocus="this.blur()">我的文档</a>
		</div>

		<div>
			<c:if test="${role eq '普通员工' }">
				<span>办公申请</span>
				<a href="<%=path%>/apply/add.jsp" target="mainFrame" onFocus="this.blur()">提交申请</a>
			</c:if>
			<c:if test="${role eq '主任' }">
				<span>办公申请审核</span>
			</c:if>
			<a href="<%=path%>/apply/show.do" target="mainFrame" onFocus="this.blur()">申请信息</a>
		</div>

	</div>
</body>
</html>