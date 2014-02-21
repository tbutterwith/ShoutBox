<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.ShoutBox.Tom.stores.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>ShoutBox Feed</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
	function deleteShout(uuid)
	{
		$.ajax({
		    url: 'feed/' + uuid,
		    type: 'DELETE',
		    success: function(result) {
		    	location.reload();
		    }
		});
	}
</script>
</head>
<body>

<form name="newShout" action="feed" method="post">
<textarea name="shoutText" cols="60" rows="3" maxlength="140"></textarea><br />
<input type="submit" value="Shout">
</form>

<h1>Tweet</h1>
<%
	System.out.println("In render");
List<ShoutStore> lShout = (List<ShoutStore>) request.getAttribute("Shouts");
if (lShout==null){
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
<h3><%= ss.getUser() %></h3>
<a href="/ShoutBox/feed/<%=ss.getUser() %>" ><%=ss.getShout() %></a><br/>
<button type="button" onclick="deleteShout(<%= ss.getUuid() %>)">Delete</button> 
<%

}
}
%>

</body>
</html>