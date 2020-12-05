package com.employee;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.employee.dao.entity.Employee;
import com.employee.dao.repository.IEmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeRepositoryTest {

	@Autowired
	private IEmployeeRepository repository;

	@Test
	public void testCreateEmployee() {
		Employee emp = Employee.builder()
				.firstName("Shivam-Test")
				.lastName("Vishwakarma-Test")
				.phone("60173017646")
				.email("shivam.feb@gmail.com")
				.id(1000002L)
				.build();
		Employee createdEmp = repository.save(emp);
		assertThat(createdEmp.getId()).isGreaterThan(2);
		assertThat(createdEmp.getPhone()).isEqualTo("60173017646");
	}


	@Test 
	public void testGetAllEmployees() {
		Iterable<Employee> employees = repository.findAll();
		assertThat(((Collection<Employee>)employees).size()).isGreaterThan(0);
	}
	
	@Test
	public void testGetEmployeeById() {
		Long id = 2L;
		Optional<Employee> emp = repository.findById(id);
		assertThat(emp.get().getFirstName()).isEqualTo("Hoston");
	}
	
	@Test
	public void testUpdateEmployee() {
		Long id = 2L;
		Employee emp = repository.findById(id).get();
		emp.setPhone("0000");
		emp.setId(id);
		Employee createdEmp = repository.save(emp);
		assertThat(createdEmp.getPhone()).isEqualTo("0000");
	}
	
	@Test
	public void testDeleteEmployee() {
		Long id = 2L;
		repository.deleteById(id);
		Optional<Employee> emp = repository.findById(id);
		assertThat(emp).isEmpty();
	}

}
