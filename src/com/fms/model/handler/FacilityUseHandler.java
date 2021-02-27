package com.fms.model.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.model.facility.FacilityPersistencyInterface;
import com.fms.model.facility.FacilityUseInterface;
import com.fms.model.facility.FacilityUseRecord;

public class FacilityUseHandler implements FacilityUseInterface {
	
	private FacilityPersistencyInterface<FacilityUseRecord> ficilityUsePersistency;
	
	public FacilityUseHandler(FacilityPersistencyInterface<FacilityUseRecord> ficilityUsePersistency) {
		this.ficilityUsePersistency = ficilityUsePersistency;
	}
	
	// List all the use records of facility
	public List<FacilityUseRecord> listActualUsage(String facilityId) {
		
		List<FacilityUseRecord> recordList = new ArrayList<FacilityUseRecord>();
		
		try {
			recordList = ficilityUsePersistency.listRecordsByFacilityId(facilityId);
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		
		return recordList;
	}
	
	// Calculate the usage rate of facility during interval
	public double calcUsageRate(String facilityId, Date startDate, Date endDate) {
		
		double usageRate = 0;
		
		long difference = endDate.getTime() - startDate.getTime();
		
		if (difference > 0) {
		    	
			long usageTime = 0;
			
			try {
				List<FacilityUseRecord> recordList = ficilityUsePersistency.listRecordsByFacilityId(facilityId);
				
				if (recordList != null) {
					
					for (FacilityUseRecord record : recordList) {
						usageTime += Math.max((Math.min(record.getVacateDate().getTime(), endDate.getTime()) - 
								Math.max(record.getAssignDate().getTime(), startDate.getTime())), 0);
					}
				}
		    } catch (Exception se) {
		    	System.err.println(se.getMessage());
		    }
			
			usageRate = (double)usageTime / (double)difference;
		}

		return usageRate;
	}
	
	// Check if a facility is in use during interval
	public boolean isInUseDuringInterval(String facilityId, Date startDate, Date endDate) {
		
		boolean isInUse = false;
		
		try {
			List<FacilityUseRecord> recordList = ficilityUsePersistency.listRecordsByFacilityId(facilityId);
			
			if (recordList != null) {
				
				for (FacilityUseRecord record : recordList) {
					
					if ((record.getAssignDate().compareTo(endDate) <= 0) && (record.getVacateDate().compareTo(startDate) >= 0)) {
						isInUse = true;
						break;
					}
				}
			}
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		return isInUse;
	}
	
	// Assign a facility to an employee
	public boolean assignFacilityToUse(String facilityId, String employeeId) {
		
		boolean result = false;
		
		try {					
			FacilityUseRecord record = new FacilityUseRecord();
			record.setFacilityId(facilityId);
			record.setEmployeeId(employeeId);
			record.setAssignDate(new Date());
			ficilityUsePersistency.addRecord(record);
			result = true;
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }

		return result;
	}
	
	// Vacate a facility
	public boolean vacateFacility(String facilityId) {
		
		boolean result = false;
		
		try {
			FacilityUseRecord latestRecord = null;
			
			List<FacilityUseRecord> recordList = ficilityUsePersistency.listRecordsByFacilityId(facilityId);
			
			for (FacilityUseRecord record : recordList) {
				if (latestRecord == null) {
					latestRecord = record;
				} else {
					if (latestRecord.getAssignDate().before(record.getAssignDate()) == true) {
						latestRecord = record;
					}
				}
			}
			
			if (latestRecord != null) {
				latestRecord.setVacateDate(new Date());
				result = ficilityUsePersistency.changeRecord(latestRecord);				
			}
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }

		return result;
	}
}