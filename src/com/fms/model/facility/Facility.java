package com.fms.model.facility;

import java.util.Date;
import java.util.List;

public class Facility {

	private String facilityId;
	private String facilityStatus;
	
	private FacilityDetail facilityDetail;
	
	private FacilityUseInterface 		 facilityUseIfc;
	private FacilityInspectInterface     facilityInspectIfc;
	private FacilityMaintainInterface    facilityMaintainIfc;
	private FacilityPersistencyInterface<FacilityRecord> facilityPersistencyIfc;
	
	public Facility(FacilityUseInterface facilityUseIfc,
				    FacilityInspectInterface facilityInspectIfc,
				    FacilityMaintainInterface facilityMaintainIfc,
				    FacilityPersistencyInterface<FacilityRecord> facilityPersistencyIfc) {
		this.facilityUseIfc = facilityUseIfc;
		this.facilityInspectIfc = facilityInspectIfc;
		this.facilityMaintainIfc = facilityMaintainIfc;
		this.facilityPersistencyIfc = facilityPersistencyIfc;
		
		facilityStatus = FacilityRecord.STATUS_READY;
		facilityDetail = new FacilityDetail();
	}
	
	// Get the facility ID
	public String getFacilityId() {
		return facilityId;
	}
	
	// Set the facility ID
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	
	// Get the facility status
	public String getFacilityStatus() {
		return facilityStatus;
	}
	
	// Set the facility status
	public void setFacilityStatus(String facilityStatus) {
		
		this.facilityStatus = facilityStatus;
		
		commitFacilityRecord();
	}
	
	// Commit the facility record into persistency
	public void commitFacilityRecord() {
		
		FacilityRecord record = new FacilityRecord(this.facilityId, 
												   this.facilityStatus, 
												   this.facilityDetail.getFacilityName(), 
												   this.facilityDetail.getFacilityAddress(), 
												   this.facilityDetail.getFacilityCapacity());
		
		facilityPersistencyIfc.changeRecord(record);
	}
	
	// Retrieve the facility record from persistency
	public void retrieveFacilityRecord(FacilityRecord record) {
		this.facilityId = record.getFacilityId();
		this.facilityStatus = record.getFacilityStatus();
		this.facilityDetail.setFacilityName(record.getFacilityName());
		this.facilityDetail.setFacilityAddress(record.getFacilityAddress());
		this.facilityDetail.setFacilityCapacity(record.getFacilityCapacity());
	}
	
	/* Facility general public interfaces */
	
	// Get the detail information of the facility
	public FacilityDetail getFacilityInformation() {
		return facilityDetail;
	}
	
	// Request the available capacity of the facility
	public int requestAvailableCapacity() {
		if (facilityDetail != null) {
			return facilityDetail.getFacilityCapacity();
		} else {
			return 0;
		}
	}
	
	// Add or set the detail information of the facility
	public void addFacilityDetail(FacilityDetail facilityDetail) {
		
		this.facilityDetail = facilityDetail;
		
		commitFacilityRecord();
	}

	// Remove the facility
	public void removeFacility() {
		if (facilityStatus != FacilityRecord.STATUS_REMOVED) {
			facilityStatus = FacilityRecord.STATUS_REMOVED;
			facilityPersistencyIfc.removeRecord(facilityId);			
		}
	}
	
	/* Facility use-related public interfaces */
	
	// List the actual usage of the facility
	public List<FacilityUseRecord> listActualUsage() {
		return facilityUseIfc.listActualUsage(facilityId);
	}
	
	// Calculate the usage rate of the facility
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
		
		if (facilityStatus == FacilityRecord.STATUS_READY) {
			
			if (facilityUseIfc.assignFacilityToUse(facilityId, employeeId) == true) {
				
				facilityStatus = FacilityRecord.STATUS_IN_USE;
				
				result = true;
			}
		}
		
		return result;
	}
	
	// Vacate the facility
	public boolean vacateFacility() {
		
		boolean result = false;
		
		if (facilityStatus == FacilityRecord.STATUS_IN_USE) {
			
			if (facilityUseIfc.vacateFacility(facilityId) == true) {
				
				facilityStatus = FacilityRecord.STATUS_READY;
				
				result = true;
			}
		}
		
		return result;
	}
	
	/* Facility inspect-related public interfaces */
	
	// List all the inspection records of the facility
	public List<FacilityInspectRecord> listInspections() {
		return facilityInspectIfc.listInspections(facilityId);
	}
	
	// Inspect the facility
	public boolean inspectFacility(String employeeId) {
		return facilityInspectIfc.inspectFacility(facilityId, employeeId);
	}
	
	/* Facility maintain-related public interfaces */
	
	// List all the maintain records of the facility
	public List<FacilityMaintainRecord> listMaintenance() {
		return facilityMaintainIfc.listMaintenance(facilityId);
	}
	
	// List the maintain records of the facility with submitted status
	public List<FacilityMaintainRecord> listMaintRequests() {
		return facilityMaintainIfc.listMaintRequests(facilityId);
	}
	
	// List the maintain records of the facility with problematic type
	public List<FacilityMaintainRecord> listFacilityProblems() {
		return facilityMaintainIfc.listFacilityProblems(facilityId);
	}
	
	// Submit a maintain record for the facility
	public FacilityMaintainRecord makeFacilityMaintRequest(String employeeId, Date submittedDate, FacilityMaintainRecord.MaintainType maintainType) {
		return facilityMaintainIfc.makeFacilityMaintRequest(facilityId, employeeId, submittedDate, maintainType);
	}
	
	// Schedule a maintain record for the facility with scheduled date
	public boolean scheduleMaintenance(String recordId, Date scheduledDate) {
		return facilityMaintainIfc.scheduleMaintenance(facilityId, recordId, scheduledDate);
	}
	
	// Complete a maintain record for the facility with completed date and maintain cost
	public boolean completeMaintenance(String recordId, Date completedDate, double maintainCost) {
		return facilityMaintainIfc.completeMaintenance(facilityId, recordId, completedDate, maintainCost);
	}
	
	// Calculate the total maintain cost for the facility
	public double calcMaintenaceCostForFacility() {
		return facilityMaintainIfc.calcMaintenaceCostForFacility(facilityId);
	}
	
	// Calculate the problem rate for the facility (number of problematic records of number of total records)
	public double calcProblemRateForFacility() {
		return facilityMaintainIfc.calcProblemRateForFacility(facilityId);
	}
	
	// Calculate the down time for the facility (days between submitted date and completed date of the problematic records)
	public double calcDownTimeForFacility() {
		return facilityMaintainIfc.calcDownTimeForFacility(facilityId);
	}
}
