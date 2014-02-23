<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="com.ShoutBox.Tom.stores.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>ShoutBox</title>
<link rel="stylesheet" type="text/css" href="main.css">
<link rel="stylesheet" type="text/css" href="index.css">
</head>
<script>
	var passcheck = false;
	var usercheck = false;
	var passmatch = false;
	function checkPass() {
		var password = document.getElementById("newPassword").value;
		var retype = document.getElementById("passwordRetype").value;

		if (password != retype) {
			document.getElementById("error").innerHTML = "Passwords do not match";
			passmatch = false;
		} else {
			document.getElementById("error").innerHTML = "";
			passmatch = true;
		}
		//checkBoxes();
	}
	function checkBoxes() {
		var username = document.getElementById("newUsername").value;
		var password = document.getElementById("newPassword").value;
		var retype = document.getElementById("passwordRetype").value;
		var email = document.getElementById("email").value;

		if (checkSpecial(username) && username != "") {
			document.getElementById("usernameerror").innerHTML = "Username cannot contain special characters";
			usercheck = false;
		} else {
			document.getElementById("usernameerror").innerHTML = "";
			usercheck = true;
		}

		if (checkSpecial(password) && password != "") {
			document.getElementById("error").innerHTML = "Password cannot contain special characters";
			passcheck = false;
		} else {
			document.getElementById("error").innerHTML = "";
			passcheck = true;
		}

		if (username != "" && password != "" && retype != "" && email != ""
				&& passcheck == true && usercheck == true && passmatch == true)
			document.getElementById("inputbutton").innerHTML = "<input type=\"submit\" value=\"Register\" />";
		else
			document.getElementById("inputbutton").innerHTML = "";
	}
	function checkSpecial(str) {
		var specials = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?";
		for ( var i = 0; i < str.length; i++) {
			if (specials.indexOf(str.charAt(i)) != -1) {
				return true;
			}
		}
		return false;
	}
	function showRegister()
	{
		document.getElementById("registerDiv").style.display = "inline";
		document.getElementById("loginDiv").style.display = "none";
	}
	function showLogin()
	{
		document.getElementById("registerDiv").style.display = "none";
		document.getElementById("loginDiv").style.display = "inline";
	}
</script>
<body>
	<div class="header">
		<div class="title">
			Shout<b>Box</b>
		</div>
	</div>
	<div class="body">
		<div id="loginDiv">
			<form name="login" action="login" method="get">
				<div id="leftColumn">
					Username<br /><br />
					Password<br /><br />
					<input type="submit" value="Login">
				</div>
				<div id="rightColumn">
					<input type="text" name="username"><br /> <br />
					<input type="password" name="password"><br /> <br /><br />
					<a href=# onClick="showRegister()">Or register here</a>
				</div>
				<div style="clear: both;"></div>
			</form>
		</div>
		<div id="registerDiv">
			<form name="register" action="register" method="post">
			<table>
			<tr>
				<td style="width:100px;" valign="top">Username</td><td style="width:240px; text-align:right;"><input type="text" id="newUsername" name="newUsername" onkeyup="checkBoxes()"><br /><br /></td>
				</tr>
			</table>
			<span id="usernameerror" class="error"></span>
				<%
					if (request.getAttribute("errorMessage") != null) {
						MessageStore errorMessage = (MessageStore) request
								.getAttribute("errorMessage");
				%><%=errorMessage.getMessage()%><br />
				<%
					}
				%>
				<table>
				<tr>
				<td valign="top" >Password</td><td style="width:240px; text-align:right;"><input type="password" id="newPassword" name="newPassword" onkeyup="checkBoxes()"><br /><br /></td>
				</tr>
				<tr>
				<td valign="top">Repeat Password</td><td style="width:240px; text-align:right;"><input type="password" id="passwordRetype" onkeyup="checkPass()"><br /><br /></td>
				</tr>
				</table>
				<span id="error" class="error"></span>
				<table>
				<tr>
				<td style="width:100px;" valign="top">Email</td><td style="width:240px; text-align:right;"><input type="text" id="email" name="email" onkeyup="checkBoxes()"><br /><br /></td>
				</tr>
				<tr>
				<td><span id="inputbutton"></span></td><td style="width:240px; text-align:right;"><a href=# onClick="showLogin()">Login</a>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>