    </div>
   </div>

<div class="clear"></div>
<br /><br />

  <div id="footer">
    <center><br /><br />
      Copyright &copy; 2011 Our Group.<br /><br /><br />
    </center>
  </div>
  <div id="lights">
<%
	if(session.getAttribute("lights")=="off") {
%>
	<a href="ChangeLights">Turn on the lights</a>
<%
	} else {
%>
	<a href="ChangeLights">Turn off the lights</a>
<%
	}
	int number = request.getRequestURL ().lastIndexOf ("/"); 
	int length = request.getRequestURL ().length ()-4;
	String url = request.getRequestURL().substring (number,length);
	url = url.substring(1,2).toUpperCase () + url.substring (2);
	session.setAttribute ("url",url);
%>

  </div>
</div>
</body>
</html>
