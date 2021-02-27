package com.fms.model.facility;

import java.util.Date;
import java.util.List;

public interface FacilityUseInterface {
	public List<FacilityUseRecord> listActualUsage(String facilityId);
	public double calcUsageRate(String facilityId, Date startDate, Date endDate);
	public boolean isInUseDuringInterval(String facilityId, Date startDate, Date endDate);
	public boolean assignFacilityToUse(String facilityId, String employeeId);
	public boolean vacateFacility(String facilityId);
}
