package com.fms.model.facility;

import java.util.Date;
import java.util.List;

public class Facility {

	public static final String STATUS_READY       = "READY";
	public static final String STATUS_IN_USE      = "IN_USE";
	public static final String STATUS_REMOVED     = "REMOVED";
	
	protected String facilityId;
	protected String facilityStatus = STATUS_READY;
	
	private FacilityDetail facilityDetail;
	
	private FacilityUseInterface 		 facilityUseIfc;
	private FacilityInspectInterface     facilityInspectIfc;
	private FacilityMaintainInterface    facilityMaintainIfc;
	private FacilityPersistencyInterface<Facility> facilityPersistencyIfc;
	
	public void setHandler(FacilityUseInterface facilityUseIfc,
						   FacilityInspectInterface facilityInspectIfc,
						   FacilityMaintainInterface facilityMaintainIfc,
						   FacilityPersistencyInterface<Facility> facilityPersistencyIfc) {
		this.facilityUseIfc = facilityUseIfc;
		this.facilityInspectIfc = facilityInspectIfc;
		this.facilityMaintainIfc = facilityMaintainIfc;
		this.facilityPersistencyIfc = facilityPersistencyIfc;
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
		facilityPersistencyIfc.changeRecord(this);
	}

	// Remove the facility
	public void removeFacility() {
		if (facilityStatus != STATUS_REMOVED) {
			facilityStatus = STATUS_REMOVED;
			facilityPersistencyIfc.removeRecord(facilityId);			
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
	public List<FacilityInspectRecord> listInspections() {
		return facilityInspectIfc.listInspections(facilityId);
	}
	
	// Inspect the facility
	public boolean inspectFacility(String employeeId) {
		return facilityInspectIfc.inspectFacility(facilityId, employeeId);
	}
	
	// List all the maintain records of facility
	public List<FacilityMaintainRecord> listMaintenance() {
		return facilityMaintainIfc.listMaintenance(facilityId);
	}
	
	public List<FacilityMaintainRecord> listMaintRequests() {
		return facilityMaintainIfc.listMaintRequests(facilityId);
	}
	
	public List<FacilityMaintainRecord> listFacilityProblems() {
		return facilityMaintainIfc.listFacilityProblems(facilityId);
	}
	
	public FacilityMaintainRecord makeFacilityMaintRequest(String employeeId, Date submittedDate, FacilityMaintainRecord.MaintainType maintainType) {
		return facilityMaintainIfc.makeFacilityMaintRequest(facilityId, employeeId, submittedDate, maintainType);
	}
	
	public boolean scheduleMaintenance(String recordId, Date date) {
		return facilityMaintainIfc.scheduleMaintenance(facilityId, recordId, date);
	}
	
	public boolean completeMaintenance(String recordId, Date date, double maintainCost) {
		return facilityMaintainIfc.completeMaintenance(facilityId, recordId, date, maintainCost);
	}
	
	public double calcMaintenaceCostForFacility() {
		return facilityMaintainIfc.calcMaintenaceCostForFacility(facilityId);
	}
	
	public double calcProblemRateForFacility() {
		return facilityMaintainIfc.calcProblemRateForFacility(facilityId);
	}
	
	public double calcDownTimeForFacility() {
		return facilityMaintainIfc.calcDownTimeForFacility(facilityId);
	}
}
