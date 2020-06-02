<%@page import="com.util.BaseDAO"%>
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
			var a = document.getElementById("empno").value;
			var reg = /^[A-Za-z0-9]+$/; //英文或数字
			if (reg.test(a)) {
				return true;
			}else if(a==""){
				return true;
			}else {
				alert("工号格式有误,请重新输入！")
				document.getElementById("empno").value = ("");
				return false;
			}

		}
		function out2() {
			var a = document.getElementById("tel").value;
			var reg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/; //手机号
			if (reg.test(a)) {
				return true;
			}else if(a==""){
				return true;
			}else {
				alert("手机号格式有误,请重新输入！")
				document.getElementById("tel").value = ("");
				return false;
			}
		}
		function out3() {
			var a = document.getElementById("mail").value;
			var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //邮箱
			if (reg.test(a)) {
				return true;
			}else if(a==""){
				return true;
			}else {
				alert("邮箱格式有误,请重新输入！")
				document.getElementById("mail").value = ("");
				return false;
			}
		}
		
	</script>
	<script type="text/javascript">
		function check(form) {
			var empno = f1.empno.value;
			var name = f1.name.value;
			var tel = f1.tel.value;
			var mail = f1.mail.value;
			var birth = f1.birth.value;
			var empTime = f1.empTime.value;
			if (empno == "") {
				alert("工号不能为空！");
				return;
			}
			if (name == "") {
				alert("姓名不能为空！");
				return;
			}
			if (tel == "") {
				alert("联系电话不能为空！");
				return;
			} 
			if (mail == "") {
				alert("邮箱不能为空！");
				return;
			} 
			if (birth == "") {
				alert("出生日期不能为空！");
				return;
			} 
			if (empTime == "") {
				alert("入职日期不能为空！");
				return;
			} 
		}
	</script>
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">

		<tr>
			<td align="left" valign="top" id="addinfo">
				<a href="#" target="mainFrame" onFocus="this.blur()" class="add">员工添加</a>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<form name="f1" method="post" action="<%=path%>/emp/add.do">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">工号：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input type="text" id="empno" name="empno" value="" class="text-word" onmouseout="out1()">
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">姓名：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input type="text" name="name" value="" class="text-word">
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">性别：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<select name="sex">
									<option>男</option>
									<option>女</option>
								</select>
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">联系电话：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input type="text" id="tel" name="tel" value="" class="text-word" onmouseout="out2()">
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">邮箱：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input type="text" id="mail" name="mail" value="" class="text-word" onmouseout="out3()">
							</td>
						</tr>


						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">出生日期：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input type="text" name="birth" id="birth" value="" class="text-word"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">入职日期：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input type="text" name="empTime" id="empTime" value="" class="text-word"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
							</td>
						</tr>

						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">部门名称：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<select name="dept">
									<%
							      BaseDAO dao = new BaseDAO();
							      List<Object[]> all = dao.find("select * from dept", null);
							      for(Object[] data:all){
							    %>
									<option><%=data[1] %></option>
									<%}%>
								</select>
							</td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">角色：</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<select name="state" id="state">
									<option>普通员工</option>
									<option>主任</option>
								</select>
							</td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle" class="borderright borderbottom bggray">&nbsp;</td>
							<td align="left" valign="middle" class="borderright borderbottom main-for">
								<input name="" type="submit" onclick="return data()" onMouseDown="check();" value="提交"
									class="text-but">
								<input name="" type="reset" value="重置" class="text-but">
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		function data() {
			var a = new Date(document.getElementById('birth').value).getTime();//birth的时间戳 
			var b = new Date(document.getElementById('empTime').value).getTime();//empTime的时间戳
			var now = new Date().getTime();//当前时间戳
			/* alert(a);
			alert(b);
			alert(now); */
			if(a > now){
				alert("输入有误，出生日期不能大于当前日期！");
				return false;
			}
			if (b > now) {
				alert("输入有误，入职日期不能大于当前日期！");
				return false;
			}
		}
	</script>
</body>
</html>