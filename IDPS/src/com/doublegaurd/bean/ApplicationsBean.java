package com.doublegaurd.bean;

import java.util.HashSet;
import java.util.Set;

public class ApplicationsBean {
	private Integer appId;
	private String applicationName;
	private String serverIpAddress;
	private String serverPort;
	private String userName;
	private String errorPage;
	private String welcomePage;

	private Set<PrivilegesBean> privileges = new HashSet<PrivilegesBean>(0);
	private Set<UserRoleBean> users = new HashSet<UserRoleBean>(0);

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getServerIpAddress() {
		return serverIpAddress;
	}

	public void setServerIpAddress(String serverIpAddress) {
		this.serverIpAddress = serverIpAddress;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public String getWelcomePage() {
		return welcomePage;
	}

	public void setWelcomePage(String welcomePage) {
		this.welcomePage = welcomePage;
	}

	public Set<PrivilegesBean> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<PrivilegesBean> privileges) {
		this.privileges = privileges;
	}

	public Set<UserRoleBean> getUsers() {
		return users;
	}

	public void setUsers(Set<UserRoleBean> users) {
		this.users = users;
	}

}
