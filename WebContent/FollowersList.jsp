<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.ShoutBox.Tom.stores.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Your Followers</title>
<link rel="stylesheet" type="text/css" href="http://ac32007.cloudapp.net:8080/ShoutBox/main.css">
<link rel="stylesheet" type="text/css" href="http://ac32007.cloudapp.net:8080/ShoutBox/feed.css">
</head>
<body>
<div class="header">
		<div class="title">
			Shout<b>Box</b>
		</div>
		<div class="links">
			<a href="http://ac32007.cloudapp.net:8080/ShoutBox/feed/all">All </a>
			<a href="http://ac32007.cloudapp.net:8080/ShoutBox/feed">Feed </a>
			<a href="http://ac32007.cloudapp.net:8080/ShoutBox/profile">Profile </a>
			<a href="http://ac32007.cloudapp.net:8080/ShoutBox/follower">Followers </a>
			<a href="http://ac32007.cloudapp.net:8080/ShoutBox/logout">Logout</a>
		</div>
	</div>
<div class="body">
<h1>You're Following</h1>
<%
Set<String> followers = (Set<String>) request.getAttribute("Followers");
if (followers.isEmpty()){
%>
<p>You don't follow anyone</p>
<%
	}else{
%>


<%
	Iterator<String> iterator;


iterator = followers.iterator();
while (iterator.hasNext()){
String ss = (String)iterator.next();
%>
<h3><%= ss %></h3>
<%

}
}
%>
</div>
</body>
</html>