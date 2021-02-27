package com.fms.model.facility;

import java.util.Date;
import java.util.List;

public class Facility {

	public static final String STATUS_READY       = "READY";
	public static final String STATUS_IN_USE      = "IN_USE";
	public static final String STATUS_IN_MAINTAIN = "IN_MAINTAIN";
	public static final String STATUS_REMOVED     = "REMOVED";
	
	private String facilityId;
	private String facilityStatus = STATUS_READY;
	
	private FacilityDetail facilityDetail;
	
	private FacilityUseInterface 		 facilityUseIfc;
	private FacilityInspectInterface     facilityInspectIfc;
	private FacilityMaintainInterface    facilityMaintainIfc;
	private FacilityPersistency facilityPersistency;

	public Facility(String facilityId) {
		this.facilityId = facilityId;
	}
	
	public void setHandler(FacilityUseInterface facilityUseIfc,
						   FacilityInspectInterface facilityInspectIfc,
						   FacilityMaintainInterface facilityMaintainIfc,
						   FacilityPersistency facilityPersistency) {
		this.facilityUseIfc = facilityUseIfc;
		this.facilityInspectIfc = facilityInspectIfc;
		this.facilityMaintainIfc = facilityMaintainIfc;
		this.facilityPersistency = facilityPersistency;
	}

	// Get the facility ID
	public String getFacilityId() {
		return facilityId;
	}
	
	// Get the facility Status
	public String getFacilityStatus() {
		return facilityStatus;
	}
	
	// Request the available capacity of facility
	public int requestAvailableCapacity() {
		if (facilityDetail != null) {
			return facilityDetail.getFacilityCapacity();
		} else {
			return 0;
		}
	}
	
	// Get the detail information of facility
	public FacilityDetail getFacilityInformation() {
		return facilityDetail;
	}
	
	// Add or set the detail information of facility
	public void addFacilityDetail(FacilityDetail facilityDetail) {
		this.facilityDetail = facilityDetail;
		facilityPersistency.changeFacility(this);
	}

	// Remove the facility
	public void removeFacility() {
		if (facilityStatus != STATUS_REMOVED) {
			facilityStatus = STATUS_REMOVED;
			facilityPersistency.removeFacility(facilityId);			
		}
	}
	
	// List the actual usage of facility
	public List<FacilityUseRecord> listActualUsage() {
		return facilityUseIfc.listActualUsage(facilityId);
	}
	
	// Calculate the usage rate of facility
	public double calcUsageRate(Date startDate, Date endDate) {
		return facilityUseIfc.calcUsageRate(facilityId, startDate, endDate);
	}
	
	// Check if the facility is in-use or not from start date to end date
	public boolean isInUseDuringInterval(Date startDate, Date endDate) {
		return facilityUseIfc.isInUseDuringInterval(facilityId, startDate, endDate);
	}
	
	// Assign the facility to use
	public boolean assignFacilityToUse() {
		
		boolean result = false;
		
		if (facilityStatus == STATUS_READY) {
			
			if (facilityUseIfc.assignFacilityToUse(facilityId) == true) {
				
				facilityStatus = STATUS_IN_USE;
				
				result = true;
			}
		}
		
		return result;
	}
	
	// Vacate the facility
	public boolean vacateFacility() {
		
		boolean result = false;
		
		if (facilityStatus == STATUS_IN_USE) {
			
			if (facilityUseIfc.vacateFacility(facilityId) == true) {
				
				facilityStatus = STATUS_READY;
				
				result = true;
			}
		}
		
		return result;
	}
	
	// List all the inspection records of facility
	public List<FacilityInspectRecord> listInspections(String facilityId) {
		return facilityInspectIfc.listInspections(facilityId);
	}
	
	// Inspect the facility
	public boolean inspectFacility(String facilityId, String employeeId) {
		return facilityInspectIfc.inspectFacility(facilityId, employeeId);
	}
}
