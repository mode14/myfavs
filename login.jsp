<%@ page language="java" %>

<%@ include file="header.jsp" %>

<%
    if (request.getParameter("error") != null)
    {
%>
<div class="error">Error: Invalid username/password.  Please try again.</div>
<%    
    }
%>

<h1>Login To Your Account</h1>

<form action="Login" method="post">
<table cellspacing=10>

<tr>
  <th>Email:</td><th><input type="text" name="user"></td>
</tr>
<tr>
  <th>Password:</td><th><input type="password" name="password"></td>
</tr>
<tr>
  <td colspan="2"><input type="submit" value="submit">
</tr>
  
</table>
</form>

New User? <a href="Signup">Click here</a> to register your account.

<%@ include file="footer.jsp" %>