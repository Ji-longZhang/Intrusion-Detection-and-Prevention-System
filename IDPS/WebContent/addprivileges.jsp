<%@ include file="/header.jsp"%>
<%@page import="java.util.List, com.doublegaurd.service.ApplicationService, com.doublegaurd.bean.ApplicationsBean" %>

<div class="clsTopNavigation clsFloatRight">
	<ul>
		<li><a href="home.jsp" class="active">Home</a></li>
		<li ><a href="addapplication.jsp">Application</a></li>
		<li><a href="addappusers.jsp">Users</a></li>
		<li  class="clsActive"><a href="addprivileges.jsp">Privileges</a></li>
		<li><a href="mapprivilegestousers.jsp">Role-Privileges</a></li>
		<li><a href="viewattack.jsp">Attacks</a></li>
		<li><a href="logout.jsp">Log Out</a></li>
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
			primarily focused on identifying possible incidents, logging
			information about them, attempting to stop them, and reporting them
			to security administrators.
		</p>
<a href="https://en.wikipedia.org/wiki/Intrusion_detection_system" target="_blank" class="clsBannerReadMore">Read more</a>	</div>
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
				Application Privileges<span> Form</span>
			</h2>
			<p>Welcome User, secure your web application from several
				attacks and secure your web hosting today.</p>
		</div>
		<div class="clsMain_Bottom clearfix clsNoBorder">
			<div class="clsMain_BottomLeft clsFloatLeft clsNoBorder">
				<%
					String userName = (String) session.getAttribute("userName");
					String message = (String) session.getAttribute("message");

					if (null != message) {
				%>

				<font color="red"> <%=message%></font>
				<%
					}
					session.removeAttribute("message");
				%>
					<form name="appForm" action="addprivileges2.jsp" method=post>
							<%
							ApplicationService apService = new ApplicationService();
							List<ApplicationsBean> appList = apService.getUserApplications(userName);
							
							%>
							
							
					<p>
						<label> Application Name:</label>
					</p>
					<p>
										<%
											if(appList!=null){
												
										%>
											<select name="appName">
										<%
											
											for(ApplicationsBean app : appList){
											
										%>
												<option value="<%=app.getAppId() %>"><%=app.getApplicationName() %> </option>
										<%
												}
										%>
											</select>
										<%
											}
										%>
					</p>
					
					
					
					<p>
					<div class="clsButton">
						<input class="clsClear" type="reset" value="Clear" name="clear" />
						<input class="clsSubmit" type="submit" value="Next"
							name="submit" />
					</div>
					</p>
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
