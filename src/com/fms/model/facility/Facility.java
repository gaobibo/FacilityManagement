package com.fms.model.facility;

import java.util.Date;
import java.util.List;

import com.fms.model.handler.UseRecord;

public class Facility {

	public static final String STATUS_READY       = "READY";
	public static final String STATUS_IN_USE      = "IN_USE";
	public static final String STATUS_IN_MAINTAIN = "IN_MAINTAIN";
	public static final String STATUS_REMOVED     = "REMOVED";
	
	private String facilityId;
	private String facilityStatus = STATUS_READY;
	
	private FacilityDetail facilityDetail = new FacilityDetail();
	
	private FacilityPersistencyInterface facilityPersistencyIfc;
	private FacilityUseInterface 		 facilityUseIfc;
	private FacilityInspectInterface     facilityInspectIfc;
	private FacilityMaintainInterface    facilityMaintainIfc;

	public Facility(String facilityId) {
		this.facilityId = facilityId;
	}
	
	public void setHandler(FacilityPersistencyInterface facilityPersistencyIfc, 
					FacilityUseInterface facilityUseIfc,
					FacilityInspectInterface facilityInspectIfc,
					FacilityMaintainInterface facilityMaintainIfc) {
		this.facilityPersistencyIfc = facilityPersistencyIfc;
		this.facilityUseIfc = facilityUseIfc;
		this.facilityInspectIfc = facilityInspectIfc;
		this.facilityMaintainIfc = facilityMaintainIfc;
	}

	// Get the facility ID
	public String getFacilityId() {
		return facilityId;
	}
	
	// Get the available capacity of facility
	public int getAvailableCapacity() {
	    return facilityDetail.getFacilityCapacity();
	}
	
	// Get the detail information of facility
	public FacilityDetail getFacilityDetail() {
		return facilityDetail;
	}
	
	// Set the detail information of facility
	public void setFacilityDetail(FacilityDetail facilityDetail) {
		this.facilityDetail = facilityDetail;
		facilityPersistencyIfc.changeFacility(this);
	}

	// Remove the facility
	public void removeFacility() {
		if (facilityStatus != STATUS_REMOVED) {
			facilityStatus = STATUS_REMOVED;
			facilityPersistencyIfc.removeFacility(facilityId);			
		}
	}
	
	// List the actual usage of facility
	public List<UseRecord> listActualUsage() {
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
	
	// Assign the facility to an employee
	public boolean assignFacilityToUse(String employeeId) {
		
		boolean result = false;
		
		if (facilityStatus == STATUS_READY) {
			
			if (facilityUseIfc.assignFacilityToUse(facilityId, employeeId) == true) {
				
				facilityStatus = STATUS_IN_USE;
				
				result = true;
			}
		}
		
		return result;
	}
	
	// Vacate the facility
	public boolean vacateFacilityFromUse() {
		
		boolean result = false;
		
		if (facilityStatus == STATUS_IN_USE) {
			
			if (facilityUseIfc.vacateFacilityFromUse(facilityId) == true) {
				
				facilityStatus = STATUS_READY;
				
				result = true;
			}
		}
		
		return result;
	}
}
