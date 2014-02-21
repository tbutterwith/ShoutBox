<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.ShoutBox.Tom.stores.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Tweets</title>
</head>
<body>

<h1>Tweet</h1>
<%
	System.out.println("In render");
List<ShoutStore> lTweet = (List<ShoutStore>)request.getAttribute("Tweets");
if (lTweet==null){
%>
<p>No Tweet found</p>
<%
	}else{
%>


<%
	Iterator<ShoutStore> iterator;


iterator = lTweet.iterator();
while (iterator.hasNext()){
ShoutStore ts = (ShoutStore)iterator.next();
%>
<a href="/ac32007examples/Tweet/<%=ts.getUser() %>" ><%=ts.getTweet() %></a><br/><%

}
}
%>

</body>
</html>