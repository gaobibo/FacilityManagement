package com.fms.model.service;

import java.util.ArrayList;
import java.util.List;

import com.fms.dal.EmployeeDAO;
import com.fms.model.employee.Employee;

public class EmployeeService {

	private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
	
	// List all the employees
	public List<Employee> listEmployees() {
		
		List<Employee> employeeList = new ArrayList<Employee>();
		
		try {
			employeeList = employeeDAO.listEmployees();
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return employeeList;
	}
	
	// Get employee by ID
	public Employee getEmployee(String employeeId) {
		
		Employee employee = null;
		
		try {
			employee = employeeDAO.getEmployee(employeeId);
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
    	return employee;
	}
	
	// Insert an employee
	public void addEmployee(Employee employee) {
		
		try {
			employeeDAO.addEmployee(employee);
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
	}
	
	// Remove an employee
	public void removeEmployee(String employeeId) {
		
		try {
			employeeDAO.removeEmployee(employeeId);
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
	}
	
	// Change an employee
	public boolean changeEmployee(Employee employee) {

		boolean result = false;
		
		try {
			result = employeeDAO.changeEmployee(employee);
	    } catch (Exception se) {
	      System.err.println(se.getMessage());
	    }
		
		return result;
	}
}