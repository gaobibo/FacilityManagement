package com.fms.test;

import java.util.Date;
import java.util.List;

import com.fms.dal.FacilityDAO;
import com.fms.model.facility.Address;
import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityDetail;
import com.fms.model.handler.FacilityInspectHandler;
import com.fms.model.handler.FacilityMaintainHandler;
import com.fms.model.handler.FacilityUseHandler;
import com.fms.model.handler.UseRecord;
import com.fms.model.service.FacilityService;

public class FacilityUnitTest {
	
	public static void main (String args[]) throws Exception {
		
		unitTestFacility();
		
		unitTestFacilityService();

		unitTestFacilityUseHandler();
	}
	
	private static void unitTestFacility() {
		
		boolean result = true;
		
		final String facilityId = "1";
		final String facilityStatus = Facility.STATUS_READY;
		final String facilityName = "East Building";
		final String street = "800 East Madison St.";
		final String unit = "Suite 2000";
		final String city = "Wheeling";
		final String state = "IL";
		final String zip = "66617";
		final int facilityCapacity = 100;
		
		Address facilityAddress = new Address();
		facilityAddress.setStreet(street);
		facilityAddress.setUnit(unit);
		facilityAddress.setCity(city);
		facilityAddress.setState(state);
		facilityAddress.setZip(zip);
		
		FacilityDetail facilityDetail = new FacilityDetail();
		facilityDetail.setFacilityName(facilityName);
		facilityDetail.setFacilityAddress(facilityAddress);
		facilityDetail.setFacilityCapacity(facilityCapacity);
        
        Facility facility = new Facility(facilityId);
        
        facility.setHandler(new FacilityUseHandler(),
							new FacilityInspectHandler(),
							new FacilityMaintainHandler(),
							FacilityDAO.getInstance());
        
        facility.addFacilityDetail(facilityDetail);
        
        result &= (facility.getFacilityId().equals(facilityId));
        result &= (facility.getFacilityStatus().equals(facilityStatus));
        result &= (facility.getFacilityInformation().getFacilityName().equals(facilityName));
        result &= (facility.getFacilityInformation().getFacilityAddress().getStreet().equals(street));
        result &= (facility.getFacilityInformation().getFacilityAddress().getUnit().equals(unit));
        result &= (facility.getFacilityInformation().getFacilityAddress().getCity().equals(city));
        result &= (facility.getFacilityInformation().getFacilityAddress().getState().equals(state));
        result &= (facility.getFacilityInformation().getFacilityAddress().getZip().equals(zip));
        result &= (facility.requestAvailableCapacity() == facilityCapacity);
        
        facility.removeFacility();
        result &= (facility.getFacilityStatus() == Facility.STATUS_REMOVED);
        
		System.out.println("Facility UT: " + (result ? "PASS" : "FAIL"));
	}
	
	private static void unitTestFacilityService() {
		
		boolean result = true;
		
		FacilityService facilityService = new FacilityService();
		
		Facility facility1 = facilityService.addNewFacility();
		Facility facility2 = facilityService.addNewFacility();
		Facility facility3 = facilityService.addNewFacility();
		Facility facility4 = facilityService.addNewFacility();
		Facility facility5 = facilityService.addNewFacility();
		
		facility2.removeFacility();
		facility4.removeFacility();
		
		List<Facility> facilityList = facilityService.listFacilities();
		
		result &= (facilityList.size() == 3);

		System.out.println("FacilityService UT: " + (result ? "PASS" : "FAIL"));
	}
	
	private static void unitTestFacilityUseHandler() {
	
		boolean result = true;
		
		String facilityId1 = "1";
		String facilityId2 = "2";
		
		FacilityUseHandler facilityUseHandler = new FacilityUseHandler();
		
		Date startDate1 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date startDate2 = new Date();
		
		facilityUseHandler.assignFacilityToUse(facilityId1);
		facilityUseHandler.assignFacilityToUse(facilityId2);
		
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		
		facilityUseHandler.vacateFacility(facilityId1);
		facilityUseHandler.vacateFacility(facilityId2);
		
		Date endDate2 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date endDate1 = new Date();
		
		Date startDate3 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date endDate3 = new Date();
		
		List<UseRecord> recordList1 = facilityUseHandler.listActualUsage(facilityId1);
		List<UseRecord> recordList2 = facilityUseHandler.listActualUsage(facilityId2);
		
		for (UseRecord record : recordList1) {
			System.out.println("\t" + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getAssignDate() + " -- " + 
							   		  record.getVacateDate());
		}
		
		for (UseRecord record : recordList2) {
			System.out.println("\t" + record.getRecordId() + " -- " + 
			   		  				  record.getFacilityId() + " -- " + 
			   		  				  record.getAssignDate() + " -- " + 
			   		  				  record.getVacateDate());
		}
		
		double usageRate1 = facilityUseHandler.calcUsageRate(facilityId1, startDate1, endDate1);
		boolean isInUse1 = facilityUseHandler.isInUseDuringInterval(facilityId1, startDate1, endDate1);

		double usageRate2 = facilityUseHandler.calcUsageRate(facilityId1, startDate2, endDate2);
		boolean isInUse2 = facilityUseHandler.isInUseDuringInterval(facilityId1, startDate2, endDate2);
		
		double usageRate3 = facilityUseHandler.calcUsageRate(facilityId1, startDate3, endDate3);
		boolean isInUse3 = facilityUseHandler.isInUseDuringInterval(facilityId1, startDate3, endDate3);
		
		System.out.println("\t" + usageRate1 + " -- " + isInUse1);
		System.out.println("\t" + usageRate2 + " -- " + isInUse2);
		System.out.println("\t" + usageRate3 + " -- " + isInUse3);
		
		result &= (isInUse1 == true);
		result &= (isInUse2 == true);
		result &= (isInUse3 == false);
		
		System.out.println("FacilityUseHandler UT: " + (result ? "PASS" : "FAIL"));
	}
}
