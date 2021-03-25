package com.fms.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityDetail;
import com.fms.model.facility.FacilityInspectRecord;
import com.fms.model.facility.FacilityMaintainRecord;
import com.fms.model.facility.FacilityRecord;
import com.fms.model.facility.FacilityUseRecord;
import com.fms.model.handler.FacilityInspectHandler;
import com.fms.model.handler.FacilityMaintainHandler;
import com.fms.model.handler.FacilityUseHandler;
import com.fms.model.service.FacilityService;

public class FacilityUnitTest {
	
	private static ApplicationContext context;
	
	public static void main (String args[]) throws Exception {
		
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		
		unitTestFacility();
		
		unitTestFacilityService();

		unitTestFacilityUseHandler();
		
		unitTestFacilityInspectHandler();
		
		unitTestFacilityMaintainHandler();
		
		((AbstractApplicationContext)context).close();
	}
	
	private static void unitTestFacility() {
		
		boolean result = true;
		
		final String facilityId = "1";
		final String facilityStatus = FacilityRecord.STATUS_READY;
		final String facilityName = "East Building";
		final String facilityAddress = "800 East Madison St, Wheeling, IL 66617";
		final int facilityCapacity = 100;
		
		FacilityDetail facilityDetail = (FacilityDetail)context.getBean("facilityDetail");
		facilityDetail.setFacilityName(facilityName);
		facilityDetail.setFacilityAddress(facilityAddress);
		facilityDetail.setFacilityCapacity(facilityCapacity);
        
        Facility facility = (Facility)context.getBean("facility");
        
        facility.setFacilityId(facilityId);
        facility.setFacilityStatus(facilityStatus);
        facility.setFacilityDetail(facilityDetail);
        
        result &= (facility.getFacilityId().equals(facilityId));
        result &= (facility.getFacilityStatus().equals(facilityStatus));
        result &= (facility.getFacilityDetail().getFacilityName().equals(facilityName));
        result &= (facility.getFacilityDetail().getFacilityAddress().equals(facilityAddress));
        result &= (facility.requestAvailableCapacity() == facilityCapacity);
        
        facility.removeFacility();
        result &= (facility.getFacilityStatus() == FacilityRecord.STATUS_REMOVED);
        
		System.out.println("Facility UT: " + (result ? "PASS" : "FAIL"));
	}
	
	private static void unitTestFacilityService() {
		
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
		
		List<Facility> facilityList = facilityService.listAllFacilities();
		result &= (facilityList.size() == 5);

		System.out.println("FacilityService UT: " + (result ? "PASS" : "FAIL"));
	}
	
