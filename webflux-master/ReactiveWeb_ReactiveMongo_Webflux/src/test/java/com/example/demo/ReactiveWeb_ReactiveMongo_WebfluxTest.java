package com.example.demo;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;
import net.bytebuddy.matcher.ElementMatcher;
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
public class ReactiveWeb_ReactiveMongo_WebfluxTest {
	@Autowired
	WebTestClient webTestClient;
	@Mock
	EmployeeRepository employeeRepository;
	@Test
	public void testSaveEmployee(){
		Employee employee = new Employee("5ebbfa934fffbf123cbdf233", "Praneeth", 10000L);

		when(employeeRepository.save(employee)).thenReturn(Mono.just(employee));
		webTestClient.post().uri("/employee")
				.body(Mono.just(employee), Employee.class)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Employee.class)
				.consumeWith(resp -> {
					if (resp.getResponseBody() != null) {
						assertEquals("jkjk", resp.getResponseBody().getName());
					}
				});
	}
	}

