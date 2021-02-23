package com.fms.test;

import com.fms.model.common.Address;
import com.fms.model.employee.Employee;
import com.fms.model.facility.Facility;

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
        
        Facility facility = new Facility();
        facility.setFacilityAddress(facilityAddress);
        facility.setFacilityId(facilityId);;
        facility.setFacilityName(facilityName);
        
        result &= facility.getFacilityId().equals(facilityId);
        result &= facility.getFacilityName().equals(facilityName);
        result &= facility.getFacilityAddress().getStreet().equals(street);
        result &= facility.getFacilityAddress().getUnit().equals(unit);
        result &= facility.getFacilityAddress().getCity().equals(city);
        result &= facility.getFacilityAddress().getState().equals(state);
        result &= facility.getFacilityAddress().getZip().equals(zip);
        
		System.out.println("Facility UT: " + (result ? "PASS" : "FAIL"));
	}
}
