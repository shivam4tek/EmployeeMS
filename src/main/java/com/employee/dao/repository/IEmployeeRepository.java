package com.employee.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.dao.entity.Employee;

@Repository
public interface IEmployeeRepository extends CrudRepository<Employee, Long>{

}
