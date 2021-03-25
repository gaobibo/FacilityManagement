package com.fms.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityInspectRecord;
import com.fms.model.facility.FacilityMaintainRecord;
import com.fms.model.facility.FacilityRecord;
import com.fms.model.facility.FacilityUseRecord;
import com.fms.model.service.FacilityService;

public class FacilityIntegrationTest {
	
	private static ApplicationContext context;
	
	public static void main (String args[]) throws Exception {
		
		context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		
		integrationBtwFacilityServiceAndFacility();
		
		integrationBtwFacilityAndFacilityUseHandler();
		
		integrationBtwFacilityAndFacilityInspectHandler();
		
		integrationBtwFacilityAndFacilityMaintainHandler();
		
		((AbstractApplicationContext)context).close();
	}
	
	private static void integrationBtwFacilityServiceAndFacility() {

		boolean result = true;
		
		FacilityService facilityService = (FacilityService)context.getBean("facilityServiceSingleton");
		
		Facility facility1 = facilityService.addNewFacility();
		Facility facility2 = facilityService.addNewFacility();
		Facility facility3 = facilityService.addNewFacility();
		Facility facility4 = facilityService.addNewFacility();
		Facility facility5 = facilityService.addNewFacility();
		
		System.out.println("\t" + facility1.getFacilityId() + " -- " + 
								  facility2.getFacilityId() + " -- " + 
								  facility3.getFacilityId() + " -- " + 
								  facility4.getFacilityId() + " -- " + 
								  facility5.getFacilityId());
		
		List<Facility> facilityList1 = facilityService.listAllFacilities();
		result &= (facilityList1.size() == 5);
		
		facility2.removeFacility();
		facility4.removeFacility();
		
		List<Facility> facilityList2 = facilityService.listAllFacilities();
		result &= (facilityList2.size() == 3);
		
		System.out.println("FacilityService IT: " + (result ? "PASS" : "FAIL"));
	}
	
	private static void integrationBtwFacilityAndFacilityUseHandler() {
		
		boolean result = true;
		
		final String facilityId = "1";
		final String employeeId = "Alice";

        Facility facility = (Facility)context.getBean("facility");
        
        facility.setFacilityId(facilityId);
        facility.setFacilityStatus(FacilityRecord.STATUS_READY);
        
		Date startDate1 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date startDate2 = new Date();
		
		facility.assignFacilityToUse(employeeId);
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		facility.vacateFacility();
		
		Date endDate2 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date endDate1 = new Date();
		
		Date startDate3 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date endDate3 = new Date();
		
		List<FacilityUseRecord> recordList = facility.listActualUsage();
		
		for (FacilityUseRecord record : recordList) {
			System.out.println("\t" + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getAssignDate() + " -- " + 
							   		  record.getVacateDate());
		}
		
		double usageRate1 = facility.calcUsageRate(startDate1, endDate1);
		boolean isInUse1 = facility.isInUseDuringInterval(startDate1, endDate1);

		double usageRate2 = facility.calcUsageRate(startDate2, endDate2);
		boolean isInUse2 = facility.isInUseDuringInterval(startDate2, endDate2);
		
		double usageRate3 = facility.calcUsageRate(startDate3, endDate3);
		boolean isInUse3 = facility.isInUseDuringInterval(startDate3, endDate3);
		
		System.out.println("\t" + usageRate1 + " -- " + isInUse1);
		System.out.println("\t" + usageRate2 + " -- " + isInUse2);
		System.out.println("\t" + usageRate3 + " -- " + isInUse3);
		
		result &= (isInUse1 == true);
		result &= (isInUse2 == true);
		result &= (isInUse3 == false);
		
		System.out.println("FacilityUseHandler IT: " + (result ? "PASS" : "FAIL"));		
	}
	
	private static void integrationBtwFacilityAndFacilityInspectHandler() {
		
		boolean result = true;
		
		final String facilityId = "1";
		final String employeeId = "Alice";
		
        Facility facility = (Facility)context.getBean("facility");
        
        facility.setFacilityId(facilityId);

        facility.inspectFacility(employeeId);
        facility.inspectFacility(employeeId);
		
		List<FacilityInspectRecord> recordList = facility.listInspections();
		
		for (FacilityInspectRecord record : recordList) {
			System.out.println("\t" + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getEmployeeId() + " -- " + 
							   		  record.getInspectDate());
		}
		
		result &= (recordList.size() == 2);
		
		System.out.println("FacilityInspectHandler IT: " + (result ? "PASS" : "FAIL"));		
	}
	
	private static void integrationBtwFacilityAndFacilityMaintainHandler() {
		
		boolean result = true;
		
		final String facilityId = "1";
		final String employeeId = "Alice";

        Facility facility = (Facility)context.getBean("facility");
        
        facility.setFacilityId(facilityId);
		
		Date submittedDate = parseDate("2020-2-1");
		Date scheduledDate = parseDate("2020-2-2");
		Date completedDate = parseDate("2020-2-3");

		FacilityMaintainRecord record1 = facility.makeFacilityMaintRequest(employeeId, submittedDate, FacilityMaintainRecord.MaintainType.GENERAL);
		FacilityMaintainRecord record2 = facility.makeFacilityMaintRequest(employeeId, submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);
		FacilityMaintainRecord record3 = facility.makeFacilityMaintRequest(employeeId, submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);
		
		System.out.println("\t" + record1.getRecordId() + " -- " + 
								  record2.getRecordId() + " -- " + 
								  record3.getRecordId());
		
		facility.scheduleMaintenance(record2.getRecordId(), scheduledDate);
		facility.completeMaintenance(record2.getRecordId(), completedDate, 100);
		
		List<FacilityMaintainRecord> recordList1 = facility.listMaintenance();
		List<FacilityMaintainRecord> recordList2 = facility.listMaintRequests();
		List<FacilityMaintainRecord> recordList3 = facility.listFacilityProblems();
		
		System.out.println("\t" + recordList1.size() + " -- " + 
								  recordList2.size() + " -- " + 
								  recordList3.size());
		
		result &= (recordList1.size() == 3);
		result &= (recordList2.size() == 2);
		result &= (recordList3.size() == 2);
		
		double maintainCost = facility.calcMaintenaceCostForFacility();
		double problemRate = facility.calcProblemRateForFacility();
		double downTime = facility.calcDownTimeForFacility();
		
		System.out.println("\t" + maintainCost + " -- " + problemRate + " -- " + downTime);
		
		result &= (maintainCost == 100.0);
		result &= (problemRate == 2.0 / 3.0);
		result &= (downTime == 2.0);
		
		System.out.println("FacilityMaintainHandler IT: " + (result ? "PASS" : "FAIL"));		
	}
	
	private static Date parseDate(String date) {
	    try {
	        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	    } catch (ParseException e) {
	        return null;
	    }
	}
}
