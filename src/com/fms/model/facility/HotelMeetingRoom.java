package com.fms.model.facility;

import java.util.Date;

import com.fms.dal.FacilityTableRAM;
import com.fms.model.handler.FacilityInspectHandler;
import com.fms.model.handler.FacilityMaintainHandler;

public class HotelMeetingRoom extends Facility {

	public void acceptVisitor(ResourceVisitor visitor) {
		visitor.visitHotelMeetingRoom(this);
	}
	
	// Calculate the usage rate of the facility
	public double calcUsageRate(Date startDate, Date endDate) {
		System.out.println("calcUsageRate in HotelMeetingRoom");
		return facilityUse.calcUsageRate(facilityId, startDate, endDate);
	}
}
