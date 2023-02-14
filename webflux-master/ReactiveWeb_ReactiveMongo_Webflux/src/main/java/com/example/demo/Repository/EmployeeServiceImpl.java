package com.example.demo.Repository;

import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl ( EmployeeRepository employeeRepository ) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Mono<Employee> findEmployeeById ( String Id ) {
        return employeeRepository.findById(Id);
    }

    @Override
    public Flux<Employee> findAll () {
        return employeeRepository.findAll();
    }

    @Override
    public Mono<Employee> save ( Employee employee ) {
        return employeeRepository.save(employee);
    }

    @Override
    public Mono<Void> delete ( Employee employee ) {
        return employeeRepository.delete(employee);
    }
}
