package com.fms.model.facility;

import java.util.Date;

public class FacilityMaintainRecord {
	
	public static final String STATUS_SUBMITTED = "SUBMITTED";
	public static final String STATUS_SCHEDULED = "SCHEDULED";
	public static final String STATUS_COMPLETED = "COMPLETED";
	
	private String recordId;
	private String status = STATUS_SUBMITTED;
	private String facilityId;
	private String employeeId;
	private Date reportedDate;
	private Date scheduleDate;
	private Date completeDate;
	
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
	
	public Date getReportedDate() {
		return reportedDate;
	}
	
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
	
	public Date getScheduleDate() {
		return scheduleDate;
	}
	
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
	public Date getCompleteDate() {
		return completeDate;
	}
	
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
