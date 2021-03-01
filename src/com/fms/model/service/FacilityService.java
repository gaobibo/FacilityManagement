package com.fms.model.service;

import java.util.ArrayList;
import java.util.List;

import com.fms.dal.FacilityInspectTableRAM;
import com.fms.dal.FacilityMaintainTableRAM;
import com.fms.dal.FacilityTableRAM;
import com.fms.dal.FacilityUseTableRAM;
import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityPersistencyInterface;
import com.fms.model.facility.FacilityRecord;
import com.fms.model.handler.FacilityInspectHandler;
import com.fms.model.handler.FacilityMaintainHandler;
import com.fms.model.handler.FacilityUseHandler;

public class FacilityService {

	private FacilityPersistencyInterface<FacilityRecord> facilityPersistencyIfc;
	
	public FacilityService(FacilityPersistencyInterface<FacilityRecord> facilityPersistencyIfc) {
		this.facilityPersistencyIfc = facilityPersistencyIfc;
	}

	// List all the facilities
	public List<Facility> listAllFacilities() {
		
		Facility facility = null;
		List<Facility> facilityList = new ArrayList<Facility>();
		
		try {
			List<FacilityRecord> facilityRecords = facilityPersistencyIfc.listRecords();
			
			for (FacilityRecord record : facilityRecords) {
				
				facility = new Facility(new FacilityUseHandler(FacilityUseTableRAM.getInstance()),
											     new FacilityInspectHandler(FacilityInspectTableRAM.getInstance()),
											     new FacilityMaintainHandler(FacilityMaintainTableRAM.getInstance()),
											     FacilityTableRAM.getInstance());
				facility.retrieveFacilityRecord(record);
				
				facilityList.add(facility);
			}
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facilityList;
	}
	
	// Add a new facility
	public Facility addNewFacility() {
		
		Facility facility = null;
		
		try {
			FacilityRecord record = new FacilityRecord();
			facilityPersistencyIfc.addRecord(record);
			
			facility = new Facility(new FacilityUseHandler(FacilityUseTableRAM.getInstance()),
								    new FacilityInspectHandler(FacilityInspectTableRAM.getInstance()),
								    new FacilityMaintainHandler(FacilityMaintainTableRAM.getInstance()),
								    FacilityTableRAM.getInstance());
			facility.retrieveFacilityRecord(record);
			
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return facility;
	}	
}