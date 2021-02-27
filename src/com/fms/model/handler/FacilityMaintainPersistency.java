package com.fms.model.handler;

import java.util.List;

import com.fms.model.facility.FacilityMaintainRecord;

public interface FacilityMaintainPersistency {
	
	public List<FacilityMaintainRecord> listRecordsByFacilityId(String facilityId);
	public FacilityMaintainRecord getRecord(String recordId);
	
	public void addRecord(FacilityMaintainRecord record);
	public void removeRecord(String recordId);
	public boolean changeRecord(FacilityMaintainRecord record);
}
