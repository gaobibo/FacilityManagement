package com.fms.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.model.employee.Employee;

public class EmployeeDAO {

    // static variable
    private static EmployeeDAO instance = null; 
  
    // private constructor
    private EmployeeDAO() 
    { 
    } 
  
    // static method to create instance of EmployeeDAO class 
    public static EmployeeDAO getInstance() 
    { 
        if (instance == null) {
        	instance = new EmployeeDAO();
        }
  
        return instance; 
    }
	
	private Map<String, Employee> employees = new HashMap<String, Employee>();
	private int index = 0;
	
	public List<Employee> listEmployees() {
		
		List<Employee> employeeList = new ArrayList<Employee>(employees.values());
		
		return employeeList;
	}
	
	public Employee getEmployee(String employeeId) {
	    return employees.get(employeeId);
	  }
	
	public void addEmployee(Employee employee) {
		employee.setEmployeeId(Integer.toString(index++));
		employees.put(employee.getEmployeeId(), employee);
    }
	
	public void removeEmployee(String employeeId) {
		employees.remove(employeeId);
    }
	
	public boolean changeEmployee(Employee employee) {
		
		boolean result = false;
		
		if (employees.containsKey(employee.getEmployeeId()) == true) {
			employees.put(employee.getEmployeeId(), employee);
			result = true;
		}
		
		return result;
    }
}
