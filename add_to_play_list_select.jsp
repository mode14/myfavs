<%@ page language="java" %>

<%@ include file="header.jsp" %>

<h1>Select Playlist</h1>


<form action="AddToPlayList" method="post">
<input type="hidden" name="song_id" value="<%= request.getAttribute("song_id") %>">
<input type="hidden" name="user_id" value="<%= request.getAttribute("user_id") %>">
<p class="note">
Select a playlist below to add this song to.  You can also create a new playlist with the text box below.
</p>

<table cellspacing=10>

<%
    if(request.getAttribute("select_box")!="") {
%>
<tr>
  <th>Existing PlayList:</th>
  <th><select name="existing">
  <%= request.getAttribute("select_box") %>
  </select>
  </th>
</tr>
<%
}
%>
<tr>
  <td>Create New Playlist:</td><td><input type="text" name="create" maxsize="75"></td>
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