<%@ include file="/header.jsp"%>


			<div class="clsTopNavigation clsFloatRight">
				<ul>
					<li ><a href="index.jsp">Home</a></li>
					<li class="clsActive"><a href="register.jsp">Register</a></li>
					<li><a href="forgetpassword.jsp">Forgot PWD</a></li>
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
        <h2>Register<span> Form</span></h2>
        <p><b>Register now to secure your web application </p>
      </div>
      <%
							String message = (String)session.getAttribute("message");
							if(null != message){
						%>
							<font color="red"><%=message %> </font>
						<%
							}
							session.removeAttribute("message");
						%>
							<form name="registerForm" action="MainController?action=doRegister" method="post">
      <div class="clsMain_Bottom clearfix clsNoBorder">
        <div class="clsMain_BottomLeft clsFloatLeft clsNoBorder">
          <p>
            <label>First name:</label>
          </p>
          <p>
            <input type="text" size="30" name="firstName" autofocus required/>
          </p>
          <p>
            <label>Last Name:</label>
          </p>
          <p>
            <input type="text" size="30" required name="lastName"/>
          </p>
          <p>
            <label>Email Address</label>
          </p>
          <p>
            <input type="email" size="30" required name="email"/>
          </p>

		   <p>
            <label>Mobile</label>
          </p>
          <p>
            <input type="text" size="30" required name="mobile"/>
          </p>
        </div>
        <div class="clsMain_BottomRight clsFloatRight">
         <p>
            <label>Birth Date</label>
          </p>
          <p>
            <input type="date" size="30" required name="birthDate"/>
          </p>
          <p>
            <label>Address:</label>
          </p>
          <p>
            <input type="text" size="30" required  name="address"/>
          </p>
          <p>
            <label>User Name:</label>
          </p>
          <p>
            <input type="text" size="30" required name="userName"/>
          </p>
          <p>
            <label>Password</label>
          </p>
          <p>
            <input type="password" size="30" required name="password"/>
          </p>
		  
		  <P>
          <div class="clsButton">
		  <input class="clsSubmit" type="submit" value="Register" name="submit" />
		  <input class="clsSubmit" type="Reset" value="Clear" name="clear"/></div>
		  </p>
        </div>
      </div>
      </form>
    </div>
  </div>
<!--Ending main-->
</div>
<!--Ending Content-->
<!--Ending Container-->
<!--Starting Footer-->
<%@ include file="/footer.jsp"%>
