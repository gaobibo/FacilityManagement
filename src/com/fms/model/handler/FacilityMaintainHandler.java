package com.fms.model.handler;

import java.util.ArrayList;
import java.util.List;

import com.fms.model.facility.FacilityMaintainInterface;
import com.fms.model.facility.FacilityMaintainRecord;
import com.fms.model.facility.FacilityPersistencyInterface;

public class FacilityMaintainHandler implements FacilityMaintainInterface {

	private FacilityPersistencyInterface<FacilityMaintainRecord> ficilityMaintainPersistency;
	
	public FacilityMaintainHandler(FacilityPersistencyInterface<FacilityMaintainRecord> ficilityMaintainPersistency) {
		this.ficilityMaintainPersistency = ficilityMaintainPersistency;
	}
	
	// List all the maintain records of facility
	public List<FacilityMaintainRecord> listMaintenance(String facilityId) {
		
		List<FacilityMaintainRecord> recordList = new ArrayList<FacilityMaintainRecord>();
		
		try {
			recordList = ficilityMaintainPersistency.listRecordsByFacilityId(facilityId);
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		
		return recordList;
	}
}
