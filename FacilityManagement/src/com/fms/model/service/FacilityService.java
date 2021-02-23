package com.fms.model.service;

import java.util.ArrayList;
import java.util.List;

import com.fms.dal.FacilityDAO;
import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityDetail;

public class FacilityService {

	private FacilityDAO facilityDAO = FacilityDAO.getInstance();

	// List all the facilities
	public List<Facility> listFacilities() {
		
		List<Facility> facilityList = new ArrayList<Facility>();
		
		try {
			facilityList = facilityDAO.listFacilites();
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facilityList;
	}
	
	// Get facility by ID
	public Facility getFacility(String facilityId) {
		
		Facility facility = null;
		
		try {
			facility = facilityDAO.getFacility(facilityId);
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facility;
	}
	
	// Insert a facility
	public void addFacility(Facility facility) {
		
		try {
			facilityDAO.addFacility(facility);
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
	}
	
	// Remove a facility
	public void removeFacility(String facilityId) {
		
		try {
			facilityDAO.removeFacility(facilityId);
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
	}
	
	// Change a facility
	public boolean changeFacility(Facility facility) {

		boolean result = false;
		
		try {
			result = facilityDAO.changeFacility(facility);
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return result;
	}

	// Change a facility detail
	public boolean changeFacilityDetail(String facilityId, FacilityDetail facilityDetail) {

		boolean result = false;
	    
	    try {
	    	Facility facility = facilityDAO.getFacility(facilityId);
	    	
		    if (facility != null) {
		    	facility.setFacilityDetail(facilityDetail);
		    	result = facilityDAO.changeFacility(facility);
		    }
	    } catch (Exception se) {
		      System.err.println(se.getMessage());
		}
	    
		return result;
	}
	
	// Get the available capacity of facility
	public int getAvailableCapacity(String facilityId) {
		
		int facilityCapacity = 0;
	    
	    try {
	    	Facility facility = facilityDAO.getFacility(facilityId);
	    	
		    if (facility != null) {
		    	facilityCapacity = facility.getFacilityCapacity();
		    }
	    } catch (Exception se) {
		      System.err.println(se.getMessage());
		}
	    
	    return facilityCapacity;
	}
}