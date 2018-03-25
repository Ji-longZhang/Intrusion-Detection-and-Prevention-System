<%@ include file="/header.jsp"%>

			<div class="clsTopNavigation clsFloatRight">
				<ul>
					<li ><a href="index.jsp">Home</a></li>
					<li><a href="register.jsp">Register</a></li>
					<li class="clsActive"><a href="forgetpassword.jsp">Forgot PWD</a></li>
					<li><a href="aboutus.jsp">About Us</a></li>
					<li><a href="contactus.jsp">Contact Us</a></li>
				</ul>
			</div>
		</div>
		<!--Ending Header-->
		<!--Starting Banner-->
		<div id="Banner" class="clearfix">
			<div class="clsBannerLeft clsFloatLeft">
				<p>
					<a href="#"><img src="images/banner_img.jpg" height="297"
						width="267" alt="" /></a>
				</p>
			</div>
			<div class="clsBannerRight clsFloatRight">
				<h2>Intrusion Detection and Prevention System</h2>
				<p>
					<span>Intrusion detection and prevention systems (IDPS)</span>: are
					primarily focused on identifying possible attacks, logging
					information about them, attempting to stop them, and reporting them
					to security administrators.
				</p>
<a href="https://en.wikipedia.org/wiki/Intrusion_detection_system" target="_blank" class="clsBannerReadMore">Read more</a>			</div>
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
				Forgot Password<span> Form</span>
			</h2>
			<p>
				<b>Get Your Password..</b>  Your password will be sent to your registered email id
			</p>
		</div>
		<div class="clsMain_Bottom clearfix clsNoBorder">
			<div class="clsMain_BottomLeft clsFloatLeft clsNoBorder">
			<%
							String message = (String)session.getAttribute("message");
							if(null != message){
						%>
							<font color="red"><%=message %> </font>
						<%
							}
							session.removeAttribute("message");
						%>
			<form name="registerForm" action="MainController?action=doforGetPwd" method="post">
				<p>
					<label>Email ID:</label>
				</p>
				<p>
					<input type="email" required autofocus name="emailId" size="30" />
				</p>
				
				<p>
					<div class="clsButton">
						<input class="clsClear" type="submit" value="Clear" name="clear" />
						<input class="clsSubmit" type="submit" value="Submit"
							name="submit" />
					</div>
					</form>
			</div>
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
