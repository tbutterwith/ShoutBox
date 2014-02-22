<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.ShoutBox.Tom.stores.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Followers</title>
</head>
<body>
<h1>Followers</h1>
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
</body>
</html>