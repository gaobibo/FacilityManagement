package com.fms.model.facility;

import java.util.Date;
import java.util.List;

import com.fms.model.use.UseRecord;

public interface FacilityUseInterface {
	public List<UseRecord> listActualUsage(String facilityId);
	public double calcUsageRate(String facilityId, Date startDate, Date endDate);
	public boolean isInUseDuringInterval(String facilityId, Date startDate, Date endDate);
	public boolean assignFacilityToUse(String facilityId, String employeeId);
	public boolean vacateFacilityFromUse(String facilityId);
}
