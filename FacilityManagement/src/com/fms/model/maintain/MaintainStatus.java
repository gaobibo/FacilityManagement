package com.fms.model.maintain;

public class MaintainStatus {

	public static final String SUBMITTED = "SUBMITTED";
	public static final String SCHEDULED = "SCHEDULED";
	public static final String COMPLETED = "COMPLETED";
	
	private String status = SUBMITTED;
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
