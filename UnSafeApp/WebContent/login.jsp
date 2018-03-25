<form name="loginForm"
	action="LoginServlet"
	method="post">
	<table align="center" width=40%>
		<tr>
			<td bgcolor=#006699 width=100%><font face="verdana"
				color=#FFFFFF size=4><strong>Login</strong></font></td>
		</tr>
	</table>

	<table align="center" width=40%>
		<tr>
			<td bgcolor=#e1eff0 align="right"><font face="verdana"
				color=#006699 size=2>Login Name</font></td>
			<td bgcolor=#e1eff0><input type="text" name="uname" size=25></input></td>
		</tr>
		<tr>
			<td bgcolor=#e1eff0 align="right"><font face="verdana"
				color=#006699 size=2>Password</font></td>
			<td bgcolor=#e1eff0><input type="password" name="password"
				size=15></input></td>
		</tr>
		<tr>
			<td bgcolor=#e1eff0 align="right"><font face="verdana"
				color=#006699 size=2>Employee Type</font></td>
			<td bgcolor=#e1eff0><select name="empType">
					<option value="Admin">Admin</option>
					<option value="User">User</option>
			</select></td>
		</tr>
	</table>
	<table align="center" width=40%>
		<tr>
			<td bgcolor=#e1eff0 align="center"><input type="submit"
				value="  Login  "></td>
		</tr>
	</table>
</form>