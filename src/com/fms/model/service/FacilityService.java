package com.fms.model.service;

import java.util.ArrayList;
import java.util.List;

import com.fms.dal.FacilityInspectTableRAM;
import com.fms.dal.FacilityTableRAM;
import com.fms.dal.FacilityUseTableRAM;
import com.fms.model.facility.Facility;
import com.fms.model.handler.FacilityInspectHandler;
import com.fms.model.handler.FacilityMaintainHandler;
import com.fms.model.handler.FacilityUseHandler;

public class FacilityService {

	private FacilityTableRAM facilityTableRAM = FacilityTableRAM.getInstance();

	// List all the facilities
	public List<Facility> listFacilities() {
		
		List<Facility> facilityList = new ArrayList<Facility>();
		
		try {
			facilityList = facilityTableRAM.listFacilites();
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facilityList;
	}
	
	// Add a facility
	public Facility addNewFacility() {
		
		Facility facility = null;
		
		try {
			facility = facilityTableRAM.addFacility();
			
			facility.setHandler(new FacilityUseHandler(FacilityUseTableRAM.getInstance()),
								new FacilityInspectHandler(FacilityInspectTableRAM.getInstance()),
								new FacilityMaintainHandler(),
								FacilityTableRAM.getInstance());
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facility;
	}
}