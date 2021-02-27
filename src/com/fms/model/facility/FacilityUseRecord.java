package com.fms.model.facility;

import java.util.Date;

public class FacilityUseRecord {
	private String recordId;
	private String facilityId;
	private String employeeId;
	private Date assignDate;
	private Date vacateDate;
	
	public String getRecordId() {
		return recordId;
	}
	
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	public String getFacilityId() {
		return facilityId;
	}
	
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public Date getAssignDate() {
		return assignDate;
	}
	
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	
	public Date getVacateDate() {
		return vacateDate;
	}
	
	public void setVacateDate(Date vacateDate) {
		this.vacateDate = vacateDate;
	}
}
