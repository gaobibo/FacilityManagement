package com.fms.test;

import com.fms.dal.FacilityDAO;
import com.fms.model.facility.Address;
import com.fms.model.facility.Facility;
import com.fms.model.handler.FacilityInspectHandler;
import com.fms.model.handler.FacilityMaintainHandler;
import com.fms.model.handler.FacilityUseHandler;

public class FacilityUnitTest {
	
	public static void main (String args[]) throws Exception {
		
		unitTestFacility();

	}
	
	private static void unitTestFacility() {
		
		boolean result = true;
		final String street = "800 East Madison St.";
		final String unit = "Suite 2000";
		final String city = "Wheeling";
		final String state = "IL";
		final String zip = "66617";
		final String facilityId = "1";
		final String facilityName = "East Building";
		
		Address facilityAddress = new Address();
		facilityAddress.setStreet(street);
		facilityAddress.setUnit(unit);
		facilityAddress.setCity(city);
		facilityAddress.setState(state);
		facilityAddress.setZip(zip);
        
        Facility facility = new Facility(facilityId);
        
        facility.setHandler(FacilityDAO.getInstance(), 
							new FacilityUseHandler(),
							new FacilityInspectHandler(),
							new FacilityMaintainHandler());
        facility.getFacilityDetail().setFacilityAddress(facilityAddress);
        facility.getFacilityDetail().setFacilityName(facilityName);
        
        result &= facility.getFacilityId().equals(facilityId);
        result &= facility.getFacilityDetail().getFacilityName().equals(facilityName);
        result &= facility.getFacilityDetail().getFacilityAddress().getStreet().equals(street);
        result &= facility.getFacilityDetail().getFacilityAddress().getUnit().equals(unit);
        result &= facility.getFacilityDetail().getFacilityAddress().getCity().equals(city);
        result &= facility.getFacilityDetail().getFacilityAddress().getState().equals(state);
        result &= facility.getFacilityDetail().getFacilityAddress().getZip().equals(zip);
        
		System.out.println("Facility UT: " + (result ? "PASS" : "FAIL"));
	}
}
