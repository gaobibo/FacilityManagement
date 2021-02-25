package com.fms.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.model.maintain.MaintainRecord;

public class MaintainRecordDAO {

    // static variable
    private static MaintainRecordDAO instance = null; 
  
    // private constructor
    private MaintainRecordDAO() 
    { 
    } 
  
    // static method to create instance of MaintainRecordDAO class 
    public static MaintainRecordDAO getInstance() 
    { 
        if (instance == null) {
        	instance = new MaintainRecordDAO();
        }
  
        return instance; 
    }
	
	private Map<String, MaintainRecord> records = new HashMap<String, MaintainRecord>();
	private int index = 0;
	
	public List<MaintainRecord> listRecordsByFacilityId(String facilityId) {
		
		List<MaintainRecord> recordList = new ArrayList<MaintainRecord>(records.values());

		for (Map.Entry<String, MaintainRecord> entry : records.entrySet()) {
			if (entry.getValue().getFacilityId().equals(facilityId)) {
				recordList.add(entry.getValue());
			}
		}
		
		return recordList;
	}
	
	public MaintainRecord getRecord(String recordId) {
	    return records.get(recordId);
	  }
	
	public void addRecord(MaintainRecord record) {
		record.setRecordId(Integer.toString(index++));
		records.put(record.getRecordId(), record);
    }
	
	public void removeRecord(String recordId) {
		records.remove(recordId);
    }
	
	public boolean changeRecord(MaintainRecord record) {
		
		boolean result = false;
		
		if (records.containsKey(record.getRecordId()) == true) {
			records.put(record.getRecordId(), record);
			result = true;
		}
		
		return result;
    }
}
