package com.fms.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.model.facility.FacilityInspectRecord;
import com.fms.model.handler.FacilityInspectPersistency;

public class FacilityInspectTableRAM implements FacilityInspectPersistency {

    // static variable
    private static FacilityInspectTableRAM instance = null; 
  
    // private constructor
    private FacilityInspectTableRAM() 
    { 
    } 
  
    // static method to create instance of FacilityInspectTableRAM class 
    public static FacilityInspectTableRAM getInstance() 
    { 
        if (instance == null) {
        	instance = new FacilityInspectTableRAM();
        }
  
        return instance; 
    }
	
	private Map<String, FacilityInspectRecord> records = new HashMap<String, FacilityInspectRecord>();
	private int index = 0;
	
	public List<FacilityInspectRecord> listRecordsByFacilityId(String facilityId) {
		
		List<FacilityInspectRecord> recordList = new ArrayList<FacilityInspectRecord>();

		for (Map.Entry<String, FacilityInspectRecord> entry : records.entrySet()) {
			if (entry.getValue().getFacilityId().equals(facilityId)) {
				recordList.add(entry.getValue());
			}
		}
		
		return recordList;
	}
	
	public FacilityInspectRecord getRecord(String recordId) {
	    return records.get(recordId);
	  }
	
	public void addRecord(FacilityInspectRecord record) {
		record.setRecordId(Integer.toString(index++));
		records.put(record.getRecordId(), record);
    }
	
	public void removeRecord(String recordId) {
		records.remove(recordId);
    }
	
	public boolean changeRecord(FacilityInspectRecord record) {
		
		boolean result = false;
		
		if (records.containsKey(record.getRecordId()) == true) {
			records.put(record.getRecordId(), record);
			result = true;
		}
		
		return result;
    }
}
