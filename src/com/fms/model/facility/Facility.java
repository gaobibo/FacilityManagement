package com.fms.model.facility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Facility implements Resource {

	protected String facilityId;
	private String facilityType;
	private String facilityStatus;
	private FacilityDetail facilityDetail;
	private List<ResourceObserver> observerList = new ArrayList<ResourceObserver>();
	
	protected FacilityUseInterface 		 facilityUse;
	private FacilityInspectInterface     facilityInspect;
	private FacilityMaintainInterface    facilityMaintain;
	private FacilityPersistencyInterface<FacilityRecord> facilityPersistency;
	
	public void acceptVisitor(ResourceVisitor visitor) {
		visitor.visitFacility(this);
	}
	
	public void attachObserver(ResourceObserver observer) {
		observerList.add(observer);		
	}
	
	public void dettachObserver(ResourceObserver observer) {
		observerList.remove(observer);
	}
	
	private void notifyObserver() {
		for (ResourceObserver observer : observerList) {
			observer.updateStatus(this);
		}
	}
	
	public String getResourceId() {
		return facilityId;
	}
	
	public String getResourceStatus() {
		return facilityStatus;
	}
	
	public FacilityPersistencyInterface<FacilityRecord> getFacilityPersistency() {
		return facilityPersistency;
	}
	
	public void setFacilityPersistency(FacilityPersistencyInterface<FacilityRecord> facilityPersistency) {
		this.facilityPersistency = facilityPersistency;
	}
	
	public FacilityUseInterface getFacilityUse() {
		return facilityUse;
	}
	
	public void setFacilityUse(FacilityUseInterface facilityUse) {
		this.facilityUse = facilityUse;
	}
	
	public FacilityInspectInterface getFacilityInspect() {
		return facilityInspect;
	}
	
	public void setFacilityInspect(FacilityInspectInterface facilityInspect) {
		this.facilityInspect = facilityInspect;
	}
	
	public FacilityMaintainInterface getFacilityMaintain() {
		return facilityMaintain;
	}
	
	public void setFacilityMaintain(FacilityMaintainInterface facilityMaintain) {
		this.facilityMaintain = facilityMaintain;
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
	
	// Get the facility status
	public String getFacilityStatus() {
		return facilityStatus;
	}
	
	// Set the facility status
	public void setFacilityStatus(String facilityStatus) {
		
		this.facilityStatus = facilityStatus;
		
		notifyObserver();
		
		commitFacilityRecord();
	}
	
	// Get the detail information of the facility
	public FacilityDetail getFacilityDetail() {
		return facilityDetail;
	}
	
	// Set the detail information of the facility
	public void setFacilityDetail(FacilityDetail facilityDetail) {
		
		this.facilityDetail = facilityDetail;
		
		commitFacilityRecord();
	}
	
	// Commit the facility record into persistency
	public void commitFacilityRecord() {
		
		FacilityRecord record = new FacilityRecord(this.facilityId, 
												   this.facilityType,
												   this.facilityStatus, 
												   this.facilityDetail.getFacilityName(), 
												   this.facilityDetail.getFacilityAddress(), 
												   this.facilityDetail.getFacilityCapacity());
		
		facilityPersistency.changeRecord(record);
	}
	
	// Retrieve the facility record from persistency
	public void retrieveFacilityRecord(FacilityRecord record) {
		this.facilityId = record.getFacilityId();
		this.facilityType = record.getFacilityType();
		this.facilityStatus = record.getFacilityStatus();
		this.facilityDetail.setFacilityName(record.getFacilityName());
		this.facilityDetail.setFacilityAddress(record.getFacilityAddress());
		this.facilityDetail.setFacilityCapacity(record.getFacilityCapacity());
	}
	
	/* Facility general public interfaces */
	
	// Request the available capacity of the facility
	public int requestAvailableCapacity() {
		if (facilityDetail != null) {
			return facilityDetail.getFacilityCapacity();
		} else {
			return 0;
		}
	}
	
	// Remove the facility
	public void removeFacility() {
		if (facilityStatus != FacilityRecord.STATUS_REMOVED) {
			setFacilityStatus(FacilityRecord.STATUS_REMOVED);
			facilityPersistency.removeRecord(facilityId);			
		}
	}
	
	/* Facility use-related public interfaces */
	
	// List the actual usage of the facility
	public List<FacilityUseRecord> listActualUsage() {
		return facilityUse.listActualUsage(facilityId);
	}
	
	// Calculate the usage rate of the facility
	public double calcUsageRate(Date startDate, Date endDate) {
		System.out.println("calcUsageRate in Facility");
		return facilityUse.calcUsageRate(facilityId, startDate, endDate);
	}
	
	// Check if the facility is in-use or not from start date to end date
	public boolean isInUseDuringInterval(Date startDate, Date endDate) {
		return facilityUse.isInUseDuringInterval(facilityId, startDate, endDate);
	}
	
	// Assign the facility to use
	public boolean assignFacilityToUse(String employeeId) {
		
		boolean result = false;
		
		if (facilityStatus == FacilityRecord.STATUS_READY) {
			
			if (facilityUse.assignFacilityToUse(facilityId, employeeId) == true) {
				
				setFacilityStatus(FacilityRecord.STATUS_IN_USE);
				
				result = true;
			}
		}
		
		return result;
	}
	
	// Vacate the facility
	public boolean vacateFacility() {
		
		boolean result = false;
		
		if (facilityStatus == FacilityRecord.STATUS_IN_USE) {
			
			if (facilityUse.vacateFacility(facilityId) == true) {
				
				setFacilityStatus(FacilityRecord.STATUS_READY);
				
				result = true;
			}
		}
		
		return result;
	}
	
	/* Facility inspect-related public interfaces */
	
	// List all the inspection records of the facility
	public List<FacilityInspectRecord> listInspections() {
		return facilityInspect.listInspections(facilityId);
	}
	
	// Inspect the facility
	public boolean inspectFacility(String employeeId) {
		return facilityInspect.inspectFacility(facilityId, employeeId);
	}
	
	/* Facility maintain-related public interfaces */
	
	// List all the maintain records of the facility
	public List<FacilityMaintainRecord> listMaintenance() {
		return facilityMaintain.listMaintenance(facilityId);
	}
	
	// List the maintain records of the facility with submitted status
	public List<FacilityMaintainRecord> listMaintRequests() {
		return facilityMaintain.listMaintRequests(facilityId);
	}
	
	// List the maintain records of the facility with problematic type
	public List<FacilityMaintainRecord> listFacilityProblems() {
		return facilityMaintain.listFacilityProblems(facilityId);
	}
	
	// Submit a maintain record for the facility
	public FacilityMaintainRecord makeFacilityMaintRequest(String employeeId, Date submittedDate, FacilityMaintainRecord.MaintainType maintainType) {
		return facilityMaintain.makeFacilityMaintRequest(facilityId, employeeId, submittedDate, maintainType);
	}
	
	// Schedule a maintain record for the facility with scheduled date
	public boolean scheduleMaintenance(String recordId, Date scheduledDate) {
		return facilityMaintain.scheduleMaintenance(facilityId, recordId, scheduledDate);
	}
	
	// Complete a maintain record for the facility with completed date and maintain cost
	public boolean completeMaintenance(String recordId, Date completedDate, double maintainCost) {
		return facilityMaintain.completeMaintenance(facilityId, recordId, completedDate, maintainCost);
	}
	
	// Calculate the total maintain cost for the facility
	public double calcMaintenaceCostForFacility() {
		return facilityMaintain.calcMaintenaceCostForFacility(facilityId);
	}
	
	// Calculate the problem rate for the facility (number of problematic records of number of total records)
	public double calcProblemRateForFacility() {
		return facilityMaintain.calcProblemRateForFacility(facilityId);
	}
	
	// Calculate the down time for the facility (days between submitted date and completed date of the problematic records)
	public double calcDownTimeForFacility() {
		return facilityMaintain.calcDownTimeForFacility(facilityId);
	}
}
