package com.fms.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.model.facility.FacilityPersistencyInterface;
import com.fms.model.facility.FacilityUseRecord;

public class FacilityUseTableRAM implements FacilityPersistencyInterface<FacilityUseRecord> {

    private static FacilityUseTableRAM instance = null; 
  
	private Map<String, FacilityUseRecord> records = new HashMap<String, FacilityUseRecord>();
	private int index = 0;

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
	
	public List<FacilityUseRecord> listRecords() {
		
		List<FacilityUseRecord> recordList = new ArrayList<FacilityUseRecord>(records.values());
		
		return recordList;
	}
	
	public List<FacilityUseRecord> listRecordsByFacilityId(String facilityId) {
		
		List<FacilityUseRecord> recordList = new ArrayList<FacilityUseRecord>();
		
		for (Map.Entry<String, FacilityUseRecord> entry : records.entrySet()) {
			if (entry.getValue().getFacilityId().equals(facilityId)) {
				recordList.add(entry.getValue());
			}
		}

		return recordList;
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
