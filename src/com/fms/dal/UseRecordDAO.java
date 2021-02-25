package com.fms.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.model.use.UseRecord;

public class UseRecordDAO {

    // static variable
    private static UseRecordDAO instance = null; 
  
    // private constructor
    private UseRecordDAO() 
    { 
    } 
  
    // static method to create instance of UseRecordDAO class 
    public static UseRecordDAO getInstance() 
    { 
        if (instance == null) {
        	instance = new UseRecordDAO();
        }
  
        return instance; 
    }
	
	private Map<String, UseRecord> records = new HashMap<String, UseRecord>();
	private int index = 0;

	public List<UseRecord> listRecordsByFacilityId(String facilityId) {
		
		List<UseRecord> recordList = new ArrayList<UseRecord>();
		
		for (Map.Entry<String, UseRecord> entry : records.entrySet()) {
			if (entry.getValue().getFacilityId().equals(facilityId)) {
				recordList.add(entry.getValue());
			}
		}

		return recordList;
	}
	
	public UseRecord getLatestRecord(String facilityId) {
		
		UseRecord latestRecord = null;
		
		for (Map.Entry<String, UseRecord> entry : records.entrySet()) {
			UseRecord record = entry.getValue();
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
	
	public UseRecord getRecord(String recordId) {
	    return records.get(recordId);
	}
	
	public void addRecord(UseRecord record) {
		record.setRecordId(Integer.toString(index++));
		records.put(record.getRecordId(), record);
    }
	
	public void removeRecord(String recordId) {
		records.remove(recordId);
    }
	
	public boolean changeRecord(UseRecord record) {
		
		boolean result = false;
		
		if (records.containsKey(record.getRecordId()) == true) {
			records.put(record.getRecordId(), record);
			result = true;
		}
		
		return result;
    }
}
