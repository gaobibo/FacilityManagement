package com.fms.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.model.inspect.InspectRecord;

public class InspectRecordDAO {

    // static variable
    private static InspectRecordDAO instance = null; 
  
    // private constructor
    private InspectRecordDAO() 
    { 
    } 
  
    // static method to create instance of InspectRecordDAO class 
    public static InspectRecordDAO getInstance() 
    { 
        if (instance == null) {
        	instance = new InspectRecordDAO();
        }
  
        return instance; 
    }
	
	private Map<String, InspectRecord> records = new HashMap<String, InspectRecord>();
	private int index = 0;
	
	public List<InspectRecord> listRecordsByFacilityId(String facilityId) {
		
		List<InspectRecord> recordList = new ArrayList<InspectRecord>(records.values());

		for (Map.Entry<String, InspectRecord> entry : records.entrySet()) {
			if (entry.getValue().getFacilityId().equals(facilityId)) {
				recordList.add(entry.getValue());
			}
		}
		
		return recordList;
	}
	
	public InspectRecord getRecord(String recordId) {
	    return records.get(recordId);
	  }
	
	public void addRecord(InspectRecord record) {
		record.setRecordId(Integer.toString(index++));
		records.put(record.getRecordId(), record);
    }
	
	public void removeRecord(String recordId) {
		records.remove(recordId);
    }
	
	public boolean changeRecord(InspectRecord record) {
		
		boolean result = false;
		
		if (records.containsKey(record.getRecordId()) == true) {
			records.put(record.getRecordId(), record);
			result = true;
		}
		
		return result;
    }
}
