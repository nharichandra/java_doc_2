package com.example.demo.Repository;

import com.example.demo.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<Employee> findEmployeeById(String id);

    Flux<Employee> findAll();

    Mono<Employee> save(Employee Employee);

    Mono<Void> delete(Employee employee);
}
