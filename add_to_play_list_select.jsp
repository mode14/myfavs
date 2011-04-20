<%@ page language="java" %>

<%@ include file="header.jsp" %>

<h1>Select Playlist</h1>


<form action="AddToPlayList" method="post">
<input type="hidden" name="song_id" value="<%= request.getAttribute("song_id") %>">
<input type="hidden" name="user_id" value="<%= request.getAttribute("user_id") %>">
<table cellspacing=10>

<%
    if(request.getAttribute("select_box")!="") {
%>
<tr>
  <td>Existing PlayList:</td>
  <td><select name="existing">
  <%= request.getAttribute("select_box") %>
  </select>
  </td>
</tr>
<%
}
%>
<tr>
  <td>Create Playlist:</td><td><input type="text" name="create" maxsize="75"></td>
</tr>

<tr>
  <td>&nbsp;</td><td></td>
</tr>
<tr>
  <td></td><td><input type="submit" value="Add"></td>
</tr>
  
</table>
</form>

<%@ include file="footer.jsp" %>