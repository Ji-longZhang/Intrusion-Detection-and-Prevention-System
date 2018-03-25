
<%@include file="header.jsp"%>
	<%@include file="pm_header.jsp" %>
	
<%
	String userType = (String)session.getAttribute("userType");
%>
<form name="loginForm"
	action="NewEmpServlet?userType=<%=userType %>"
	method="post">
	<table align="center" width=40%>
		<tr>
			<td bgcolor=#006699 width=100%><font face="verdana"
				color=#FFFFFF size=4><strong>New Employee</strong></font></td>
		</tr>
	</table>

	<table align="center" width=40%>
		<tr>
			<td bgcolor=#e1eff0 align="right"><font face="verdana"
				color=#006699 size=2>Name</font></td>
			<td bgcolor=#e1eff0><input type="text" name="fullName" size=25></input></td>
		</tr>
		
		<tr>
			<td bgcolor=#e1eff0 align="right"><font face="verdana"
				color=#006699 size=2>Email</font></td>
			<td bgcolor=#e1eff0><input type="text" name="email" size=25></input></td>
		</tr>
		
		<tr>
			<td bgcolor=#e1eff0 align="right"><font face="verdana"
				color=#006699 size=2>Salary</font></td>
			<td bgcolor=#e1eff0><input type="text" name="salary" size=25></input></td>
		</tr>
		<tr>
			<td bgcolor=#e1eff0 align="right"><font face="verdana"
				color=#006699 size=2>Login Name</font></td>
			<td bgcolor=#e1eff0><input type="text" name="userName" size=25></input></td>
		</tr>
		<tr>
			<td bgcolor=#e1eff0 align="right"><font face="verdana"
				color=#006699 size=2>User Login Password</font></td>
			<td bgcolor=#e1eff0><input type="password" name="password" size=25></input></td>
		</tr>
	</table>
	<table align="center" width=40%>
		<tr>
			<td bgcolor=#e1eff0 align="center"><input type="submit"
				value="  Add Employee  "></td>
		</tr>
	</table>
</form>