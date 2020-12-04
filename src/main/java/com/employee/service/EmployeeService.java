package com.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.entity.Employee;
import com.employee.dao.repository.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository repository;
	
	@Override
	public Iterable<Employee> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Employee createOrUpdateEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {
		repository.deleteById(id);
	}

}
