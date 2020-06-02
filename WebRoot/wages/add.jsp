﻿<%@page import="com.util.BaseDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<script language=javascript src='<%=path%>/js/My97DatePicker/WdatePicker.js'></script>
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
	color: #FFF
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
	color: #FFF
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
	color: #666;
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
			var a = document.getElementById("perf").value;
			var reg = /^[0-9]*$/; //数字
			if (reg.test(a)) {
				return true;
			}else if(a==""){
				return true;
			}else {
				alert("提成格式有误,请重新输入！")
				document.getElementById("perf").value = ("");
				return false;
			}

		}
		function out2() {
			var a = document.getElementById("base").value;
			var reg = /^[0-9]*$/; //数字
			if (reg.test(a)) {
				return true;
			}else if(a==""){
				return true;
			}else {
				alert("工资格式有误,请重新输入！")
				document.getElementById("base").value = ("");
				return false;
			}

		}
	</script>

	<script type="text/javascript">
		function check(form) {
			var empno = form1.empno.value;
			var name = form1.name.value;
			var perf = form1.perf.value;
			var base = form1.base.value;
			if (empno == "") {
				alert("工号不能为空，请选择工号！");
				return;
			}
 			if (name == "") {
				alert("姓名不能为空，请选择工号！");
				return;
			}
			if (perf == "") {
				alert("提成不能为空！");
				return;
			} 
			if (base == "") {
				alert("工资不能为空！");
				return;
			} 
		}
	</script>

	<script type="text/javascript">
		function a(sel) {
			document.getElementById("name").value=sel.value;
		}
	</script>
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">

		<tr>
			<td align="left" valign="top" id="addinfo">
				<a href="#" target="mainFrame" onFocus="this.blur()" class="add">薪资登记</a>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<form method="post" id="form1" action="<%=path%>/wages/add.do">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">工号：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<select name="empno" id="wages" onchange="a(this)">

									<%
							      BaseDAO dao = new BaseDAO();
							      List<Object[]> all = dao.find("select * from emp", null);
							      for(Object[] data:all){
							    %>
									<!-- 反向查找 -->
									<option value="<%=data[2] %>"><%=data[1] %></option>
									<%}%>

								</select>
							</td>

						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">姓名：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input type="text" id="name" name="name" value="" class="text-word" readonly="readonly">
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">提成：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input type="text" name="perf" value="" class="text-word" id="perf" onmouseout="out1()">
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">工资：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input type="text" name="base" value="" class="text-word" id="base" onmouseout="out2()">
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">&nbsp;</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input name="" type="button" value="提交" onMouseDown="check();" class="text-but"
									onclick="smt()">
								<input name="" type="reset" value="重置" class="text-but">
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		function smt(){
			var form1=document.getElementById("form1");
			var sel=document.getElementById("wages");
			var ops=sel.getElementsByTagName("option");
			for (var i = 0; i < ops.length; i++) {
				if(ops[i].selected){
					ops[i].value=ops[i].innerHTML;
				}
			}
			form1.submit();
		}
	</script>
</body>
</html>