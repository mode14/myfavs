<%@ page language="java" %>

<%@ include file="header.jsp" %>

<%= request.getAttribute("highlight") %>

<h1>Search Results</h1>

<%= request.getAttribute("table") %>

<%@ include file="footer.jsp" %>
