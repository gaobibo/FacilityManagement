package com.fms.model.facility;

public class FacilityDetail {

	private String facilityName;
	private String facilityAddress;
	private int facilityCapacity;

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
