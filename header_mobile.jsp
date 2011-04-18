<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Play List</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
  <link href="static/css/style_mobile.css" rel="stylesheet" type="text/css" />

  <script src="static/js/main.js"></script>
  <script>
  window.addEventListener('load', function() {
    setTimeout(scrollTo, 0, 0, 1);
    }, false);
  </script>
  
  <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">

  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon" /> 
</head>

<body>

<!-- wrapper -->
<div id="container">

  <!-- header -->
  <div id="header">
  
    <div id="greeting">
    
<%
    if(session.getAttribute("login")=="go") {
%>
Welcome Mobile User: <%= session.getAttribute("full_name") %> | <a href="logout.jsp">Logout</a>
<%
    } else {
%>
<a href="Signup">Signup</a> | <a href="Login">Login</a>
<%
    }
%>

    </div>

    <!-- logo -->
    <a href="/"></a>
    <!-- / logo -->
    
    <br /><br /><br /><h1><a href="menu_mobile.jsp">Main Menu</a></h1>
  
  </div>
  <!-- / header -->
  
  <!-- main body -->
  <div id="content">