package com.fms.dal;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.fms.model.facility.FacilityPersistencyInterface;
import com.fms.model.facility.FacilityRecord;

public class FacilityTableRAM implements FacilityPersistencyInterface<FacilityRecord> {
    
	private Map<String, FacilityRecord> facilities = new HashMap<String, FacilityRecord>();
	private int index = 0;
		
	public List<FacilityRecord> listRecords() {
		
		List<FacilityRecord> facilityList = new ArrayList<FacilityRecord>(facilities.values());
		
		return facilityList;
	}
	
	public List<FacilityRecord> listRecordsByFacilityId(String facilityId) {
		
		List<FacilityRecord> facilityList = new ArrayList<FacilityRecord>();
		
		facilityList.add(facilities.get(facilityId));
		
		return facilityList;
	}
	
	public FacilityRecord getRecord(String facilityId) {
	    return facilities.get(facilityId);
	}
	
	public void addRecord(FacilityRecord facility) {
		facility.setFacilityId(Integer.toString(index++));
		facilities.put(facility.getFacilityId(), facility);
    }
	
	public void removeRecord(String facilityId) {
		facilities.remove(facilityId);
    }
	
	public boolean changeRecord(FacilityRecord facility) {
		
		boolean result = false;
		if (facilities.containsKey(facility.getFacilityId()) == true) {
			facilities.put(facility.getFacilityId(), facility);
			result = true;
		}
		
		return result;
    }
}
