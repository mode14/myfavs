<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Play List</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
  <link href="static/css/style.css" rel="stylesheet" type="text/css" />

  <script src="static/js/main.js"></script>

  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon" /> 
</head>

<body>

<!-- wrapper -->
<div class="rapidxwpr floatholder">

  <!-- header -->
  <div id="header">
  
    <div id="greeting">
    
    <!-- logo -->
    <a href="/"><img id="logo" src="static/images/logo_v2.png" width=200 height=44 alt="Logo" /></a>
    <!-- / logo -->
    
<%
    if(session.getAttribute("login")=="go") {
%>
Welcome: <%= session.getAttribute("full_name") %> | <a href="logout.jsp">Logout</a>
<%
    } else {
%>
<a href="Login">Login</a>
<%
    }
%>

    </div>

    <!-- logo -->
    <a href="/"></a>
    <!-- / logo -->
    
    <!-- topmenu -->
    <div id="topmenu">
      <ul>
      <li><a href="Main"><span>Main</span></a></li>
      <li><a href="Master?page=1"><span>Master List</span></a></li>
      <li><a href="Users"><span>User Play Lists</span></a></li>
      <li><a href="Search"><span>Search</span></a></li>
      
<%
    if(session.getAttribute("login")=="go") {
%>
      <li><a href="Edit"><span>Edit Your List</span></a></li>
      <li><a href="AddSong"><span>Add Song</span></a></li>
      <li><a href="Stats"><span>Stats</span></a></li>
<%
    }
%>

      </ul>
    </div>
    <!-- / topmenu -->
  
  </div>
  <!-- / header -->
  
  <!-- main body -->
  <div id="middle">