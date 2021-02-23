package com.fms.dal;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.fms.model.facility.Facility;

public class FacilityDAO {

    // static variable
    private static FacilityDAO instance = null; 
  
    // private constructor
    private FacilityDAO() 
    { 
    } 
  
    // static method to create instance of FacilityDAO class 
    public static FacilityDAO getInstance() 
    { 
        if (instance == null) {
        	instance = new FacilityDAO();
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
	
	public void addFacility(Facility facility) {
		facility.setFacilityId(Integer.toString(index++));
		facilities.put(facility.getFacilityId(), facility);
    }
	
	public void removeFacility(String facilityId) {
		facilities.remove(facilityId);
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
