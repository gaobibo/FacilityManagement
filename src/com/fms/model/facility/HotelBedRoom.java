package com.fms.model.facility;

import java.util.Date;

public class HotelBedRoom extends Facility {
	
	public void acceptVisitor(ResourceVisitor visitor) {
		visitor.visitHotelBedRoom(this);
	}
	
	// Calculate the usage rate of the facility
	public double calcUsageRate(Date startDate, Date endDate) {
		System.out.println("calcUsageRate in HotelBedRoom");
		return facilityUse.calcUsageRate(facilityId, startDate, endDate);
	}
}
