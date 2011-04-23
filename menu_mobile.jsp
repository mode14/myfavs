<%@ page language="java" %>

<%@ include file="header.jsp" %>


      <ul>
      <li><a href="Main"><h2>Main</h2></a></li>
      <li><a href="Master"><h2>Master List</h2></a></li>
      <li><a href="Users"><h2>User Play Lists</h2></a></li>
      <li><a href="Search"><h2>Search</h2></a></li>
      
<%
    if(session.getAttribute("login")=="go") {
%>
      <li><a href="Edit"><h2>Edit Your List</h2></a></li>
      <li><a href="AddSong"><h2>Add Song</h2></a></li>
      <li><a href="Stats"><h2>Stats</h2></a></li>
<%
    }
%>

      </ul>
      
<%@ include file="footer.jsp" %>