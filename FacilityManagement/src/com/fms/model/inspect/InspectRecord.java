package com.fms.model.inspect;

import java.util.Date;

public class InspectRecord {
	private String recordId;
	private String facilityId;
	private String employeeId;
	private Date inspectDate;
	
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
	
	public Date getInspectDate() {
		return inspectDate;
	}
	
	public void setInspectDate(Date inspectDate) {
		this.inspectDate = inspectDate;
	}
}
