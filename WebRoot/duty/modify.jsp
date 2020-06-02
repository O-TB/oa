<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<script src="<%=path%>/js/city.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
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
	background: url(<%=path%>/images/main/add.jpg) no-repeat 0px 6px;
	padding: 0 10px 0 26px;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	font-weight: bold;
	color: #FFF;
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
	border-top: 1px solid #ebebeb;
}

.borderright {
	border-right: 1px solid #ebebeb;
}

.borderbottom {
	border-bottom: 1px solid #ebebeb;
}

.borderleft {
	border-left: 1px solid #ebebeb;
}

.gray {
	color: #dbdbdb;
}

td.fenye {
	padding: 10px 0 0 0;
	text-align: right;
}

.bggray {
	background: #f9f9f9;
	font-size: 14px;
	font-weight: bold;
	padding: 10px 10px 10px 0;
	width: 120px;
}

.main-for {
	padding: 10px;
}

.main-for input.text-word {
	width: 310px;
	height: 36px;
	line-height: 36px;
	border: #ebebeb 1px solid;
	background: #FFF;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	padding: 0 10px;
}

.main-for select {
	width: 310px;
	height: 36px;
	line-height: 36px;
	border: #ebebeb 1px solid;
	background: #FFF;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	color: #666;
}

.main-for input.text-but {
	width: 100px;
	height: 40px;
	line-height: 30px;
	border: 1px solid #cdcdcd;
	background: #468cd2;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	color: #f2f0f5;
	float: left;
	margin: 0 10px 0 0;
	display: inline;
	cursor: pointer;
	font-size: 14px;
	font-weight: bold;
}

.main-for textarea {
	width: 100%;
	height: 150px;
	line-height: 24px;
	border: #ebebeb 1px solid;
	background: #FFF;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	padding: 10px;
	color: #666
}

#addinfo a {
	font-size: 14px;
	font-weight: bold;
	background: url(<%=path%>/images/main/addinfoblack.jpg) no-repeat 0 1px;
	padding: 0px 0 0px 20px;
	line-height: 45px;
}

#addinfo a:hover {
	background: url(<%=path%>/images/main/addinfoblue.jpg) no-repeat 0 1px;
}
</style>
</head>
<body>
	<script>
		function out1() {
			var a = document.getElementById("start").value;
			var reg = /^([01][0-9]|2[0-3])(:[0-5][0-9]){2}$/; //时间
			if (reg.test(a)) {
				return true;
			}else if(a==""){
				return true;
			}else {
				alert("上班时间格式有误,请重新输入！")
				document.getElementById("start").value = ("");
				return false;
			}

		}
		function out2() {
			var a = document.getElementById("over").value;
			var reg = /^([01][0-9]|2[0-3])(:[0-5][0-9]){2}$/; //时间
			if (reg.test(a)) {
				return true;
			}else if(a==""){
				return true;
			}else {
				alert("下班时间格式有误,请重新输入！")
				document.getElementById("over").value = ("");
				return false;
			}
		}
		function out3() {
			var a = document.getElementById("dutyDate").value;
			var reg = /^\d{4}-\d{1,2}-\d{1,2}$/; //日期
			if (reg.test(a)) {
				return true;
			}else if(a==""){
				return true;
			}else {
				alert("考勤日期格式有误,请重新输入！")
				document.getElementById("dutyDate").value = ("");
				return false;
			}
		}
		
	</script>
	
	<script type="text/javascript">
		function check(form) {
			var start = f1.start.value;
			var over = f1.over.value;
			var dutyDate = f1.dutyDate.value;
			if (start == "") {
				alert("上班时间不能为空！");
				return;
			}
			if (over == "") {
				alert("下班时间不能为空！");
				return;
			}
			if (dutyDate == "") {
				alert("考勤日期不能为空！");
				return;
			}
		}
	</script>
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">

		<tr>
			<td align="left" id="main-left">
				<a href="#" target="mainFrame" onFocus="this.blur()" class="edit">编辑考勤信息</a>
				<a href="http://localhost:8080/oa/duty/show.do" target="mainFrame" onFocus="this.blur()" class="return">返回</a>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<form name="f1" method="post" action="<%=path%>/duty/update.do">
					<input type="hidden" name="id" value="${record.id }" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="main-tab">


						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工号：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="empno" value="${record.empno}"
								class="text-word" readonly="readonly"></td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">姓名：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="name" value="${record.name}" 
								class="text-word" readonly="readonly">
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">上班时间：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" id="start" name="start" value="${record.start}"
								class="text-word" onmouseout="out1()"></td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">下班时间：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" id="over" name="over" value="${record.over}" 
								class="text-word" onmouseout="out2()">
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">考勤日期：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="dutyDate" id="dutyDate" onmouseout="out3()" value="${record.dutyDate}"
								class="text-word" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						</tr>

		      <tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">&nbsp;</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input name=""
								type="submit" onMouseDown="check();" onclick="return data();" value="提交" class="text-but"> <input
								name="" type="reset" value="重置" class="text-but">
							</td>
						</tr>
					</table>
				</form></td>
		</tr>
	</table>
</body>
<script language="javascript">
	window.onload = function() {
		var strdate = window.document.getElementById('dutyDate').value;
		var date = new Date(strdate);
		var month = date.getMonth();
		if (date.getMonth() < 9) {
			month = '0' + (month+1);
		} else {
			month = month + 1;
		}
		var datetime = date.getFullYear() + '-' + month + '-' + date.getDate();

		window.document.getElementById('dutyDate').value = datetime;

	}
</script>
<script type="text/javascript">
	function data() {
		var a = document.getElementById('start').value;
		var b = document.getElementById('over').value;
		var arr1 = a.split(':');
		var arr2 = b.split(':');
		var a1 = arr1[0] * 3600 + arr1[1] * 60 + arr1[2];
		var b1 = arr2[0] * 3600 + arr2[1] * 60 + arr2[2];
		var c = new Date(document.getElementById('dutyDate').value).getTime();//dutyDate的时间戳
		var now = new Date().getTime();//当前时间戳
		var temp = 86400000;
		var now2 = now - temp;

		/* alert(a1);
		alert(b1); */
		if (a1 > b1) {
			alert("输入有误，下班时间不能小于上班时间！")
			return false;
		}

		if (c > now2) {
			alert("输入有误，考勤日期不能大于当前日期！")
			return false;
		}
	}
</script>
</html>