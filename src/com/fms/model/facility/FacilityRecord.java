package com.fms.model.facility;

public class FacilityRecord {
	
	public static final String STATUS_READY       = "READY";
	public static final String STATUS_IN_USE      = "IN_USE";
	public static final String STATUS_REMOVED     = "REMOVED";

	public static final String TYPE_FACILITY      = "FACILITY";
	public static final String TYPE_BEDROOM       = "BEDROOM";
	public static final String TYPE_MEETINGROOM   = "MEETINGROOM";
	
	private String facilityId;
	private String facilityType;
	private String facilityStatus = STATUS_READY;
	private String facilityName;
	private String facilityAddress;
	private int facilityCapacity;
	
	public FacilityRecord(String facilityType) {
		this.facilityType = facilityType;
	}
	
	public FacilityRecord(String facilityId, String facilityType, String facilityStatus, String facilityName, String facilityAddress, int facilityCapacity) {
		this.facilityId = facilityId;
		this.facilityType = facilityType;
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
	
	// Get the facility Type
	public String getFacilityType() {
		return facilityType;
	}
	
	// Set the facility Type
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
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
