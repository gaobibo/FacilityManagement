package com.fms.model.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.dal.UseRecordDAO;
import com.fms.model.facility.FacilityUseInterface;

public class FacilityUseHandler implements FacilityUseInterface {
	
	private UseRecordDAO recordDAO = UseRecordDAO.getInstance();
	
	// List all the use records of facility
	public List<UseRecord> listActualUsage(String facilityId) {
		
		List<UseRecord> recordList = new ArrayList<UseRecord>();
		
		try {
			recordList = recordDAO.listRecordsByFacilityId(facilityId);
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
				List<UseRecord> recordList = recordDAO.listRecordsByFacilityId(facilityId);
				
				if (recordList != null) {
					
					for (UseRecord record : recordList) {
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
			List<UseRecord> recordList = recordDAO.listRecordsByFacilityId(facilityId);
			
			if (recordList != null) {
				
				for (UseRecord record : recordList) {
					
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
	public boolean assignFacilityToUse(String facilityId) {
		
		boolean result = false;
		
		try {					
			UseRecord record = new UseRecord();
			record.setFacilityId(facilityId);
			record.setAssignDate(new Date());
			recordDAO.addRecord(record);
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
			UseRecord record = recordDAO.getLatestRecord(facilityId);
			record.setVacateDate(new Date());
			result = recordDAO.changeRecord(record);
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }

		return result;
	}
}