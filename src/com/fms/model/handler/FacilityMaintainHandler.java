package com.fms.model.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.model.facility.FacilityMaintainInterface;
import com.fms.model.facility.FacilityMaintainRecord;
import com.fms.model.facility.FacilityPersistencyInterface;

public class FacilityMaintainHandler implements FacilityMaintainInterface {
	
	private FacilityPersistencyInterface<FacilityMaintainRecord> ficilityMaintainPersistency;
	
	public FacilityPersistencyInterface<FacilityMaintainRecord> getFacilityMaintainPersistency() {
		return ficilityMaintainPersistency;
	}
	
	public void setFacilityMaintainPersistency(FacilityPersistencyInterface<FacilityMaintainRecord> ficilityMaintainPersistency) {
		this.ficilityMaintainPersistency = ficilityMaintainPersistency;
	}
	
	// List all the maintain records of facility
	public List<FacilityMaintainRecord> listMaintenance(String facilityId) {
		
		List<FacilityMaintainRecord> recordList = new ArrayList<FacilityMaintainRecord>();
		
		try {
			recordList = ficilityMaintainPersistency.listRecordsByFacilityId(facilityId);
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		
		return recordList;
	}
	
	public List<FacilityMaintainRecord> listMaintRequests(String facilityId) {
		
		List<FacilityMaintainRecord> submittedRecordList = new ArrayList<FacilityMaintainRecord>();
		List<FacilityMaintainRecord> recordList = new ArrayList<FacilityMaintainRecord>();
		
		try {
			recordList = ficilityMaintainPersistency.listRecordsByFacilityId(facilityId);
			
			for (FacilityMaintainRecord record : recordList) {
				if (record.getStatus() == FacilityMaintainRecord.STATUS_SUBMITTED) {
					submittedRecordList.add(record);
				}
			}
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		
		return submittedRecordList;
	}
	
	public List<FacilityMaintainRecord> listFacilityProblems(String facilityId) {
		
		List<FacilityMaintainRecord> problematicRecordList = new ArrayList<FacilityMaintainRecord>();
		List<FacilityMaintainRecord> recordList = new ArrayList<FacilityMaintainRecord>();
		
		try {
			recordList = ficilityMaintainPersistency.listRecordsByFacilityId(facilityId);
			
			for (FacilityMaintainRecord record : recordList) {
				if (record.getMaintainType() == FacilityMaintainRecord.MaintainType.PROBLEMATIC) {
					problematicRecordList.add(record);
				}
			}
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		
		return problematicRecordList;
	}
	
	public FacilityMaintainRecord makeFacilityMaintRequest(String facilityId, 
														   String employeeId, 
														   Date submittedDate,
														   FacilityMaintainRecord.MaintainType maintainType) {
		
		FacilityMaintainRecord record = new FacilityMaintainRecord();
		record.setFacilityId(facilityId);
		record.setEmployeeId(employeeId);
		record.setMaintainType(maintainType);
		record.setSubmittedDate(submittedDate);
		
		try {					
			ficilityMaintainPersistency.addRecord(record);
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }

		return record;
	}
	
	public boolean scheduleMaintenance(String facilityId, String recordId, Date scheduledDate) {
		
		boolean result = false;
		
		try {
			FacilityMaintainRecord record = ficilityMaintainPersistency.getRecord(recordId);
			
			if ((record != null) && (record.getFacilityId().equals(facilityId))) {
				record.setStatus(FacilityMaintainRecord.STATUS_SCHEDULED);
				record.setScheduledDate(scheduledDate);
				
				ficilityMaintainPersistency.changeRecord(record);
				
				result = true;
			}
		} catch (Exception se) {
			System.err.println(se.getMessage());
		}
		
		return result;
	}
	
	public boolean completeMaintenance(String facilityId, String recordId, Date completedDate, double maintainCost) {
		
		boolean result = false;
		
		try {
			FacilityMaintainRecord record = ficilityMaintainPersistency.getRecord(recordId);
			
			if ((record != null) && (record.getFacilityId().equals(facilityId))) {
				record.setStatus(FacilityMaintainRecord.STATUS_COMPLETED);
				record.setCompletedDate(completedDate);
				record.setMaintainCost(maintainCost);
				
				ficilityMaintainPersistency.changeRecord(record);
				
				result = true;
			}
		} catch (Exception se) {
			System.err.println(se.getMessage());
		}
		
		return result;
	}
	
	public double calcMaintenaceCostForFacility(String facilityId) {
		
		double maintainCost = 0;
		List<FacilityMaintainRecord> recordList = new ArrayList<FacilityMaintainRecord>();
		
		try {
			recordList = ficilityMaintainPersistency.listRecordsByFacilityId(facilityId);
			
			for (FacilityMaintainRecord record : recordList) {
				maintainCost += record.getMaintainCost();
			}
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		
		return maintainCost;
	}
	
	public double calcProblemRateForFacility(String facilityId) {
		
		double problemRate = 0;
		double problemNumber = 0;
		
		List<FacilityMaintainRecord> recordList = new ArrayList<FacilityMaintainRecord>();
		
		try {
			recordList = ficilityMaintainPersistency.listRecordsByFacilityId(facilityId);
			
			for (FacilityMaintainRecord record : recordList) {
				if (record.getMaintainType() == FacilityMaintainRecord.MaintainType.PROBLEMATIC) {
					problemNumber++;
				}
			}			
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		
		if ((recordList != null) && (recordList.size() > 0)) {
			problemRate = problemNumber / recordList.size();
		}
		
		return problemRate;
	}
	
	public double calcDownTimeForFacility(String facilityId) {

		double downTime = 0;
		List<FacilityMaintainRecord> recordList = new ArrayList<FacilityMaintainRecord>();
		
		try {
			recordList = ficilityMaintainPersistency.listRecordsByFacilityId(facilityId);
			
			for (FacilityMaintainRecord record : recordList) {
				if (record.getMaintainType() == FacilityMaintainRecord.MaintainType.PROBLEMATIC) {
					if (record.getCompletedDate() != null && record.getSubmittedDate() != null) {
						downTime += Math.max((record.getCompletedDate().getTime() - record.getSubmittedDate().getTime()), 0);
					}
				}
			}
	    } catch (Exception se) {
	    	System.err.println(se.getMessage());
	    }
		
		return downTime / (24 * 60 * 60 * 1000);
	}
}
