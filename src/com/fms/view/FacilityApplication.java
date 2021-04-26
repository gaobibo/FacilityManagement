package com.fms.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.model.service.FacilityService;
import com.fms.model.facility.Employee;
import com.fms.model.facility.Facility;
import com.fms.model.facility.FacilityDetail;
import com.fms.model.facility.FacilityUseRecord;
import com.fms.model.facility.Resource;
import com.fms.model.facility.ResourceVisitor;
import com.fms.model.handler.ResourceTreeView;
import com.fms.model.facility.FacilityInspectRecord;
import com.fms.model.facility.FacilityMaintainRecord;

public class FacilityApplication {
	
	private static FacilityService facilityService;
	private static ResourceVisitor resourceVisitor;
	
	public static void main (String args[]) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        System.out.println("***************** Application Context instantiated! ******************");
        
        facilityService = (FacilityService)context.getBean("facilityServiceSingleton");
		
		Employee employeeAlice = new Employee("alice@fms.com", "Alice Brady");
		Employee employeePeter = new Employee("peter@fms.com", "Peter Cohen");
		Employee employeeChris = new Employee("chris@fms.com", "Chris Davis");
		
		final String facilityName = "East Room";
		final String facilityAddress = "800 East Madison St, Wheeling, IL 66617";
		final int facilityCapacity = 100;
		
		FacilityDetail facilityDetail = (FacilityDetail)context.getBean("facilityDetail");
		facilityDetail.setFacilityName(facilityName);
		facilityDetail.setFacilityAddress(facilityAddress);
		facilityDetail.setFacilityCapacity(facilityCapacity);
		
		Facility facility1 = facilityService.addNewFacility();
		Facility facility2 = facilityService.addHotelBedRoom();
		Facility facility3 = facilityService.addHotelMeetingRoom();
		Facility facility4 = facilityService.addNewFacility();
		Facility facility5 = facilityService.addNewFacility();

        facility1.setFacilityDetail(facilityDetail);
        facility2.setFacilityDetail(facilityDetail);
        facility3.setFacilityDetail(facilityDetail);
        facility4.setFacilityDetail(facilityDetail);
        facility5.setFacilityDetail(facilityDetail);

		for (Facility facility : facilityService.listAllFacilities()) {
			System.out.println("Facility: " + facility.getFacilityId() + " -- " + facility.getFacilityStatus());		
		}
		
		facility1.attachObserver(employeeAlice);
		facility1.attachObserver(employeePeter);
		facility1.attachObserver(employeeChris);
		facility2.attachObserver(employeeAlice);
		facility2.attachObserver(employeePeter);
		facility2.attachObserver(employeeChris);
		facility3.attachObserver(employeeAlice);
		facility3.attachObserver(employeePeter);
		facility3.attachObserver(employeeChris);
		facility4.attachObserver(employeeAlice);
		facility4.attachObserver(employeePeter);
		facility4.attachObserver(employeeChris);
		facility5.attachObserver(employeeAlice);
		facility5.attachObserver(employeePeter);
		facility5.attachObserver(employeeChris);

		facility4.removeFacility();
		facility5.removeFacility();

		for (Facility facility : facilityService.listAllFacilities()) {
			System.out.println("Facility: " + facility.getFacilityId() + " -- " + facility.getFacilityStatus());		
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Date startDate1 = new Date(); 
		try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
		Date startDate2 = new Date();
		
		facility1.assignFacilityToUse(employeeAlice.getEmployeeId());
		facility2.assignFacilityToUse(employeePeter.getEmployeeId());
		facility3.assignFacilityToUse(employeeChris.getEmployeeId());
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
		
        facility1.inspectFacility(employeeAlice.getEmployeeId());
        facility2.inspectFacility(employeePeter.getEmployeeId());
        facility3.inspectFacility(employeeChris.getEmployeeId());
		
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

		FacilityMaintainRecord record1 = facility1.makeFacilityMaintRequest(employeeAlice.getEmployeeId(), submittedDate, FacilityMaintainRecord.MaintainType.GENERAL);
		FacilityMaintainRecord record2 = facility1.makeFacilityMaintRequest(employeePeter.getEmployeeId(), submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);
		FacilityMaintainRecord record3 = facility1.makeFacilityMaintRequest(employeeChris.getEmployeeId(), submittedDate, FacilityMaintainRecord.MaintainType.PROBLEMATIC);
		
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
		
		List<? extends Resource> resourceList = facilityService.listAllFacilities();
		resourceVisitor = new ResourceTreeView();
		resourceVisitor.visitResourceList(resourceList);
		
		((AbstractApplicationContext)context).close();
	}
	
	private static Date parseDate(String date) {
	    try {
	        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	    } catch (ParseException e) {
	        return null;
	    }
	}
}
