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
	private List<Facility> facilityList;
	
	public FacilityPersistencyInterface<FacilityRecord> getFacilityPersistency() {
		return facilityPersistency;
	}
	
	public void setFacilityPersistency(FacilityPersistencyInterface<FacilityRecord> facilityPersistency) {
		this.facilityPersistency = facilityPersistency;
	}
	
	// List all the facilities
	public List<Facility> listAllFacilities() {
		
		facilityList = new ArrayList<Facility>();
		
		try {
			List<FacilityRecord> facilityRecords = facilityPersistency.listRecords();
			
			for (FacilityRecord record : facilityRecords) {

				Facility facility = null;

				if (record.getFacilityType().equals(FacilityRecord.TYPE_BEDROOM)) {
					facility = (Facility)context.getBean("hotelbedroom");
				} else if (record.getFacilityType().equals(FacilityRecord.TYPE_MEETINGROOM)) {
					facility = (Facility)context.getBean("hotelmeetingroom");
				} else {
					facility = (Facility)context.getBean("facility");
				}

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
			FacilityRecord record = new FacilityRecord(FacilityRecord.TYPE_FACILITY);
			facilityPersistency.addRecord(record);
			
			facility = (Facility)context.getBean("facility");
			
			facility.retrieveFacilityRecord(record);
			
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facility;
	}	
	
	// Add a hotel bed room
	public Facility addHotelBedRoom() {
		
		Facility facility = null;
		
		try {
			FacilityRecord record = new FacilityRecord(FacilityRecord.TYPE_BEDROOM);
			facilityPersistency.addRecord(record);
			
			facility = (Facility)context.getBean("hotelbedroom");
			
			facility.retrieveFacilityRecord(record);
			
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facility;
	}	
	
	// Add a hotel meeting room
	public Facility addHotelMeetingRoom() {
		
		Facility facility = null;
		
		try {
			FacilityRecord record = new FacilityRecord(FacilityRecord.TYPE_MEETINGROOM);
			facilityPersistency.addRecord(record);
			
			facility = (Facility)context.getBean("hotelmeetingroom");
			
			facility.retrieveFacilityRecord(record);
			
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facility;
	}	
}