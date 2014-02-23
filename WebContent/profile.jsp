<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.ShoutBox.Tom.stores.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Your Profile</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/ShoutBox/main.css">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/ShoutBox/feed.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
var check = false;
var emailcheck = false;
function checkEmail()
{
	var email = document.getElementById("primaryEmail");
	
	if(document.getElementById("primaryEmail") == "")
		emailcheck = false;
	else
		emailcheck = true;
	
	comparePasswords();
}
function comparePasswords()
{
	var password = document.getElementById("newPassword").value;
		var retype = document.getElementById("passwordRetype").value;

		if (password != retype) {
			document.getElementById("passwordError").innerHTML = "Passwords do not match";
			check = false;
		} else {
			document.getElementById("passwordError").innerHTML = "";
			check = true;
		}
		
		if(check == true && emailcheck == true)
				document.getElementById("submitButton").innerHTML = "<input type=\"submit\" value=\"Save changes\"/>";
		else
			document.getElementById("submitButton").innerHTML = "";
}
function deleteUser(user)
{
	$.ajax({
	    url: 'http://localhost:8080/ShoutBox/profile/' + user,
	    type: 'DELETE',
	    success: function(result) {
	    	window.location = "http://localhost:8080/ShoutBox/";
	    }
	});
}
</script>
</head>
<body>
<div class="header">
		<div class="title">
			Shout<b>Box</b>
		</div>
		<div class="links">
			<a href="http://localhost:8080/ShoutBox/feed/all">All </a>
			<a href="http://localhost:8080/ShoutBox/feed">Feed </a>
			<a href="http://localhost:8080/ShoutBox/profile">Profile </a>
			<a href="http://localhost:8080/ShoutBox/follower">Followers </a>
			<a href="http://localhost:8080/ShoutBox/logout">Logout</a>
		</div>
	</div>
<div class="body">
<h1>Profile</h1>
<%
ProfileStore profile = (ProfileStore) request.getAttribute("profile");
if (profile.getUsername() == null){
%>
<p>Error finding profile</p>
<%
	}else{
%>
<h3>Username: </h3><%= profile.getUsername() %>
<%
if (profile.getEmail() != null)
{
	
%>
<form name ="details" action="profile" method="POST">
<h3>Change Password</h3>
<table>
<tr>
<td style="width: 140px;">Current Password</td><td><input type="password" name="password" id="password" onKeyUp="checkEmail()"/></td>
</tr>
</table>
<%
MessageStore message = (MessageStore) request.getAttribute("errorMessage");
if(message != null)
{
%><%=message.getMessage() %><br/>
<%} %>
<table>
<tr>
<td style="width: 140px;">New Password</td><td><input type="password" name="newPassword" id="newPassword" /></td>
</tr>
<tr>
<td style="width: 140px;">Retype Password</td><td><input type="password" name="passwordRetype" id="passwordRetype" onKeyUp="comparePasswords()"/></td>
</tr>
</table>
<span id="passwordError" class="error"></span><br />
<table>
<tr>
<td style="width: 140px;">Primary Email</td><td><input type="text" name="primaryEmail" id="primaryEmail" value="<%= profile.getEmail() %>" onKeyUp="checkEmail()"></td>
</tr>
<tr>
<%
String email2 = "";
if(profile.getSecondaryEmail() != null)
	email2 = profile.getSecondaryEmail();
	%>
<td style="width: 140px;">Secondary Email</td><td><input type="text" name="secondaryEmail" id="secondaryEmail" value="<%= email2 %>" onKeyUp="checkEmail()"></td>
</tr>
</table>
<span id="submitButton"></span>

</form>
<button onclick="deleteUser(&quot;<%=profile.getUsername()%>&quot;)">Delete my account</button>
<%
}
}
%>

</div>
</body>
</html>