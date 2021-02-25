package com.fms.model.facility;

import com.fms.model.common.Address;
import com.fms.model.employee.Manager;

public class FacilityGeneral {
	
	public static final String STATUS_READY       = "READY";
	public static final String STATUS_IN_USE      = "IN_USE";
	public static final String STATUS_IN_MAINTAIN = "IN_MAINTAIN";
	public static final String STATUS_REMOVED     = "REMOVED";
	
	private String facilityId;
	private String facilityName;
	private int facilityCapacity;
	private Manager facilityManager;
	private Address facilityAddress;
	private String facilityStatus = STATUS_READY;


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
	
	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}
	
	public String getFacilityStatus() {
		return facilityStatus;
	}
}
