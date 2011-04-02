<%
    if(session.getAttribute("browser")=="mobile") {
%>
    <%@ include file="header_mobile.jsp" %>
<%
    } else {
%>
    <%@ include file="header_computer.jsp" %>
<%
    }
%>