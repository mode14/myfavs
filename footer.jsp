<%
    if(session.getAttribute("browser")=="mobile") {
%>
    <%@ include file="footer_mobile.jsp" %>
<%
    } else {
%>
    <%@ include file="footer_computer.jsp" %>
<%
    }
%>