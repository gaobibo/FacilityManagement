package com.fms.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fms.model.service.FacilityService;
import com.fms.dal.FacilityTableRAM;
import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityDetail;
import com.fms.model.facility.FacilityUseRecord;
import com.fms.model.facility.FacilityInspectRecord;
import com.fms.model.facility.FacilityMaintainRecord;

public class FacilityApplication {
	
	private static FacilityService facilityService = new FacilityService(FacilityTableRAM.getInstance());
	
	public static void main (String args[]) throws Exception {

		final String employeeAlice = "alice@fms.com";
		final String employeePeter = "peter@fms.com";
		final String employeeChris = "chris@fms.com";
		
		final String facilityName = "East Building";
		final String facilityAddress = "800 East Madison St, Wheeling, IL 66617";
		final int facilityCapacity = 100;
		
		FacilityDetail facilityDetail = new FacilityDetail();
		facilityDetail.setFacilityName(facilityName);
		facilityDetail.setFacilityAddress(facilityAddress);
		facilityDetail.setFacilityCapacity(facilityCapacity);
		
		Facility facility1 = facilityService.addNewFacility();
		Facility facility2 = facilityService.addNewFacility();
		Facility facility3 = facilityService.addNewFacility();
		Facility facility4 = facilityService.addNewFacility();
		Facility facility5 = facilityService.addNewFacility();
		
        facility1.addFacilityDetail(facilityDetail);
        facility2.addFacilityDetail(facilityDetail);
        facility3.addFacilityDetail(facilityDetail);
        facility4.addFacilityDetail(facilityDetail);
        facility5.addFacilityDetail(facilityDetail);
		
		facility4.removeFacility();
		facility5.removeFacility();
		
		List<Facility> facilityList = facilityService.listAllFacilities();

		for (Facility facility : facilityList) {
			System.out.println("Facility: " + facility.getFacilityId() + " -- " + 
									  facility.getFacilityStatus());		
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Date startDate1 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date startDate2 = new Date();
		
		facility1.assignFacilityToUse(employeeAlice);
		facility2.assignFacilityToUse(employeePeter);
		facility3.assignFacilityToUse(employeeChris);
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		facility1.vacateFacility();
		facility2.vacateFacility();
		facility3.vacateFacility();
		
		Date endDate2 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date endDate1 = new Date();
		
		Date startDate3 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date endDate3 = new Date();
		
		List<FacilityUseRecord> useRecordList1 = facility1.listActualUsage();
		List<FacilityUseRecord> useRecordList2 = facility2.listActualUsage();
		List<FacilityUseRecord> useRecordList3 = facility3.listActualUsage();
		
		for (FacilityUseRecord record : useRecordList1) {
			System.out.println("Facility1 UseRecord: " + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getAssignDate() + " -- " + 
							   		  record.getVacateDate());
		}
		
		for (FacilityUseRecord record : useRecordList2) {
			System.out.println("Facility2 UseRecord: " + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getAssignDate() + " -- " + 
							   		  record.getVacateDate());
		}
		
		for (FacilityUseRecord record : useRecordList3) {
			System.out.println("Facility3 UseRecord: " + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getAssignDate() + " -- " + 
							   		  record.getVacateDate());
		}
		
		double usageRate1 = facility1.calcUsageRate(startDate1, endDate1);
		boolean isInUse1 = facility1.isInUseDuringInterval(startDate1, endDate1);

		double usageRate2 = facility2.calcUsageRate(startDate2, endDate2);
		boolean isInUse2 = facility2.isInUseDuringInterval(startDate2, endDate2);
		
		double usageRate3 = facility3.calcUsageRate(startDate3, endDate3);
		boolean isInUse3 = facility3.isInUseDuringInterval(startDate3, endDate3);
		
		System.out.println("Facility1 UsageRate: " + usageRate1 + " -- IsInUse: " + isInUse1);
		System.out.println("Facility2 UsageRate: " + usageRate2 + " -- IsInUse: " + isInUse2);
		System.out.println("Facility3 UsageRate: " + usageRate3 + " -- IsInUse: " + isInUse3);
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
        facility1.inspectFacility(employeeAlice);
        facility2.inspectFacility(employeePeter);
        facility3.inspectFacility(employeeChris);
		
		List<FacilityInspectRecord> inpsectRecordList1 = facility1.listInspections();
		List<FacilityInspectRecord> inpsectRecordList2 = facility2.listInspections();
		List<FacilityInspectRecord> inpsectRecordList3 = facility3.listInspections();
		
		for (FacilityInspectRecord record : inpsectRecordList1) {
			System.out.println("Facility1 InspectRecord: " + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getEmployeeId() + " -- " + 
							   		  record.getInspectDate());
		}
		
		for (FacilityInspectRecord record : inpsectRecordList2) {
			System.out.println("Facility2 InspectRecord: " + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getEmployeeId() + " -- " + 
							   		  record.getInspectDate());
		}
		
		for (FacilityInspectRecord record : inpsectRecordList3) {
			System.out.println("Facility3 InspectRecord: " + record.getRecordId() + " -- " + 
							   		  record.getFacilityId() + " -- " + 
							   		  record.getEmployeeId() + " -- " + 
							   		  record.getInspectDate());
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Date submittedDate = parseDate("2020-2-1");
		Date scheduledDate = parseDate("2020-2-2");
		Date completedDate = parseDate("2020-2-3");

		FacilityMaintainRecord record1 = facility1.makeFacilityMaintRequest(employeeAlice, submittedDate, FacilityMaintainRecord.MaintainType.GENERAL);
		FacilityMaintainRecord record2 = facility1.makeFacilityMaintRequest(employeePeter, submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);
		FacilityMaintainRecord record3 = facility1.makeFacilityMaintRequest(employeeChris, submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);
		
		System.out.println("Facility1 MaintainRecord: " + record1.getRecordId() + " -- " + 
								  record1.getFacilityId() + " -- " + 
								  record1.getEmployeeId() + " -- " + 
								  record1.getSubmittedDate());
		
		System.out.println("Facility1 MaintainRecord: " + record2.getRecordId() + " -- " + 
				  record2.getFacilityId() + " -- " + 
				  record2.getEmployeeId() + " -- " + 
				  record2.getSubmittedDate());
		
		System.out.println("Facility1 MaintainRecord: " + record3.getRecordId() + " -- " + 
				  record3.getFacilityId() + " -- " + 
				  record3.getEmployeeId() + " -- " + 
				  record3.getSubmittedDate());
		
		facility1.scheduleMaintenance(record2.getRecordId(), scheduledDate);
		facility1.completeMaintenance(record2.getRecordId(), completedDate, 100);
		
		List<FacilityMaintainRecord> recordList1 = facility1.listMaintenance();
		List<FacilityMaintainRecord> recordList2 = facility1.listMaintRequests();
		List<FacilityMaintainRecord> recordList3 = facility1.listFacilityProblems();
		
		System.out.println("Facility1 Number of Maintenance: " + recordList1.size());
		System.out.println("Facility1 Number of MaintRequests: " + recordList2.size());
		System.out.println("Facility1 Number of Problems: " + recordList3.size());
		
		double maintainCost = facility1.calcMaintenaceCostForFacility();
		double problemRate = facility1.calcProblemRateForFacility();
		double downTime = facility1.calcDownTimeForFacility();
		
		System.out.println("Facility1 MaintainCost: " + maintainCost);
		System.out.println("Facility1 ProblemRate: " + problemRate);
		System.out.println("Facility1 DownTime: " + downTime);	
	}
	
	private static Date parseDate(String date) {
	    try {
	        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	    } catch (ParseException e) {
	        return null;
	    }
	}
}
