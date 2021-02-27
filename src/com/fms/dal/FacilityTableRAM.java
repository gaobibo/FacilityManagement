package com.fms.dal;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityPersistencyInterface;

public class FacilityTableRAM implements FacilityPersistencyInterface<Facility> {

    // static variable
    private static FacilityTableRAM instance = null; 
  
    // private constructor
    private FacilityTableRAM() 
    { 
    } 
  
    // static method to create instance of FacilityTableRAM class 
    public static FacilityTableRAM getInstance() 
    { 
        if (instance == null) {
        	instance = new FacilityTableRAM();
        }
  
        return instance; 
    }
	
	private Map<String, Facility> facilities = new HashMap<String, Facility>();
	private int index = 0;
	
	public List<Facility> listRecords() {
		
		List<Facility> facilityList = new ArrayList<Facility>(facilities.values());
		
		return facilityList;
	}
	
	public List<Facility> listRecordsByFacilityId(String facilityId) {
		
		List<Facility> facilityList = new ArrayList<Facility>();
		
		facilityList.add(facilities.get(facilityId));
		
		return facilityList;
	}
	
	public Facility getRecord(String facilityId) {
	    return facilities.get(facilityId);
	}
	
	public void removeRecord(String facilityId) {
		facilities.remove(facilityId);
    }
	
	public void addRecord(Facility facility) {
		facility.setFacilityId(Integer.toString(index++));
		facilities.put(facility.getFacilityId(), facility);
    }
		
	public boolean changeRecord(Facility facility) {
		
		boolean result = false;
		
		if (facilities.containsKey(facility.getFacilityId()) == true) {
			facilities.put(facility.getFacilityId(), facility);
			result = true;
		}
		
		return result;
    }
}
