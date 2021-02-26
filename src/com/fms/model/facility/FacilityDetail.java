package com.fms.model.facility;

public class FacilityDetail {

	private String facilityName;
	private int facilityCapacity;
	private Address facilityAddress;

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
	
	public Address getFacilityAddress() {
		return facilityAddress;
	}

	public void setFacilityAddress(Address facilityAddress) {
		this.facilityAddress = facilityAddress;
	}
}
