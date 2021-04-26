package com.fms.model.handler;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fms.model.facility.FacilityUseRecord;

public class FacilityDailyUseHandler extends FacilityUseHandler {

	// Calculate the usage rate of facility during interval
	public double calcUsageRate(String facilityId, Date startDate, Date endDate) {
		
		System.out.println("calcUsageRate by DAYS");
		
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
			
			double usageTimeInDays = TimeUnit.DAYS.convert(usageTime, TimeUnit.MILLISECONDS);
			double diffeTimeInDays = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
			
			if (diffeTimeInDays > 0) {
				usageRate = usageTimeInDays / diffeTimeInDays;	
			}
		}

		return usageRate;
	}
}