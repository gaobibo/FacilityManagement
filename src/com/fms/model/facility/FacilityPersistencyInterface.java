package com.fms.model.facility;

import java.util.List;

public interface FacilityPersistencyInterface<T> {
	public List<T> listRecords();
	public List<T> listRecordsByFacilityId(String facilityId);
	public T getRecord(String recordId);
	public void addRecord(T record);
	public void removeRecord(String recordId);
	public boolean changeRecord(T record);
}
