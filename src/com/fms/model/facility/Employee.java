package com.fms.model.facility;

public class Employee implements ResourceObserver {

	private String employeeId;
	private String employeeName;
	
	public Employee(String employeeId, String employeeName) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}

	public void updateStatus(Resource resource) {
		System.out.println(employeeId + 
						   " received notification from facility " + 
						   resource.getResourceId() + " ==> " + 
						   resource.getResourceStatus());
	}
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
