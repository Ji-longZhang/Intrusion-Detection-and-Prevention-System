package com.doublegaurd.bean;

import java.util.HashSet;
import java.util.Set;

public class PrivilegesBean {

	private Integer privilegeId;
	private Integer applicationId;
	private String privilegeName;
	private String privilegeURL;
	private String mappedURL;
	
	
	private Set<AppRolePrivilegesBean> userRoles = new HashSet<AppRolePrivilegesBean>(0);
	
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	public Integer getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getPrivilegeURL() {
		return privilegeURL;
	}
	public void setPrivilegeURL(String privilegeURL) {
		this.privilegeURL = privilegeURL;
	}
	public String getMappedURL() {
		return mappedURL;
	}
	public void setMappedURL(String mappedURL) {
		this.mappedURL = mappedURL;
	}
	public Set<AppRolePrivilegesBean> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<AppRolePrivilegesBean> userRoles) {
		this.userRoles = userRoles;
	}
}
