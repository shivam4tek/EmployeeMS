package com.employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
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
				.firstName("ShivamTest")
				.lastName("VishwakarmaTest")
				.phone("60173017646")
				.email("shivam.feb@gmail.com")
				.build();
		Employee createdEmp = repository.save(emp);
		assertThat(createdEmp.getId()).isGreaterThan(2);
		assertThat(createdEmp.getPhone()).isEqualTo("60173017646");
	}
}
