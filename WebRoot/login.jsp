
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	session.removeAttribute("admin");
	session.invalidate();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>请登录</title>
<link href="images/login_title.png" rel="shortcut icon" type="image/x-ico">

<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" media="all" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <meta name="keywords"
		content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" /> -->
<link
	href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>

</head>
<body>
	<div class="login">
		<!-- <h2>医院OA办公管理系统</h2> -->
		<div class="login-top">
			<h1>用户登录</h1>
			<form name="f1" action="<%=path%>/user/login.do" method="post">
				<input name="name" class="editbox4" type="text" value="User Id" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'User Id';}">
				<input name="pwd" class="editbox4" type="password" value="password" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'password';}">

				<div class="forgot">

					<div class="insteadKind" style="margin-right: 98; height: 25;">

						<div class="insteadKindRadio">
							<input type="radio" id="option1" name="role" value="员工" class="option-radio"
								checked="checked" />
							<label for="option1">
								<span class="">员 工</span>
							</label>

							<input type="radio" id="option2" name="role" value="管理员" class="option-radio" />
							<label for="option2">
								<span class="">管理员</span>
							</label>

						</div>
					</div>
					<input name="submit" type="submit" onMouseDown="check();" onclick="sub()" class="button" id="submit" value="登录">
					<input name="cs" type="reset" onMouseDown="check();" class="button" id="cs" value="取 消">

				</div>
			</form>
		</div>
		<div class="login-bottom"></div>
	</div>
	<script type="text/javascript">
		function check(form) {
			var name = f1.name.value;
			var pwd = f1.pwd.value;
			if (name == "User Id" && pwd == "password") {
				alert("请输入用户Id和密码！");
				return;
			} else if (name == "User Id") {
				alert("请输入用户Id！");
				return;
			} else if (pwd == "password") {
				alert("请输入用户密码！");
				return;
			}

		}
	</script>
	<!-- <script>
	function sub() {
		var saytext = decodeURI("我最棒");
		var text;
		var err_list = new Array("别乱填！！", "就不能夸夸自己吗？");
		text = prompt("本系统专属验证码是“" + saytext + "”");
		for (i = 0; text != saytext; ++i) {
			if (i == err_list.length) {
				i = 0;
			}
			alert(err_list[i]);
			text = prompt("本系统专属验证码是“" + saytext + "”");
		}
		alert("嗯，好好工作吧！");
	}
	</script> -->
	<div class="copyright">
		<p>
			Copyright &copy; 2019.医院协同OA办公系统的设计与实现.
			<a href="" target="_blank">华祖全</a>
		</p>
	</div>
</body>
</html>