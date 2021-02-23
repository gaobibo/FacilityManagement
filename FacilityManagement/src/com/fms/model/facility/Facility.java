package com.fms.model.facility;

import com.fms.model.common.Address;
import com.fms.model.employee.Manager;

public class Facility {
	private String facilityId;
	private String facilityName;
	private int facilityCapacity;
	private Manager facilityManager;
	private Address facilityAddress;
	private FacilityStatus facilityStatus;
	private FacilityDetail facilityDetail;

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
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
	
	public Manager getFacilityManager() {
		return facilityManager;
	}

	public void setFacilityManager(Manager facilityManager) {
		this.facilityManager = facilityManager;
	}
	
	public Address getFacilityAddress() {
		return facilityAddress;
	}

	public void setFacilityAddress(Address facilityAddress) {
		this.facilityAddress = facilityAddress;
	}
	
	public FacilityStatus getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(FacilityStatus facilityStatus) {
		this.facilityStatus = facilityStatus;
	}
	
	public FacilityDetail getFacilityDetail() {
		return facilityDetail;
	}

	public void setFacilityDetail(FacilityDetail facilityDetail) {
		this.facilityDetail = facilityDetail;
	}
}
