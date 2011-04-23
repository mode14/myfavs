<%@ page language="java" %>

<%@ include file="header.jsp" %>

<%
    if (request.getParameter("error") != null)
    {
%>
<div class="error">Error: Error registering.</div>
<%    
    }
%>

  <script>
  $(document).ready(function(){
    $("#error_form").validate({
      errorClass: "invalid",
      rules: {
        user: {
          required: true,
          email: true
        },
        full_name: "required",
        password: "required",
        password2: {
          equalTo: "#password"
        }
      }
    });
  });
  </script>

<h1>Register New Account</h1>

<form action="Signup" id="error_form" method="post">
<table cellspacing=10>

<tr>
  <th>Email:</th><td><input type="text" name="user" maxsize="20"></td>
</tr>
<tr>
  <th>Password:</th><td><input type="password" name="password" id="password" maxsize="20"></td>
</tr>
<tr>
  <th>Confirm Password:</th><td><input type="password" name="password2" id="password2" maxsize="20"></td>
</tr>
<tr>
  <th>Full Name:</th><td><input type="text" name="full_name" maxsize="40"></td>
</tr>
<tr>
  <td colspan="2"><input type="submit" value="Signup">
</tr>
  
</table>
</form>

<%@ include file="footer.jsp" %>