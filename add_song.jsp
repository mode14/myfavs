<%@ page language="java" %>

<%@ include file="header.jsp" %>

<%
    if (request.getParameter("error") != null)
    {
%>
<div class="error">Error: Error adding song.</div>
<%    
    }
%>

  <script>
  $(document).ready(function(){
    $("#error_form").validate({errorClass: "invalid"});
  });
  </script>

<h1>Add Song to Master Song List</h1>

<a href="import.jsp">Click here to import your iTunes playlist</a>

<form action="AddSong" id="error_form" method="post">
<table cellspacing=10>

<tr>
  <td>Title:</td><td><input class="required" type="text" name="song_name"></td>
</tr>
<tr>
  <td>Artist:</td><td><input class="required" type="text" name="artist"></td>
</tr>
<tr>
  <td>Album:</td><td><input class="required" type="text" name="album"></td>
</tr>
<tr>
  <td>Genre:</td><td><input class="required" type="text" name="genre"></td>
</tr>
<tr>
  <td colspan="2"><input type="submit" value="Add">
</tr>
  
</table>
</form>

<%@ include file="footer.jsp" %>