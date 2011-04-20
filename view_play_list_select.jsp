<%@ page language="java" %>

<%@ include file="header.jsp" %>


<h1>Select Playlist</h1>


<form action="ViewPlayList" method="post">
<input type="hidden" name="user_id" value="<%= request.getAttribute("user_id") %>">
<table cellspacing=10>


<tr>
  <td>Select Play List:</td>
  <td><select name="play_list_id">
  <%= request.getAttribute("select_box") %>
  </select>
  </td>
</tr>

<tr>
  <td></td><td><input type="submit" value="View"></td>
</tr>
  
</table>
</form>

<%@ include file="footer.jsp" %>