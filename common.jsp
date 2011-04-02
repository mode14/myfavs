<%@ page language="java" %>

<%@ include file="header.jsp" %>

<h1>Songs in common with: <%= request.getAttribute("full_name") %> </h1>
<%
    if (request.getParameter("error") != null)
    {
%>
<div class="error">Error: No common songs found.</div>
<%    
    }
%>


<%
    if (request.getAttribute("table") != null)
    {
%>
        <%= request.getAttribute("table") %>
<%
    }
%>



<%@ include file="footer.jsp" %>
