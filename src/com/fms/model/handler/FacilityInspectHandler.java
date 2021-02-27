package com.fms.model.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.model.facility.FacilityInspectInterface;
import com.fms.model.facility.FacilityInspectRecord;

public class FacilityInspectHandler implements FacilityInspectInterface {
	
	private FacilityInspectPersistency ficilityInspectPersistency;
	
	public FacilityInspectHandler(FacilityInspectPersistency ficilityInspectPersistency) {
		this.ficilityInspectPersistency = ficilityInspectPersistency;
	}
	
	// List all the inspection records of facility
	public List<FacilityInspectRecord> listInspections(String facilityId) {
		
		List<FacilityInspectRecord> recordList = new ArrayList<FacilityInspectRecord>();
		
		try {
			recordList = ficilityInspectPersistency.listRecordsByFacilityId(facilityId);
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		
		return recordList;
	}
	
	// Inspect the facility
	public boolean inspectFacility(String facilityId, String employeeId) {
		
		boolean result = false;
		
		try {					
			FacilityInspectRecord record = new FacilityInspectRecord();
			record.setFacilityId(facilityId);
			record.setEmployeeId(employeeId);
			record.setInspectDate(new Date());
			ficilityInspectPersistency.addRecord(record);
			result = true;
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }

		return result;
	}
}
