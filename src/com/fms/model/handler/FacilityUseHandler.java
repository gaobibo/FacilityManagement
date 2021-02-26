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
		
		long difference = startDate.getTime() - endDate.getTime();
		
		if (difference > 0) {
		    	
			long   usageTime = 0;
			
			try {
				List<UseRecord> recordList = recordDAO.listRecordsByFacilityId(facilityId);
				
				if (recordList != null) {
					
					for (UseRecord record : recordList) {
						
						if (record.getAssignDate().after(startDate) && record.getVacateDate().before(endDate)) {
							usageTime += Math.max((record.getVacateDate().getTime() - record.getAssignDate().getTime()), 0);	
						} else if (record.getAssignDate().after(startDate) && record.getAssignDate().before(endDate)) {
							usageTime += Math.max((endDate.getTime() - record.getAssignDate().getTime()), 0);
						} else if (record.getVacateDate().after(startDate) && record.getVacateDate().before(endDate)) {
							usageTime += Math.max((record.getVacateDate().getTime() - startDate.getTime()), 0);
						}
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
					
					if (record.getAssignDate().after(startDate) && record.getAssignDate().before(endDate)) {
						isInUse = true;
						break;
					} else if (record.getVacateDate().after(startDate) && record.getVacateDate().before(endDate)) {
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
			UseRecord record = new UseRecord();
			record.setFacilityId(facilityId);
			record.setEmployeeId(employeeId);
			record.setAssignDate(new Date());
			recordDAO.addRecord(record);
			result = true;
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }

		return result;
	}
	
	// Vacate a facility
	public boolean vacateFacilityFromUse(String facilityId) {
		
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