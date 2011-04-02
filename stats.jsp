<%@ page language="java" %>

<%@ include file="header.jsp" %>

<h1>Stats</h1>

<table class="stripe">
<tr><td>Most Popular Song</td><td><%= request.getAttribute("most_popular_song") %></td></tr>
<tr class="alt"><td>Total Songs</td><td><%= request.getAttribute("total_songs") %></td></tr>
<tr><td>Total Users</td><td><%= request.getAttribute("total_users") %></td></tr>

</table>

<br /><br />

<center>
<img src="<%= request.getAttribute("genres_url") %>">
</center>

<br /><br />

<center>
<img src="<%= request.getAttribute("users_url") %>">
</center>

<%@ include file="footer.jsp" %>
