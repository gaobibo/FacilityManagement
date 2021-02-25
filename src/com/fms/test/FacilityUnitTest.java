package com.fms.test;

import com.fms.dal.FacilityDAO;
import com.fms.model.common.Address;
import com.fms.model.employee.Employee;
import com.fms.model.facility.Facility;
import com.fms.model.service.FacilityUseHandler;

public class FacilityUnitTest {
	
	public static void main (String args[]) throws Exception {
		
		unitTestEmployee();
		unitTestFacility();

	}
	
	private static void unitTestEmployee() {
		
		boolean result = true;
		final String street = "500 West Madison St.";
		final String unit = "Suite 1000";
		final String city = "Chicago";
		final String state = "IL";
		final String zip = "66610";
		final String id = "1";
		final String fname = "Alice";
		final String lname = "Ciss";
		
		Address homeAddress = new Address();
        homeAddress.setStreet(street);
        homeAddress.setUnit(unit);
        homeAddress.setCity(city);
        homeAddress.setState(state);
        homeAddress.setZip(zip);
        
        Employee employee = new Employee();
        employee.setEmployeeId(id);
        employee.setFirstName(fname);
        employee.setLastName(lname);
        employee.setHomeAddress(homeAddress);
        
        result &= employee.getEmployeeId().equals(id);
        result &= employee.getFirstName().equals(fname);
        result &= employee.getLastName().equals(lname);
        result &= employee.getHomeAddress().getStreet().equals(street);
        result &= employee.getHomeAddress().getUnit().equals(unit);
        result &= employee.getHomeAddress().getCity().equals(city);
        result &= employee.getHomeAddress().getState().equals(state);
        result &= employee.getHomeAddress().getZip().equals(zip);
        
		System.out.println("Employee UT: " + (result ? "PASS" : "FAIL"));
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
        
        Facility facility = new Facility(FacilityDAO.getInstance(), new FacilityUseHandler());
        facility.getFacilityGeneral().setFacilityAddress(facilityAddress);
        facility.getFacilityGeneral().setFacilityId(facilityId);;
        facility.getFacilityGeneral().setFacilityName(facilityName);
        
        result &= facility.getFacilityGeneral().getFacilityId().equals(facilityId);
        result &= facility.getFacilityGeneral().getFacilityName().equals(facilityName);
        result &= facility.getFacilityGeneral().getFacilityAddress().getStreet().equals(street);
        result &= facility.getFacilityGeneral().getFacilityAddress().getUnit().equals(unit);
        result &= facility.getFacilityGeneral().getFacilityAddress().getCity().equals(city);
        result &= facility.getFacilityGeneral().getFacilityAddress().getState().equals(state);
        result &= facility.getFacilityGeneral().getFacilityAddress().getZip().equals(zip);
        
		System.out.println("Facility UT: " + (result ? "PASS" : "FAIL"));
	}
}
