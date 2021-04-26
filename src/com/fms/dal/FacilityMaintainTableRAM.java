package com.fms.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.model.facility.FacilityMaintainRecord;
import com.fms.model.facility.FacilityPersistencyInterface;

public class FacilityMaintainTableRAM  implements FacilityPersistencyInterface<FacilityMaintainRecord> {

	private Map<String, FacilityMaintainRecord> records = new HashMap<String, FacilityMaintainRecord>();
	private int index = 0;

	public List<FacilityMaintainRecord> listRecords() {
		
		List<FacilityMaintainRecord> recordList = new ArrayList<FacilityMaintainRecord>(records.values());
		
		return recordList;
	}
	
	public List<FacilityMaintainRecord> listRecordsByFacilityId(String facilityId) {
		
		List<FacilityMaintainRecord> recordList = new ArrayList<FacilityMaintainRecord>();

		for (Map.Entry<String, FacilityMaintainRecord> entry : records.entrySet()) {
			if (entry.getValue().getFacilityId().equals(facilityId)) {
				recordList.add(entry.getValue());
			}
		}
		
		return recordList;
	}
	
	public FacilityMaintainRecord getRecord(String recordId) {
	    return records.get(recordId);
	  }
	
	public void addRecord(FacilityMaintainRecord record) {
		record.setRecordId(Integer.toString(index++));
		records.put(record.getRecordId(), record);
    }
	
	public void removeRecord(String recordId) {
		records.remove(recordId);
    }
	
	public boolean changeRecord(FacilityMaintainRecord record) {
		
		boolean result = false;
		
		if (records.containsKey(record.getRecordId()) == true) {
			records.put(record.getRecordId(), record);
			result = true;
		}
		
		return result;
    }
}
