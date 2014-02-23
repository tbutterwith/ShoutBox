<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.ShoutBox.Tom.stores.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>ShoutBox</title>
</head>
<script>
var passcheck = false;
var usercheck = false;
var passmatch = false;
function checkPass()
{
	var password = document.getElementById("newPassword").value;
	var retype = document.getElementById("passwordRetype").value;
	
	if(password != retype)
		{
			document.getElementById("error").innerHTML="Passwords do not match";
			passmatch = false;
		}
	else
		{
			document.getElementById("error").innerHTML="";
			passmatch = true;
		}
	//checkBoxes();
}
function checkBoxes()
{
	var username = document.getElementById("newUsername").value;
	var password = document.getElementById("newPassword").value;
	var retype = document.getElementById("passwordRetype").value;
	var email = document.getElementById("email").value;
	
	if(checkSpecial(username) && username != "")
		{
			document.getElementById("usernameerror").innerHTML="Username cannot contain special characters";
			usercheck = false;
		}
	else
		{
		document.getElementById("usernameerror").innerHTML="";
			usercheck = true;
		}
		
	if(checkSpecial(password) && password != "")
		{
			document.getElementById("error").innerHTML="Password cannot contain special characters";
			passcheck = false;
		}
	else
		{
			document.getElementById("error").innerHTML = "";
			passcheck = true;
		}

		if (username != "" && password != "" && retype != "" && email != ""
				&& passcheck == true && usercheck == true && passmatch==true)
			document.getElementById("inputbutton").innerHTML = "<input type=\"submit\" value=\"Register\" />";
		else
			document.getElementById("inputbutton").innerHTML = "";
	}
	function checkSpecial(str) {
		var specials = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?";
		for (var i = 0; i < str.length; i++) {
		       if (specials.indexOf(str.charAt(i)) != -1) 
		       {
		           return true;
		       }
		    }
		    return false;
	}
</script>
<body>

<form name="login" action="login" method="get">
Username <input type="text" name="username"><br />
Password <input type="password" name="password"><br />
<input type="submit" value="login">
</form>
<form name="register" action="register" method="post">
Username <input type="text" id="newUsername" name="newUsername" onkeyup="checkBoxes()"><br />
<span id="usernameerror"></span><br />
<%
if(request.getAttribute("errorMessage") !=null)
{
	MessageStore errorMessage = (MessageStore) request.getAttribute("errorMessage");
%><%=errorMessage.getMessage() %><br/>
<%} %>
Password <input type="password" id="newPassword" name="newPassword" onkeyup="checkBoxes()"><br />
Repeat Password <input type="password" id="passwordRetype" onkeyup="checkPass()"><br />
<span id="error"></span><br />
Email <input type="text" id="email" name="email" onkeyup="checkBoxes()"><br />
<span id="inputbutton"></span>
</form>
</body>
</html>