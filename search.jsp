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
  <th>Song Name:</th><td><input type="text" name="song_name" id="song_name" size="40" maxsize="75"></td>
</tr>
<tr>
  <th>Artist:</th><td><input type="text" name="artist" id="artist" size="40" maxsize="75"></td>
</tr>
<tr>
  <th>Album:</th><td><input type="text" name="album" id="album" size="40" maxsize="75"></td>
</tr>
<tr>
  <th>Genre:</th><td><input type="text" name="genre" id="genre" size="40" maxsize="50"></td>
</tr>

<tr>
  <td colspan="2"><input type="submit" value="Search">
</tr>
  
</table>
</form>

<%@ include file="footer.jsp" %>