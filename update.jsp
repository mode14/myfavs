<%@ page language="java" %>

<%@ include file="header.jsp" %>

<%
    if (request.getParameter("error") != null)
    {
%>
<div class="error">Error: Error updating song.</div>
<%    
    }
%>

  <script>
  $(document).ready(function(){
    $("#error_form").validate({errorClass: "invalid"});
  });
  </script>

<h1>Update Song</h1>

<form action="Update" id="error_form" method="post">
<input type="hidden" name="song_id" value="<%= request.getAttribute("song_id") %>">
<table cellspacing=10>

<tr>
  <td>Title:</td><td><input class="required" type="text" name="song_name" value="<%= request.getAttribute("song_name") %>"></td>
</tr>
<tr>
  <td>Artist:</td><td><input class="required" type="text" name="artist" value="<%= request.getAttribute("artist") %>"></td>
</tr>
<tr>
  <td>Album:</td><td><input class="required" type="text" name="album" value="<%= request.getAttribute("album") %>"></td>
</tr>
<tr>
  <td>Genre:</td><td><input class="required" type="text" name="genre" value="<%= request.getAttribute("genre") %>"></td>
</tr>
<tr>
  <td colspan="2"><input type="submit" value="Update">
</tr>
  
</table>
</form>

<%@ include file="footer.jsp" %>