package com.fms.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.model.facility.FacilityUseRecord;
import com.fms.model.handler.FacilityUsePersistency;

public class FacilityUseTableRAM implements FacilityUsePersistency {

    // static variable
    private static FacilityUseTableRAM instance = null; 
  
    // private constructor
    private FacilityUseTableRAM() 
    { 
    } 
  
    // static method to create instance of FacilityUseTableRAM class 
    public static FacilityUseTableRAM getInstance() 
    { 
        if (instance == null) {
        	instance = new FacilityUseTableRAM();
        }
  
        return instance; 
    }
	
	private Map<String, FacilityUseRecord> records = new HashMap<String, FacilityUseRecord>();
	private int index = 0;

	public List<FacilityUseRecord> listRecordsByFacilityId(String facilityId) {
		
		List<FacilityUseRecord> recordList = new ArrayList<FacilityUseRecord>();
		
		for (Map.Entry<String, FacilityUseRecord> entry : records.entrySet()) {
			if (entry.getValue().getFacilityId().equals(facilityId)) {
				recordList.add(entry.getValue());
			}
		}

		return recordList;
	}
	
	public FacilityUseRecord getLatestRecord(String facilityId) {
		
		FacilityUseRecord latestRecord = null;
		
		for (Map.Entry<String, FacilityUseRecord> entry : records.entrySet()) {
			FacilityUseRecord record = entry.getValue();
			if (record.getFacilityId().equals(facilityId)) {
				if (latestRecord != null) {
					if (latestRecord.getAssignDate().before(record.getAssignDate()) == true) {
						latestRecord = record;
					}
				} else {
					latestRecord = record;
				}
			}
		}
		
	    return latestRecord;
	}
	
	public FacilityUseRecord getRecord(String recordId) {
	    return records.get(recordId);
	}
	
	public void addRecord(FacilityUseRecord record) {
		record.setRecordId(Integer.toString(index++));
		records.put(record.getRecordId(), record);
    }
	
	public void removeRecord(String recordId) {
		records.remove(recordId);
    }
	
	public boolean changeRecord(FacilityUseRecord record) {
		
		boolean result = false;
		
		if (records.containsKey(record.getRecordId()) == true) {
			records.put(record.getRecordId(), record);
			result = true;
		}
		
		return result;
    }
}
