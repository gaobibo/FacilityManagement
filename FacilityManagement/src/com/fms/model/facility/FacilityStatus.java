package com.fms.model.facility;

public class FacilityStatus {
	
	public static final String AVAILABLE 	 = "AVAILABLE";
	public static final String INUSE 		 = "INUSE";
	public static final String NOTAVAILABLE = "NOTAVAILABLE";
	
	private String status = AVAILABLE;
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
