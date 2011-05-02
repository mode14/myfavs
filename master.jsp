<%@ page language="java" %>

<%@ include file="header.jsp" %>

<script type="text/javascript" charset="utf-8">
  $(document).ready(function() {
    $('#zebra').dataTable( {
      "sPaginationType": "full_numbers",
      
    } );
  } );
</script>

<h1>Master Song List</h1>

<%= request.getAttribute("table") %>


<%@ include file="footer.jsp" %>
