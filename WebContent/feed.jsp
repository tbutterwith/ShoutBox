<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.ShoutBox.Tom.stores.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>ShoutBox Feed</title>

<link rel="stylesheet" type="text/css" href="http://localhost:8080/ShoutBox/main.css">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/ShoutBox/feed.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
	function deleteShout(uuid)
	{
		$.ajax({
		    url: 'http://localhost:8080/ShoutBox/feed/' + uuid,
		    type: 'DELETE',
		    success: function(result) {
		    	location.reload();
		    }
		});
	}
	function startFollowing(user)
	{
		$.ajax({
			url: 'http://localhost:8080/ShoutBox/follower/' + user,
			type: 'POST',
			success: function(result) {
				window.location = "http://localhost:8080/ShoutBox/follower";
			}
		});
	}
	function stopFollowing(user)
	{
		$.ajax({
			url: 'http://localhost:8080/ShoutBox/follower/' + user,
			type: 'DELETE',
			success: function(result) {
				location.reload();
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
<form name="newShout" action="feed" method="post">
<textarea name="shoutText" cols="60" rows="3" maxlength="140"></textarea><br />
<div style="width: 475px; text-align: right;"><input type="submit" value="Shout"></div>
</form>
<%
UserStore uS = (UserStore) request.getSession().getAttribute("user");
String username = uS.getUsername();
%><div style="width: 475px; text-align: center;"><h2><%=username %>'s <span style="color: #FFFFFF;">Feed</span></h2></div>
<div class="feed">

<%
	System.out.println("In render");
List<ShoutStore> lShout = (List<ShoutStore>) request.getAttribute("Shouts");
if (lShout.isEmpty()){
%>
<p>No Shouts found</p>
<%
	}else{
%>


<%
	Iterator<ShoutStore> iterator;


iterator = lShout.iterator();
while (iterator.hasNext()){
ShoutStore ss = (ShoutStore)iterator.next();
%>
<a href="/ShoutBox/feed/<%= ss.getUser() %>" class="userLink"><%= ss.getUser() %></a><br />
<a href="/ShoutBox/feed/<%=ss.getUuid() %>" class="shoutLink"><%=ss.getShout() %></a><br/>
<% if(username.equals(ss.getUser())) 
{
	
%>
<button type="button" onclick="deleteShout(&quot;<%= ss.getUuid() %>&quot;, &quot;<%= ss.getUser() %>&quot;)">Delete this shout?</button><br /><br /><br /> 
<% } else { %>
<button onclick="startFollowing(&quot;<%= ss.getUser() %>&quot;)">Follow <%= ss.getUser() %>'s shouts?</button>
<button onclick="stopFollowing(&quot;<%= ss.getUser() %>&quot;)">Stop following <%= ss.getUser() %>'s shouts?</button>
<br /><br /><br />
<%
}
}
}
%>
</div>
</div>
</body>
</html>