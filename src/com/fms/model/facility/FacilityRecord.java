package com.fms.model.facility;

public class FacilityRecord {
	
	public static final String STATUS_READY       = "READY";
	public static final String STATUS_IN_USE      = "IN_USE";
	public static final String STATUS_REMOVED     = "REMOVED";
	
	private String facilityId;
	private String facilityStatus = STATUS_READY;
	private String facilityName;
	private String facilityAddress;
	private int facilityCapacity;
	
	public FacilityRecord() {
	}
	
	public FacilityRecord(String facilityId, String facilityStatus, String facilityName, String facilityAddress, int facilityCapacity) {
		this.facilityId = facilityId;
		this.facilityStatus = facilityStatus;
		this.facilityName = facilityName;
		this.facilityAddress = facilityAddress;
		this.facilityCapacity = facilityCapacity;
	}

	// Get the facility ID
	public String getFacilityId() {
		return facilityId;
	}
	
	// Set the facility ID
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	
	// Get the facility Status
	public String getFacilityStatus() {
		return facilityStatus;
	}
	
	// Set the facility Status
	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}
	
	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public int getFacilityCapacity() {
		return facilityCapacity;
	}

	public void setFacilityCapacity(int facilityCapacity) {
		this.facilityCapacity = facilityCapacity;
	}
	
	public String getFacilityAddress() {
		return facilityAddress;
	}

	public void setFacilityAddress(String facilityAddress) {
		this.facilityAddress = facilityAddress;
	}
}
