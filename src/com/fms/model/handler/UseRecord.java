package com.fms.model.handler;

import java.util.Date;

public class UseRecord {
	private String recordId;
	private String facilityId;
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
