package com.fms.model.facility;

import java.util.List;

public interface FacilityPersistencyInterface {
	public List<Facility> listFacilites();
	public Facility getFacility(String facilityId);
	public void removeFacility(String facilityId);
	public Facility addFacility();
	public boolean changeFacility(Facility facility);
}
