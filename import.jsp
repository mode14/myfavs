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

<div class="note">
To import songs, make sure your Library.xml is in the WEB-INF directory.<br />
Make sure you generate this document in iTunes from clicking File -> Library -> Export Library... Then click the import button below.
</div>

<form action="Import" id="error_form" method="get">
<table cellspacing=10>

<tr>
  <td colspan="2"><input type="submit" value="Import">
</tr>
  
</table>
</form>

<%@ include file="footer.jsp" %>