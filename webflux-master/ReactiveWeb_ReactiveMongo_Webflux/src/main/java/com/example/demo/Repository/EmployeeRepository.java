package com.example.demo.Repository;

import com.example.demo.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}

	
	


