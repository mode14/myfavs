<%@ page language="java" %>

<%@ include file="header.jsp" %>

<script type="text/javascript" charset="utf-8">
  $(document).ready(function() {
    $('#zebra').dataTable( {
      "sPaginationType": "full_numbers"	
    } );
  } );
</script>

<h1>Play List For: <%= request.getAttribute("full_name") %> </h1>

<%
    if(session.getAttribute("login")=="go") {
%>
<a href="Common?user_id=<%= request.getAttribute("user_id") %>">Find songs I have in common with this user</a><br /><br />
<%
    } 
%>


<%= request.getAttribute("table") %>

<%@ include file="footer.jsp" %>
