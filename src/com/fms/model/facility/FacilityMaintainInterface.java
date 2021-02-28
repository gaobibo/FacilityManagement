package com.fms.model.facility;

import java.util.Date;
import java.util.List;

public interface FacilityMaintainInterface {
	public List<FacilityMaintainRecord> listMaintenance(String facilityId);
	public List<FacilityMaintainRecord> listMaintRequests(String facilityId);
	public List<FacilityMaintainRecord> listFacilityProblems(String facilityId);
	public FacilityMaintainRecord makeFacilityMaintRequest(String facilityId, String employeeId, Date submittedDate, FacilityMaintainRecord.MaintainType maintainType);
	public boolean scheduleMaintenance(String facilityId, String recordId, Date scheduledDate);
	public boolean completeMaintenance(String facilityId, String recordId, Date completedDate, double maintainCost);
	public double calcMaintenaceCostForFacility(String facilityId);
	public double calcProblemRateForFacility(String facilityId);
	public double calcDownTimeForFacility(String facilityId);
}
