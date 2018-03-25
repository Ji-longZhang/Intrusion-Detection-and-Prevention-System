package com.doublegaurd.bean;

public class AppRolePrivilegesBean {
	Integer approleprivilegesId;
	ApplicationsBean application;
	PrivilegesBean privileges;
	UserRoleBean userRole;

	/**
	 * @return the approleprivilegesId
	 */
	public Integer getApproleprivilegesId() {
		return approleprivilegesId;
	}

	/**
	 * @param approleprivilegesId
	 *            the approleprivilegesId to set
	 */
	public void setApproleprivilegesId(Integer approleprivilegesId) {
		this.approleprivilegesId = approleprivilegesId;
	}

	/**
	 * @return the application
	 */
	public ApplicationsBean getApplication() {
		return application;
	}

	/**
	 * @param application
	 *            the application to set
	 */
	public void setApplication(ApplicationsBean application) {
		this.application = application;
	}

	/**
	 * @return the privileges
	 */
	public PrivilegesBean getPrivileges() {
		return privileges;
	}

	/**
	 * @param privileges
	 *            the privileges to set
	 */
	public void setPrivileges(PrivilegesBean privileges) {
		this.privileges = privileges;
	}

	/**
	 * @return the userRole
	 */
	public UserRoleBean getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole
	 *            the userRole to set
	 */
	public void setUserRole(UserRoleBean userRole) {
		this.userRole = userRole;
	}

}
