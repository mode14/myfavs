<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Play List</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%
	if(session.getAttribute("lights")=="off") {
%>
	<link href="static/css/mse.css" rel="stylesheet" type="text/css" />
<%
	} else {
%>
	<link href="static/css/new.css" rel="stylesheet" type="text/css" />
<%
	}
%>
  
  <script src="static/js/main.js"></script>

  <link rel="shortcut icon" href="static/images/favicon.ico" type="image/x-icon" /> 
</head>
<body>


<div id="layout">
  <div id="header">  
  <div id="greeting">
	<!-- HEADER - put any utilities such as sign in here -->
<%
    if(session.getAttribute("login")=="go") {
%>
Welcome: <%= session.getAttribute("full_name") %> | <a href="logout.jsp">Logout</a>
<%
    } else {
%>
<a href="Signup">Signup</a> | <a href="Login">Login</a>
<%
    }
%>
  </div>

	<!-- LINK TO HOMEPAGE HERE -->
<%
	if(session.getAttribute("lights")=="off") {
%>
	<a href = ""><img src = "static/images/LogoDark.jpg" width = "150" height = "150", class = "header"></a>
<%
	} else {
%>
	<a href = ""><img src = "static/images/Logo.jpg" width = "150" height = "150", class = "header"></a>
<%	
	}
%>
  </div>
  <div id="navbar">
	<a href ="Main" ><img src = "static/images/buttons/MainRegular.jpg" height = "50" width = "150" name = "Button" onMouseOver = "this.src = 'static/images/buttons/MainSelected.jpg'" onMouseOut = "this.src = 'static/images/buttons/MainRegular.jpg'"></a><br>
	<a href ="Master" ><img src = "static/images/buttons/MasterSongListRegular.jpg" height = "50" width = "150" name = "Button" onMouseOver = "this.src = 'static/images/buttons/MasterSongListSelected.jpg'" onMouseOut = "this.src = 'static/images/buttons/MasterSongListRegular.jpg'"></a><br>
	<a href ="Users" ><img src = "static/images/buttons/UserPlayListsRegular.jpg" height = "50" width = "150" name = "Button" onMouseOver = "this.src = 'static/images/buttons/UserPlayListsSelected.jpg'" onMouseOut = "this.src = 'static/images/buttons/UserPlayListsRegular.jpg'"></a><br>
	<a href ="Search" ><img src = "static/images/buttons/SearchRegular.jpg" height = "50" width = "150" name = "Button" onMouseOver = "this.src = 'static/images/buttons/SearchSelected.jpg'" onMouseOut = "this.src = 'static/images/buttons/SearchRegular.jpg'"></a><br>

<%
    if(session.getAttribute("login")=="go") {
%>

	<a href ="Edit" ><img src = "static/images/buttons/EditPlayListRegular.jpg" height = "50" width = "150" name = "Button" onMouseOver = "this.src = 'static/images/buttons/EditPlayListSelected.jpg'" onMouseOut = "this.src = 'static/images/buttons/EditPlayListRegular.jpg'"></a><br>
  <a href ="AddSong" ><img src = "static/images/buttons/AddSongRegular.jpg" height = "50" width = "150" name = "Button" onMouseOver = "this.src = 'static/images/buttons/AddSongSelected.jpg'" onMouseOut = "this.src = 'static/images/buttons/AddSongRegular.jpg'"></a><br>
<%
    }
%>
	<a href ="Stats" ><img src = "static/images/buttons/StatsRegular.jpg" height = "50" width = "150" name = "Button" onMouseOver = "this.src = 'static/images/buttons/StatsSelected.jpg'" onMouseOut = "this.src = 'static/images/buttons/StatsRegular.jpg'"></a><br>
  </div>


  <div id="content">
	<!-- MAIN CONTENT GOES HERE -->
	  <div style="padding:8px;">