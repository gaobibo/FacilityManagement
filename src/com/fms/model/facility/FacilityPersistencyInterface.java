package com.fms.model.facility;

import java.util.List;

public interface FacilityPersistencyInterface<T> {
	// List all the records
	public List<T> listRecords();
	
	// List the records with the specific facility ID
	public List<T> listRecordsByFacilityId(String facilityId);
	
	// Get a record with the specific record ID
	public T getRecord(String recordId);
	
	// Add a record
	public void addRecord(T record);
	
	// Remove a record with the specific record ID
	public void removeRecord(String recordId);
	
	// Change a record
	public boolean changeRecord(T record);
}
