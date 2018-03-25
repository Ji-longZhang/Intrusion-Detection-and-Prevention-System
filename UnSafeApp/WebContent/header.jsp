<%@page language="java"%> 

<%@ include file="styles.css" %>

<table width=100% border="0">
  <tr>
    <td width="15%" align="left" valign="top"><img src="header.jpg" width="92" height="61"></td>
    <td width="60%" align="left"><h1><font face="verdana" color=#006699>Effort Tracking System</h1></font></td>
    <td width="25%" align="center" valign="top">
		<table border="0">
			<tr> 
			  <td align="center"> 
				  <% if(null != (String)session.getValue("uname")) {%>
				  <font face="verdana" size=2 color=#006699><%=session.getValue("uname")%>@<%=request.getRemoteHost()%></font></td>
			</tr>
			<tr> 
			  <td><font face="verdana" size=2 color=#006699><a href="logout.jsp">Log-out</a></font> | <%=(String)session.getValue("userType") %>
				<%} else {%> <font face="verdana"  size=2 color=#006699>You are not Logged-In</font> <%}%> </td>
			</tr>
		  </table>
    </td>
</table>


