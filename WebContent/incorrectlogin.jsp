<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Oh oh</title>
<link rel="stylesheet" type="text/css" href="main.css">
<link rel="stylesheet" type="text/css" href="index.css">
</head>
<body>
<div class="header">
		<div class="title">
			Shout<b>Box</b>
		</div>
	</div>
	<div class="body">
		<div id="loginDiv">
		<h3>Oh oh - invalid username or password</h3>
			<form name="login" action="login" method="get">
				<div id="leftColumn">
					Username<br /><br />
					Password<br /><br />
					<input type="submit" value="Login">
				</div>
				<div id="rightColumn">
					<input type="text" name="username"><br /> <br />
					<input type="password" name="password"><br /> <br /><br />
				</div>
				<div style="clear: both;"></div>
			</form>
		</div>
	</div>
</body>
</html>