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

<%
    if(session.getAttribute("admin")=="1") {
%>
<a href="import.jsp">Click here to import your iTunes playlist</a>
<%
    }
%>

<form action="AddSong" id="error_form" method="post">
<table cellspacing=10>

<tr>
  <th>Title:</th><td><input class="required" type="text" name="song_name" maxsize="75"></td>
</tr>
<tr>
  <th>Artist:</th><td><input class="required" type="text" name="artist" maxsize="75"></td>
</tr>
<tr>
  <th>Album:</th><td><input class="required" type="text" name="album" maxsize="75"></td>
</tr>
<tr>
  <th>Genre:</th><td><input class="required" type="text" name="genre" maxsize="75"></td>
</tr>
<tr>
  <td colspan="2"><input type="submit" value="Add">
</tr>
  
</table>
</form>

<%@ include file="footer.jsp" %>