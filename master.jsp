<%@ page language="java" %>

<%@ include file="header.jsp" %>

<h1>Master Song List</h1>

<%= request.getAttribute("table") %>

<%
    if(request.getAttribute("page_links")!="") {
%>
<br />
<div style="float: right">
<%= request.getAttribute("page_links") %>
</div>
<br /><br />
<%
    } 
%>

<%@ include file="footer.jsp" %>
