package com.fms.model.facility;

import java.util.Date;

public class FacilityMaintainRecord {
	
	public static final String STATUS_SUBMITTED = "SUBMITTED";
	public static final String STATUS_SCHEDULED = "SCHEDULED";
	public static final String STATUS_COMPLETED = "COMPLETED";
	
	public enum MaintainType {
		  GENERAL,
		  PROBLEMATIC
		}
	
	private String recordId;
	private String facilityId;
	private String employeeId;
	private String status = STATUS_SUBMITTED;
	private MaintainType maintainType = MaintainType.GENERAL;
	private Date submittedDate;
	private Date scheduledDate;
	private Date completedDate;
	private double maintainCost;
	
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
	
	public Date getSubmittedDate() {
		return submittedDate;
	}
	
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}
	
	public Date getScheduledDate() {
		return scheduledDate;
	}
	
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	public Date getCompletedDate() {
		return completedDate;
	}
	
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public MaintainType getMaintainType() {
		return maintainType;
	}
	
	public void setMaintainType(MaintainType maintainType) {
		this.maintainType = maintainType;
	}
	
	public double getMaintainCost() {
		return maintainCost;
	}
	
	public void setMaintainCost(double maintainCost) {
		this.maintainCost = maintainCost;
	}
}
