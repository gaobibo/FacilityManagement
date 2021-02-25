package com.fms.model.facility;

import java.util.List;

public interface FacilityPersistencyInterface {
	public List<Facility> listFacilites();
	public Facility getFacility(String facilityId);
	public void removeFacility(String facilityId);
	public void addFacility(Facility facility);
	public boolean changeFacility(Facility facility);
}
