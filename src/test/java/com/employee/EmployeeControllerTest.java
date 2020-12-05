package com.employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.employee.dao.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EmployeeControllerTest extends EmployeeMicroserviceApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    protected ObjectMapper objectMapper;
	
	Employee emp = Employee.builder()
			.firstName("updated_shivam")
			.lastName("updated_vishwakarma")
			.email("shivam.feb@gmail.com")
			.phone("60173017646")
			.build();
	
	String url = "/employee/2";

	@Test
	public void testGetEmployeeById() throws Exception {
		
		mockMvc.perform(get(url)).andExpect(status().isOk())
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$.firstName").value("Hoston"))
		.andExpect(jsonPath("$.lastName").value("lindey"))
		.andExpect(jsonPath("$.id").value("2"));
	}
	
	@Test
	public void testCreateEmployee() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(emp))).andExpect(status().isCreated())
				.andExpect(header().exists("Location"));

	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.put(url)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(emp))).andExpect(status().isNoContent());
		
		mockMvc.perform(get(url)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.firstName").value(emp.getFirstName()))
				.andExpect(jsonPath("$.lastName").value(emp.getLastName()))
				.andExpect(jsonPath("$.id").value("2"));

	}
	
	@Test
	@Rollback(true)
	public void testDeleteEmployee() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete(url)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(emp))).andExpect(status().isNoContent());

		mockMvc.perform(get(url)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testGetAllEmployees() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/all")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(emp))).andExpect(status().isOk())
				.andExpect(jsonPath("$.[*].id").exists());
	}
	
	@Test
	public void testGetEmployeeByIdNegative() throws Exception {
		
		mockMvc.perform(get("/employee/-17816237")).andExpect(status().isNotFound());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 

}
