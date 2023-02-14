package com.example.demo;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SaveEmploeeComponentTest {
    @Autowired
    WebTestClient webTestClient;
    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void saveEmployeeTest () {

        Employee employee = new Employee("5ebbfa934fffbf123cbdf233", "Praneeth", 10000L);

        when(employeeRepository.save(employee)).thenReturn(Mono.just(employee));
        webTestClient.post().uri("/employee")
                .body(Mono.just(employee), Employee.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Employee.class)
                .consumeWith(resp -> {
                    if (resp.getResponseBody() != null) {
                        assertEquals("Praneeth", resp.getResponseBody().getName());
                    }
                });
    }





/*
    @Test
    public void testGetEmployeeDetails () {

        com.example.demo.model.Employee employee = new com.example.demo.model.Employee("5ebbac8c470a341cc8372cf3", "Dia", 10000L);
        com.example.demo.model.Employee employee1 = new Employee("5ebbac8c470a341gh8372cf3", "Adi", 15000L);

        when(employeeRepository.findAll()).thenReturn(Flux.just(employee,employee1));
        webTestClient.get().uri("/getAllEmployees")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Profile.class)
                .consumeWith(resp -> {
                    if (resp.getResponseBody() != null) {
                        assertEquals("Trainee", resp.getResponseBody().getDesignation());
                    }
                });
    }
*/


}
