package com.fms.model.handler;

import java.util.List;

import com.fms.model.facility.FacilityInspectRecord;

public interface FacilityInspectPersistency {
	
	public List<FacilityInspectRecord> listRecordsByFacilityId(String facilityId);
	public FacilityInspectRecord getRecord(String recordId);

	public void addRecord(FacilityInspectRecord record);
	public void removeRecord(String recordId);
	public boolean changeRecord(FacilityInspectRecord record);
}
