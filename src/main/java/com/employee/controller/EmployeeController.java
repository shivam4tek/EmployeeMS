package com.employee.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.employee.dao.entity.Employee;
import com.employee.service.IEmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
	
	@Autowired
	IEmployeeService service;
	
	@GetMapping(path = "/all")
	private Iterable<Employee> getAllEmployees() {
		return service.getAllEmployees();
	}
	
	@GetMapping(path = "/{id}")
	private ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Optional<Employee> employee = service.getEmployeeById(id);
		if (!employee.isPresent()) {
			log.info("Employee with id " + id + " does not exists");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info("Found Employee:: " + employee);
		return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = service.createOrUpdateEmployee(employee);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedEmployee.getId())
					.toUri();
		log.info("Employee " + savedEmployee + " created. Reference URI:: " + uri);
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/{id}")
	private ResponseEntity<HttpStatus> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		Optional<Employee> existingEmployee = service.getEmployeeById(id);
		if (!existingEmployee.isPresent()) {
			log.info("Employee with id " + id + " does not exists");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		employee.setId(id);
		service.createOrUpdateEmployee(employee);
		log.info("Updated Employee:: " + employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(path = "/{id}")
	private ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
		Optional<Employee> existingEmployee = service.getEmployeeById(id);
		if (!existingEmployee.isPresent()) {
			log.info("Employee with id " + id + " does not exists");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		service.deleteEmployee(id);
		log.info("Found Employee:: " + existingEmployee.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
