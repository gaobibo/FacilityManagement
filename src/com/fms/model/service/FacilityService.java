package com.fms.model.service;

import java.util.ArrayList;
import java.util.List;

import com.fms.dal.FacilityDAO;
import com.fms.model.facility.Facility;
import com.fms.model.handler.FacilityInspectHandler;
import com.fms.model.handler.FacilityMaintainHandler;
import com.fms.model.handler.FacilityUseHandler;

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
	
	// Insert a facility
	public Facility addNewFacility() {
		
		Facility facility = null;
		
		try {
			facility = facilityDAO.addFacility();
			
			facility.setHandler(facilityDAO,
								new FacilityUseHandler(),
								new FacilityInspectHandler(),
								new FacilityMaintainHandler());
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facility;
	}
}