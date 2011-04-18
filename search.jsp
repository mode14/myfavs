<%@ page language="java" %>

<%@ include file="header.jsp" %>


<script>
  $(document).ready(function(){
    $("#song_name").autocomplete("AutoComplete?field=song_name",{selectFirst: false, max: 20, minChars: 2});
    $("#artist").autocomplete("AutoComplete?field=artist",{selectFirst: false, max: 20, minChars: 2});
    $("#album").autocomplete("AutoComplete?field=album",{selectFirst: false, max: 20, minChars: 2});
    $("#genre").autocomplete("AutoComplete?field=genre",{selectFirst: false, max: 20, minChars: 2});
  });
</script>

<h1>Search Master Song List</h1>

<%
    if (request.getParameter("error") != null)
    {
%>
<div class="error">Error: No results found.</div>
<%    
    }
%>

<form action="Search" method="post">
<table cellspacing=10>

<tr>
  <td>Song Name:</td><td><input type="text" name="song_name" id="song_name" maxsize="75"></td>
</tr>
<tr>
  <td>Artist:</td><td><input type="text" name="artist" id="artist" maxsize="75"></td>
</tr>
<tr>
  <td>Album:</td><td><input type="text" name="album" id="album" maxsize="75"></td>
</tr>
<tr>
  <td>Genre:</td><td><input type="text" name="genre" id="genre" maxsize="50"></td>
</tr>

<tr>
  <td colspan="2"><input type="submit" value="Search">
</tr>
  
</table>
</form>

<%@ include file="footer.jsp" %>