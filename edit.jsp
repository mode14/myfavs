<%@ page language="java" %>

<%@ include file="header.jsp" %>

<script type="text/javascript" charset="utf-8">
  $(document).ready(function() {
    $('#zebra').dataTable( {
      "sPaginationType": "full_numbers"	
    } );
  } );
</script>

<h1>Edit Your Play List</h1>

<%= request.getAttribute("table") %>

<%@ include file="footer.jsp" %>
