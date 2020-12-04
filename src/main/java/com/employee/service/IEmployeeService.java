package com.employee.service;

import java.util.Optional;

import com.employee.dao.entity.Employee;

public interface IEmployeeService {
	public Iterable<Employee> getAllEmployees();
	public Optional<Employee> getEmployeeById(Long id);
	public Employee createOrUpdateEmployee(Employee employee);
	public void deleteEmployee(Long id);
}
