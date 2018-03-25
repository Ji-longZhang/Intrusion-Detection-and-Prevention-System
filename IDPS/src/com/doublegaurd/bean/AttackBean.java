package com.doublegaurd.bean;

import java.util.Date;

public class AttackBean {

	private Integer attackId;
	private Integer applicationId;
	private Date attackDate;
	private String attackType;
	ApplicationsBean application;
	
	public Integer getAttackId() {
		return attackId;
	}
	public void setAttackId(Integer attackId) {
		this.attackId = attackId;
	}
	public Integer getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}
	public Date getAttackDate() {
		return attackDate;
	}
	public void setAttackDate(Date attackDate) {
		this.attackDate = attackDate;
	}
	public String getAttackType() {
		return attackType;
	}
	public void setAttackType(String attackType) {
		this.attackType = attackType;
	}
	
	public ApplicationsBean getApplication() {
		return application;
	}
	public void setApplication(ApplicationsBean application) {
		this.application = application;
	}
	
	
	
}
