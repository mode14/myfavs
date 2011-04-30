<%@ page language="java" %>

<%@ include file="header.jsp" %>


<%
    if (request.getParameter("error") != null)
    {
%>
<h1>No Songs In Common </h1>
<div class="error">Sorry, you two have no common songs.</div>
<%    
    }
    else
    {
%>
<h1>Songs in common with: <%= request.getAttribute("full_name") %> </h1>
<% } %>

<%
    if (request.getAttribute("table") != null)
    {
%>
        <%= request.getAttribute("table") %>
<%
    }
%>



<%@ include file="footer.jsp" %>
