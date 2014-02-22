<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>ShoutBox</title>
</head>
<body>
<form name="login" action="login" method="get">
Username <input type="text" name="username"><br />
Password <input type="password" name="password"><br />
<input type="submit"/>
</form>
<form name="register" action="register" method="post">
Username <input type="text" name="username"><br />
Password <input type="password" name="password"><br />
Repeat Password <input type="password" name="passwordRetype"><br />
Email <input type="text" name="email"><br />
<input type="submit"/>
</form>
</body>
</html>