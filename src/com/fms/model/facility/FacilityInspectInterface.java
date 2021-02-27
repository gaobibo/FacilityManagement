package com.fms.model.facility;

import java.util.List;

public interface FacilityInspectInterface {
	public List<FacilityInspectRecord> listInspections(String facilityId);
	public boolean inspectFacility(String facilityId, String employeeId);
}
