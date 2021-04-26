package com.fms.model.facility;

import java.util.List;

public interface ResourceVisitor {
	
	public void visitResourceList(List<? extends Resource> resourceList);
	
	public void visitFacility(Facility facility);
	public void visitHotelBedRoom(HotelBedRoom bedroom);
	public void visitHotelMeetingRoom(HotelMeetingRoom meetingroom);
}
