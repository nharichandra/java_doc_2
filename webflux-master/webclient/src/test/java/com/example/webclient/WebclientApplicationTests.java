package com.example.webclient;

import com.example.demo.Repository.EmployeeRepository;
import com.example.webclient.Service.ProfileRepository;
import com.example.webclient.model.Profile;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class WebclientApplicationTests {
    @Autowired
    WebTestClient webTestClient;
    @Mock
    ProfileRepository profileService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void saveProfileTest () {

        Profile profile = new Profile("5ebbfa934fffbf123cbdf233", "Praneeth", 1, "UI", "Trainee");

        when(profileService.save(profile)).thenReturn(Mono.just(profile));
        webTestClient.post().uri("/profile")
                .body(Mono.just(profile), Profile.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Profile.class)
                .consumeWith(resp -> {
                    if (resp.getResponseBody() != null) {
                        assertEquals("Trainee", resp.getResponseBody().getDesignation());
                    }
                });
    }

    @Test
    public void testGetProfileById(){
        Profile profile = new Profile("5ebbfa934fffbf123cbdf233", "Praneeth", 1, "UI", "Trainee");
        when(profileService.getProfileByName(anyString())).thenReturn(Mono.just(profile));
        webTestClient.get().uri("/profileByName/Praneeth")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Profile.class)
                .consumeWith(resp -> {
                    if (resp.getResponseBody() != null) {
                        assertEquals("Trainee", resp.getResponseBody().getDesignation());
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
