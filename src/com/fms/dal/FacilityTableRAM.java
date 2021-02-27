package com.fms.dal;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityPersistency;

public class FacilityTableRAM implements FacilityPersistency {

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
	
	public List<Facility> listFacilites() {
		
		List<Facility> facilityList = new ArrayList<Facility>(facilities.values());
		
		return facilityList;
	}
	
	public Facility getFacility(String facilityId) {
	    return facilities.get(facilityId);
	}
	
	public void removeFacility(String facilityId) {
		facilities.remove(facilityId);
    }
	
	public Facility addFacility() {
		
		String facilityId = Integer.toString(index++);
		Facility facility = new Facility(facilityId);
		
		facilities.put(facilityId, facility);
		
		return facility;
    }
		
	public boolean changeFacility(Facility facility) {
		
		boolean result = false;
		
		if (facilities.containsKey(facility.getFacilityId()) == true) {
			facilities.put(facility.getFacilityId(), facility);
			result = true;
		}
		
		return result;
    }
}
