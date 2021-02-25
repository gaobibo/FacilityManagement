package com.fms.model.facility;

import java.util.Date;
import java.util.List;

import com.fms.model.use.UseRecord;

public class Facility {
	private FacilityGeneral facilityGeneral;
	private FacilityDetail facilityDetail;
	
	private FacilityPersistencyInterface facilityPersistencyIfc;
	private FacilityUseInterface facilityUseIfc;

	public Facility(FacilityPersistencyInterface facilityPersistencyIfc, 
					FacilityUseInterface facilityUseIfc) {
		this.facilityPersistencyIfc = facilityPersistencyIfc;
		this.facilityUseIfc = facilityUseIfc;
	}

	// Get the available capacity of facility
	public int getAvailableCapacity() {
	    return facilityGeneral.getFacilityCapacity();
	}
	
	// Get the general information of facility
	public FacilityGeneral getFacilityGeneral() {
		return facilityGeneral;
	}

	// Set the general information of facility
	public void setFacilityGeneral(FacilityGeneral facilityGeneral) {
		this.facilityGeneral = facilityGeneral;
		facilityPersistencyIfc.changeFacility(this);
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
		if (facilityGeneral.getFacilityStatus() != FacilityGeneral.STATUS_REMOVED) {
			facilityGeneral.setFacilityStatus(FacilityGeneral.STATUS_REMOVED);
			facilityPersistencyIfc.removeFacility(facilityGeneral.getFacilityId());			
		}
	}
	
	// List the actual usage of facility
	public List<UseRecord> listActualUsage() {
		return facilityUseIfc.listActualUsage(facilityGeneral.getFacilityId());
	}
	
	// Calculate the usage rate of facility
	public double calcUsageRate(Date startDate, Date endDate) {
		return facilityUseIfc.calcUsageRate(facilityGeneral.getFacilityId(), startDate, endDate);
	}
	
	// Check if the facility is in-use or not from start date to end date
	public boolean isInUseDuringInterval(Date startDate, Date endDate) {
		return facilityUseIfc.isInUseDuringInterval(facilityGeneral.getFacilityId(), startDate, endDate);
	}
	
	// Assign the facility to an employee
	public boolean assignFacilityToUse(String employeeId) {
		
		boolean result = false;
		
		if (facilityGeneral.getFacilityStatus() == FacilityGeneral.STATUS_READY) {
			
			if (facilityUseIfc.assignFacilityToUse(facilityGeneral.getFacilityId(), employeeId) == true) {
				
				facilityGeneral.setFacilityStatus(FacilityGeneral.STATUS_IN_USE);
				
				result = true;
			}
		}
		
		return result;
	}
	
	// Vacate the facility
	public boolean vacateFacilityFromUse() {
		
		boolean result = false;
		
		if (facilityGeneral.getFacilityStatus() == FacilityGeneral.STATUS_IN_USE) {
			
			if (facilityUseIfc.vacateFacilityFromUse(facilityGeneral.getFacilityId()) == true) {
				
				facilityGeneral.setFacilityStatus(FacilityGeneral.STATUS_READY);
				
				result = true;
			}
		}
		
		return result;
	}
}
