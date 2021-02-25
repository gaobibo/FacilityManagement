package com.fms.model.maintain;

import java.util.Date;

public class MaintainRecord {
	private String recordId;
	private String facilityId;
	private String employeeId;
	private Date reportedDate;
	private Date scheduleDate;
	private Date completeDate;
	private MaintainStatus status;
	
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

	public MaintainStatus getStatus() {
		return status;
	}
	
	public void setStatus(MaintainStatus status) {
		this.status = status;
	}
}
