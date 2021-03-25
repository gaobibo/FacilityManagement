package com.fms.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityPersistencyInterface;
import com.fms.model.facility.FacilityRecord;

public class FacilityService {

	@Autowired
	private ApplicationContext context;
	
	private FacilityPersistencyInterface<FacilityRecord> facilityPersistency;
	
	public FacilityPersistencyInterface<FacilityRecord> getFacilityPersistency() {
		return facilityPersistency;
	}
	
	public void setFacilityPersistency(FacilityPersistencyInterface<FacilityRecord> facilityPersistency) {
		this.facilityPersistency = facilityPersistency;
	}
	
	// List all the facilities
	public List<Facility> listAllFacilities() {
		
		Facility facility = null;
		List<Facility> facilityList = new ArrayList<Facility>();
		
		try {
			List<FacilityRecord> facilityRecords = facilityPersistency.listRecords();
			
			for (FacilityRecord record : facilityRecords) {
				
				facility = (Facility)context.getBean("facility");
				
				facility.retrieveFacilityRecord(record);
				
				facilityList.add(facility);
			}
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facilityList;
	}
	
	// Add a new facility
	public Facility addNewFacility() {
		
		Facility facility = null;
		
		try {
			FacilityRecord record = new FacilityRecord();
			facilityPersistency.addRecord(record);
			
			facility = (Facility)context.getBean("facility");
			
			facility.retrieveFacilityRecord(record);
			
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facility;
	}	
}