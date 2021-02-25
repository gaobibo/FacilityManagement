package com.fms.dal;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityPersistencyInterface;

public class FacilityDAO implements FacilityPersistencyInterface {

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
	
	public void removeFacility(String facilityId) {
		facilities.remove(facilityId);
    }
	
	public void addFacility(Facility facility) {
		facility.getFacilityGeneral().setFacilityId(Integer.toString(index++));
		facilities.put(facility.getFacilityGeneral().getFacilityId(), facility);
    }
		
	public boolean changeFacility(Facility facility) {
		
		boolean result = false;
		
		if (facilities.containsKey(facility.getFacilityGeneral().getFacilityId()) == true) {
			facilities.put(facility.getFacilityGeneral().getFacilityId(), facility);
			result = true;
		}
		
		return result;
    }
}
