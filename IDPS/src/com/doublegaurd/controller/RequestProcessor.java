package com.doublegaurd.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doublegaurd.bean.AppRolePrivilegesBean;
import com.doublegaurd.bean.ApplicationsBean;
import com.doublegaurd.bean.AttackBean;
import com.doublegaurd.bean.PrivilegesBean;
import com.doublegaurd.bean.UserBean;
import com.doublegaurd.service.ApplicationService;
import com.doublegaurd.service.UserService;
import com.doublegaurd.util.Emailer;

/**
 * Servlet implementation class RequestProcessor
 */
public class RequestProcessor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Map<String, String> sessionMap = new HashMap<String, String>();

	List<ApplicationsBean> appList = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ApplicationService applicationService = new ApplicationService();
		appList = applicationService.getApplications();
		System.out.println("********** app List Size = "+appList.size());
	}
	
	@Override
	public void destroy() {
		appList = null;
		sessionMap = null;
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestProcessor() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}

	private void action(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String action = request.getParameter("action");
		@SuppressWarnings("unused")
		HttpSession session = request.getSession();
		boolean dbEnjectionAttackFlag = false;
		boolean privillegeEscalationAttackFlag = false;
	
		
		String userIp = request.getRemoteAddr();
		System.out.println("serverIp="+userIp);
		
		dbEnjectionAttackFlag = checkDBInjectionAttack(request);
		
		PrivilegesBean privillege = getAppPrivilege(action);
		ApplicationsBean appBean = getApplication(privillege);
		UserBean user = getUser(appBean.getUserName());
		String sessionId = request.getSession().getId();
		System.out.println(sessionId);
		
		String queryString = getQueryString(request);
		
		//Check for privillegeEscalationAttackFlag
		String userType = request.getParameter("userType");
		if(null != userType) {
			privillegeEscalationAttackFlag = isPrivillegeEscalationAttack(userType, privillege);
		}
		
		
		if (dbEnjectionAttackFlag) {
			addAttackInfo(user, appBean,"Database injection attack", "SQL injection attack occured for the application "+appBean.getApplicationName()+", Some one tried to access the application url="+ privillege.getPrivilegeURL()+" from the IP Address="+userIp);
			response.sendRedirect(appBean.getErrorPage());
		} else if (privillegeEscalationAttackFlag) {
			addAttackInfo(user, appBean,"privillege Escalation attack", "privillege Escalation attack occured for the application "+appBean.getApplicationName()+", Some one tried to access the application url="+ privillege.getPrivilegeURL()+" from the IP Address="+userIp);
			response.sendRedirect(appBean.getErrorPage());
		}
		else {			//SESSION HIJACK ATTACK
			String obj = sessionMap.get(userIp);
			if(obj != null) {
				
				response.sendRedirect(privillege.getPrivilegeURL()+queryString); 	//No Attack found..Legit user
				
				
			} else {
				//check if its not welcome page send to error page
				if(privillege.getPrivilegeURL().equals(appBean.getWelcomePage())){
					sessionMap.put(userIp, sessionId);
					response.sendRedirect(privillege.getPrivilegeURL()+queryString);
				} else {
					addAttackInfo(user, appBean,"Session hijack attack", "Session hijack attack occured for the application "+appBean.getApplicationName()+", Some one tried to access the application url="+ privillege.getPrivilegeURL()+" from the IP Address="+userIp);
					response.sendRedirect(appBean.getErrorPage());
				}
			}
		}

	}

	private UserBean getUser(String userName) {
		UserService userService = new UserService();
		return userService.getUser(userName);
	}

	private boolean isPrivillegeEscalationAttack(String userType,
			PrivilegesBean privillege) {
		boolean flag = true;
		Set<AppRolePrivilegesBean> userRoles = privillege.getUserRoles();
		for(AppRolePrivilegesBean role : userRoles){
			if(role.getUserRole().getUserRole().equals(userType)){
				flag=false;
				break;
			}
		}
		return flag;
	}

	private void addAttackInfo(UserBean user, ApplicationsBean appBean, String subject,
			String attackDescr) {
		ApplicationService appService = new ApplicationService();
		AttackBean attack = new  AttackBean();
		attack.setAttackDate(new Date());
		attack.setApplication(appBean);
		attack.setAttackType(attackDescr);
		appService.addAttackInfo(attack);
		
		String[]toUser = new String[]{user.getEmail()};
		Emailer.sendEmail(toUser, subject, attackDescr);
		
	}


	private ApplicationsBean getApplication(PrivilegesBean privillege) {
		ApplicationService appService = new ApplicationService();
		return appService.getApplicationDetails(privillege.getApplicationId().toString());
	}

	private PrivilegesBean getAppPrivilege(String privilegeId) {
		ApplicationService appService = new ApplicationService();
		return appService.getUserAppPrivileges(privilegeId);
	}

	private boolean checkDBInjectionAttack(HttpServletRequest request) {
		boolean dbEnjectionAttachFlag=false;
		Map<String, String[]> paramMap = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			String paramName = entry.getKey();
			Object paramObj = entry.getValue();
			String[] paramValues = (String[]) paramObj;
			System.out
					.println("name=" + paramName + " value=" + paramValues[0]);
			dbEnjectionAttachFlag = validataDBInjection(paramValues[0]);
			if (dbEnjectionAttachFlag) {
				break;
			}
		}
		return dbEnjectionAttachFlag;
				
	}
	
	private String getQueryString(HttpServletRequest request) {
		StringBuffer queryString = new StringBuffer("");
		Map<String, String[]> paramMap = request.getParameterMap();
		int i=0;
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			String paramName = entry.getKey();
			Object paramObj = entry.getValue();
			String[] paramValues = (String[]) paramObj;
			if (i==0){
				queryString.append("?");
			} else {
				queryString.append('&');
			}
			queryString.append(paramName).append("=").append(paramValues[0]);
			i++;
			
			
		}
		return queryString.toString();
				
	}

	private boolean validataDBInjection(String paramValue) {
		boolean flag = true;
		if (paramValue.toUpperCase().contains("SELECT * FROM")) {
			flag = true;
		} else if(paramValue.toUpperCase().contains("DELETE FROM")) {
			flag = true;
		} else if(paramValue.toUpperCase().contains("INSERT INTO")) {
			flag = true;
		} else if(paramValue.toUpperCase().contains("DELETE * FROM")) {
			flag = true;
		}  else if(paramValue.toUpperCase().contains("UPDATE ")) {
			flag = true;
		}  else if(paramValue.toUpperCase().contains("<SCRIPT>")) {
			flag = true;
		} else if (checkORexpression(paramValue)){ //check for regualar expression
			flag = true;
		}else {
			flag = false;
		}
		return flag;
	}
	
	
	public static void main(String[] args) {
		String str = "id = ‘1’ = OR ‘1’  ‘1’ AND password  ‘1’ OR ‘1’  ‘1’; ";
		System.out.println(checkORexpression(str));
	}
	
	private static boolean checkORexpression(String str) {
		boolean flag = false;
		str = str.toUpperCase();
		
		int or = str.toUpperCase().indexOf(" OR ");
		if(or>0){
			int eq = str.indexOf("=", or);
			if(or < eq) {
				flag = true;
			}
		}
		return flag;
	}
	

}
