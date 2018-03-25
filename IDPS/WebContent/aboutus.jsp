<%@ include file="/header.jsp"%>


			<div class="clsTopNavigation clsFloatRight">
				<ul>
					<li ><a href="index.jsp">Home</a></li>
					<li><a href="register.jsp">Register</a></li>
					<li><a href="forgetpassword.jsp">Forgot PWD</a></li>
					<li  class="clsActive"><a href="aboutus.jsp">About Us</a></li>
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
<a href="https://en.wikipedia.org/wiki/Intrusion_detection_system" target="_blank" class="clsBannerReadMore">Read more</a></div>
		</div>
		<!--Ending Banner-->
<!--Starting content-->
<div id="content" class="clearfix">
	<!--Starting Sidebar-->
	<div id="sidebar">
		<div class="sidebar1 clsFloatRight">
			<%@ include file="/contactblock.jsp"%>
			<!--Ending Sidebar-->
		</div>
	</div>
	<!--Starting Main-->
    <div id="main">
      <div class="clsAboutus">
        <h2>A Few Words<span> About Us</span></h2>
       </div>
      <div class="clsMain_Bottom clearfix">
        <div class="clsMain_BottomLeft clsFloatLeft">
          <h2>What <span>We Do</span></h2>
          <p>We provide security for all java based web applications</p>
          <p>Our goal is to make healthy and secure web environment</p>
          <!-- <div class="clsMainReadMore"><a href="#">Read more</a></div> -->
        </div>
        <div class="clsMain_BottomRight clsFloatRight">
          <h2>What <span>We Offer</span></h2>
          <div class="clsWeOffer">
            <ul class="clsmonitor">
              <li>Security from SQL Injection</li>
              <li>Security from Direct Database</li>
              <li>Security from Session Hijack</li>
            </ul>
            <ul class="clsbal">
              <li>Security from Privilege Escalation</li>
              <li>Security from Cross Site scripting</li>
            </ul>
          </div>
          <!-- <div class="clsMainReadMore"><a href="#">Read more</a></div> -->
        </div>
      </div>
      <!--Ending main-->
    </div>
    <!--Ending Content-->
  </div>
  <!--Ending Container-->
</div>

<%@ include file="/footer.jsp"%>
