package com.fms.model.handler;

import java.util.List;

import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityDetail;
import com.fms.model.facility.HotelBedRoom;
import com.fms.model.facility.HotelMeetingRoom;
import com.fms.model.facility.Resource;
import com.fms.model.facility.ResourceVisitor;

public class ResourceTreeView implements ResourceVisitor {
	
	public void visitResourceList(List<? extends Resource> resourceList) {
		
		System.out.println("-----------------------------------------------------------------");
		for (Resource resource : resourceList) {
			resource.acceptVisitor(this);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	public void visitFacility(Facility facility) {
		
		String id = facility.getFacilityId();
		String type = facility.getFacilityType();
		String status = facility.getFacilityStatus();
		FacilityDetail facilityDetail = facility.getFacilityDetail();
		
		String name = facilityDetail.getFacilityName();		
		String address = facilityDetail.getFacilityAddress();
		int capacity = facilityDetail.getFacilityCapacity();
		
		System.out.println("Facility ID = " + id);
		System.out.println("\t|--- Type     : " + type);
		System.out.println("\t|--- Status   : " + status);
		System.out.println("\t|--- Name     : " + name);
		System.out.println("\t|--- Capacity : " + capacity);
		System.out.println("\t|--- Address  : " + address);
	}
	
	public void visitHotelBedRoom(HotelBedRoom bedroom) {
		
		String id = bedroom.getFacilityId();
		String type = bedroom.getFacilityType();
		String status = bedroom.getFacilityStatus();
		FacilityDetail detail = bedroom.getFacilityDetail();
		
		String name = detail.getFacilityName();		
		String address = detail.getFacilityAddress();
		int capacity = detail.getFacilityCapacity();
		
		System.out.println("BedRoom ID = " + id);
		System.out.println("\t|--- Type     : " + type);
		System.out.println("\t|--- Status   : " + status);
		System.out.println("\t|--- Name     : " + name);
		System.out.println("\t|--- Capacity : " + capacity);
		System.out.println("\t|--- Address  : " + address);
	}
	
	public void visitHotelMeetingRoom(HotelMeetingRoom meetingroom) {
		
		String id = meetingroom.getFacilityId();
		String type = meetingroom.getFacilityType();
		String status = meetingroom.getFacilityStatus();
		FacilityDetail detail = meetingroom.getFacilityDetail();
		
		String name = detail.getFacilityName();		
		String address = detail.getFacilityAddress();
		int capacity = detail.getFacilityCapacity();
		
		System.out.println("MeetingRoom ID = " + id);
		System.out.println("\t|--- Type     : " + type);
		System.out.println("\t|--- Status   : " + status);
		System.out.println("\t|--- Name     : " + name);
		System.out.println("\t|--- Capacity : " + capacity);
		System.out.println("\t|--- Address  : " + address);
	}
}