	private static void unitTestFacilityUseHandler() {
	
		boolean result = true;
		
		String facilityId1 = "1";
		String facilityId2 = "2";
		String employeeId1 = "Alice";
		String employeeId2 = "Bob";
		
		FacilityUseHandler facilityUseHandler = (FacilityUseHandler)context.getBean("facilityUseSingleton");
		
		Date startDate1 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date startDate2 = new Date();
		
		facilityUseHandler.assignFacilityToUse(facilityId1, employeeId1);
		facilityUseHandler.assignFacilityToUse(facilityId2, employeeId2);
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		facilityUseHandler.vacateFacility(facilityId1);
		facilityUseHandler.vacateFacility(facilityId2);
		
		Date endDate2 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date endDate1 = new Date();
		
		Date startDate3 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date endDate3 = new Date();
		
		List<FacilityUseRecord> recordList1 = facilityUseHandler.listActualUsage(facilityId1);
		List<FacilityUseRecord> recordList2 = facilityUseHandler.listActualUsage(facilityId2);
		
		for (FacilityUseRecord record : recordList1) {
			System.out.println("\t" + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getAssignDate() + " -- " + 
							   		  record.getVacateDate());
		}
		
		for (FacilityUseRecord record : recordList2) {
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
	
	private static void unitTestFacilityInspectHandler() {
		
		boolean result = true;
		
		String facilityId1 = "1";
		String facilityId2 = "2";
		String employeeId1 = "Alice";
		String employeeId2 = "Bob";
		
		FacilityInspectHandler facilityInspectHandler = (FacilityInspectHandler)context.getBean("facilityInspectSingleton");
		
		facilityInspectHandler.inspectFacility(facilityId1, employeeId1);
		facilityInspectHandler.inspectFacility(facilityId1, employeeId2);
		facilityInspectHandler.inspectFacility(facilityId2, employeeId2);
		
		List<FacilityInspectRecord> recordList1 = facilityInspectHandler.listInspections(facilityId1);
		List<FacilityInspectRecord> recordList2 = facilityInspectHandler.listInspections(facilityId2);
		
		for (FacilityInspectRecord record : recordList1) {
			System.out.println("\t" + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getEmployeeId() + " -- " + 
							   		  record.getInspectDate());
		}
		
		for (FacilityInspectRecord record : recordList2) {
			System.out.println("\t" + record.getRecordId() + " -- " + 
			   		  				  record.getFacilityId() + " -- " + 
			   		  				  record.getEmployeeId() + " -- " + 
			   		  				  record.getInspectDate());
		}
		
		result &= (recordList1.size() == 2);
		result &= (recordList2.size() == 1);
		
		System.out.println("FacilityInspectHandler UT: " + (result ? "PASS" : "FAIL"));
	}
	
	private static void unitTestFacilityMaintainHandler() {
		
		boolean result = true;
		
		String facilityId1 = "1";
		String facilityId2 = "2";
		String employeeId1 = "Alice";
		String employeeId2 = "Bob";
		String employeeId3 = "John";
		
		Date submittedDate = parseDate("2020-2-1");
		Date scheduledDate = parseDate("2020-2-2");
		Date completedDate = parseDate("2020-2-3");

		FacilityMaintainHandler facilityMaintainHandler = (FacilityMaintainHandler)context.getBean("facilityMaintainSingleton");
		
		FacilityMaintainRecord record1 = facilityMaintainHandler.makeFacilityMaintRequest(facilityId1, employeeId1, submittedDate, FacilityMaintainRecord.MaintainType.GENERAL);
		FacilityMaintainRecord record2 = facilityMaintainHandler.makeFacilityMaintRequest(facilityId1, employeeId2, submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);
		FacilityMaintainRecord record3 = facilityMaintainHandler.makeFacilityMaintRequest(facilityId1, employeeId3, submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);
		
		FacilityMaintainRecord record4 = facilityMaintainHandler.makeFacilityMaintRequest(facilityId2, employeeId1, submittedDate, FacilityMaintainRecord.MaintainType.GENERAL);
		FacilityMaintainRecord record5 = facilityMaintainHandler.makeFacilityMaintRequest(facilityId2, employeeId2, submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);
		FacilityMaintainRecord record6 = facilityMaintainHandler.makeFacilityMaintRequest(facilityId2, employeeId3, submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);

		System.out.println("\t" + record1.getRecordId() + " -- " + 
								  record2.getRecordId() + " -- " + 
								  record3.getRecordId() + " -- " +
								  record4.getRecordId() + " -- " +
								  record5.getRecordId() + " -- " +
								  record6.getRecordId());
		
		facilityMaintainHandler.scheduleMaintenance(facilityId1, record2.getRecordId(), scheduledDate);
		facilityMaintainHandler.completeMaintenance(facilityId1, record2.getRecordId(), completedDate, 100);
		facilityMaintainHandler.scheduleMaintenance(facilityId1, record4.getRecordId(), scheduledDate);
		facilityMaintainHandler.completeMaintenance(facilityId1, record4.getRecordId(), completedDate, 200);
		
		List<FacilityMaintainRecord> recordList1 = facilityMaintainHandler.listMaintenance(facilityId1);
		List<FacilityMaintainRecord> recordList2 = facilityMaintainHandler.listMaintRequests(facilityId1);
		List<FacilityMaintainRecord> recordList3 = facilityMaintainHandler.listFacilityProblems(facilityId1);
		
		List<FacilityMaintainRecord> recordList4 = facilityMaintainHandler.listMaintenance(facilityId2);
		List<FacilityMaintainRecord> recordList5 = facilityMaintainHandler.listMaintRequests(facilityId2);
		List<FacilityMaintainRecord> recordList6 = facilityMaintainHandler.listFacilityProblems(facilityId2);
		
		System.out.println("\t" + recordList1.size() + " -- " + 
								  recordList2.size() + " -- " + 
								  recordList3.size() + " -- " +
								  recordList4.size() + " -- " +
								  recordList5.size() + " -- " +
								  recordList6.size());
		
		result &= (recordList1.size() == 3);
		result &= (recordList2.size() == 2);
		result &= (recordList3.size() == 2);
		result &= (recordList4.size() == 3);
		result &= (recordList5.size() == 3);
		result &= (recordList6.size() == 2);
		
		double maintainCost = facilityMaintainHandler.calcMaintenaceCostForFacility(facilityId1);
		double problemRate = facilityMaintainHandler.calcProblemRateForFacility(facilityId1);
		double downTime = facilityMaintainHandler.calcDownTimeForFacility(facilityId1);
		
		System.out.println("\t" + maintainCost + " -- " + problemRate + " -- " + downTime);
		
		result &= (maintainCost == 100.0);
		result &= (problemRate == 2.0 / 3.0);
		result &= (downTime == 2.0);
		
		System.out.println("FacilityMaintainHandler UT: " + (result ? "PASS" : "FAIL"));
	}
	
	private static Date parseDate(String date) {
	    try {
	        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	    } catch (ParseException e) {
	        return null;
	    }
	}
}
