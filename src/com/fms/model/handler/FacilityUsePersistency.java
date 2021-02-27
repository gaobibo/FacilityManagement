package com.fms.model.handler;

import java.util.List;

import com.fms.model.facility.FacilityUseRecord;

public interface FacilityUsePersistency {
	
	public List<FacilityUseRecord> listRecordsByFacilityId(String facilityId);
	public FacilityUseRecord getLatestRecord(String facilityId);
	public FacilityUseRecord getRecord(String recordId);
	
	public void addRecord(FacilityUseRecord record);
	public void removeRecord(String recordId);
	public boolean changeRecord(FacilityUseRecord record);
}
