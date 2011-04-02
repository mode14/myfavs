<%@ page language="java" %>

<%@ include file="header.jsp" %>

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
