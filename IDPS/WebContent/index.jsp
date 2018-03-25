<%@ include file="/header.jsp"%>


<div class="clsTopNavigation clsFloatRight">
	<ul>
		<li class="clsActive"><a href="index.jsp">Home</a></li>
		<li><a href="register.jsp">Register</a></li>
		<li><a href="forgetpassword.jsp">Forgot PWD</a></li>
		<li><a href="aboutus.jsp">About Us</a></li>
		<li><a href="contactus.jsp">Contact Us</a></li>
	</ul>
</div>
</div>
<!--Ending Header-->`
<!--Starting Banner-->
<div id="Banner" class="clearfix">
	<head profile="http://www.w3.org/2005/10/profile">
			<link rel="icon" 
      		type="image/png" 
      		href="C:\Users\hp\Desktop\banner_img.jpg">
    </head>
	<div class="clsBannerLeft clsFloatLeft">
		<p>
			<a href="#"><img src="images/banner_img.jpg" height="297"
				width="267" alt="" /></a>
		</p>
	</div>
	<div class="clsBannerRight clsFloatRight">
		<h2>Intrusion Detection and Prevention System</h2>
			<!-- 	<h2> for Multitier Web Application  <span>   </span> </h2> -->
		<p>
			<span>Intrusion detection and prevention systems (IDPS)</span>: are
			primarily focused on identifying possible attacks, logging
			information about them, attempting to stop them, and reporting them
			to security administrators.
		</p>
		<a href="https://en.wikipedia.org/wiki/Intrusion_detection_system" target="_blank" class="clsBannerReadMore">Read more</a>
	</div>
</div>
<!--Ending Banner-->
<!--Starting content-->
<div id="content" class="clearfix">
	<!--Starting Sidebar-->
	<div id="sidebar">
		<div class="sidebar1 clsFloatRight">
			<%@ include file="/welcomeblock.jsp"%>
			<!--Ending Sidebar-->
		</div>
	</div>
	<!--Starting Main-->
	<div id="main">
		<div class="clsContact">
			<h2>
				Login<span> Form</span>
			</h2>
			<p>
				<b>Login,</b> to secure your web application from several attacks
				and secure your web hosting today.
			</p>
		</div>
		<div class="clsMain_Bottom clearfix clsNoBorder">
			<form name="loginForm" action="MainController?action=doLogin"
				method="post">
				<div class="clsMain_BottomLeft clsFloatLeft clsNoBorder">
					<%
						String message = (String) session.getAttribute("message");
						if (null != message) {
					%>
					<font color="red"><%=message%> </font>
					<%
						}
						session.removeAttribute("message");
					%>


					<p>
						<label>User name:</label>
					</p>
					<p>
						<input type="text" name="userName" size="30" autofocus required/>
					</p>
					<p>
						<label>Password:</label>
					</p>
					<p>
						<input type="password" name="password" size="30" required/>
					</p>
					<p>
					<div class="clsButton">
						<input class="clsSubmit" type="submit" value="Login" name="submit"/>
						<input class="clsSubmit" type="reset" value="Clear" name="clear" />
					</div>
				</div>
			</form>
			</p>
		</div>
	</div>
</div>
<!--Ending main-->
</div>
<!--Ending Content-->
<!--Ending Container-->
<!--Starting Footer-->


<%@ include file="/footer.jsp"%>
<script language="javascript">
	function validate() {
		if(document.loginForm.userName.value == "") {
			alert("Please enter User Name");
		} else if(document.loginForm.password.value == "") {
			alert("Please enter Password");
		} else {
			loginForm.submit();
		} 
	}
</script>
