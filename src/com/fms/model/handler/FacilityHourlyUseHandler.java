package com.fms.model.handler;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fms.model.facility.FacilityUseRecord;

public class FacilityHourlyUseHandler extends FacilityUseHandler {

	// Calculate the usage rate of facility during interval
	public double calcUsageRate(String facilityId, Date startDate, Date endDate) {
		
		System.out.println("calcUsageRate by HOURS");
		
		double usageRate = 0;
		
		long difference = endDate.getTime() - startDate.getTime();
		
		if (difference > 0) {
		    	
			long usageTime = 0;
			
			try {
				List<FacilityUseRecord> recordList = facilityUsePersistency.listRecordsByFacilityId(facilityId);
				
				if (recordList != null) {
					
					for (FacilityUseRecord record : recordList) {
						usageTime += Math.max((Math.min(record.getVacateDate().getTime(), endDate.getTime()) - 
								Math.max(record.getAssignDate().getTime(), startDate.getTime())), 0);
					}
				}
		    } catch (Exception se) {
		    	System.err.println(se.getMessage());
		    }
			
			double usageTimeInHours = TimeUnit.HOURS.convert(usageTime, TimeUnit.MILLISECONDS);
			double diffeTimeInHours = TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
			
			if (diffeTimeInHours > 0) {
				usageRate = usageTimeInHours / diffeTimeInHours;				
			}

		}

		return usageRate;
	}
}