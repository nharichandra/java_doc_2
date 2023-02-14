package com.example.demo.Controller;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;
import com.example.demo.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@RestController
public class EmployeeController {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8833/")
            .build();

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/employees")
    public Flux<Employee> getAll () {
        return employeeRepository.findAll();
    }

    @GetMapping(value = "/employee/{id}")
    public Mono<Employee> getId ( @PathVariable("id") final String empId ) {
        return employeeRepository.findById(empId);
    }

    @PostMapping("/employee")
    public Mono<Employee> save ( @RequestBody Employee employee ) {

        return employeeRepository.save(employee);
    }

    @PostMapping(value = "/employees")
    public Flux<Employee> saveAll ( @RequestBody Flux<Employee> entities ) {
        return employeeRepository.saveAll(entities);
    }

    @DeleteMapping("/employee/{id}")
    public Mono<Void> delete ( @PathVariable("id") final String id ) {
        return employeeRepository.deleteById(id);
        // employeeRepository
    }

    @GetMapping("/employee/names")
    public Flux<Object> getEmployeeNames () {
        return employeeRepository.findAll().map(employee -> employee.getName());

    }

    @GetMapping("/employee/getEmployeeProfileByName/{name}")
    public Mono<Profile> getEmployeeProfileByName(@PathVariable String name){
        Mono<Profile> profile = webClient
                .get()
                .uri("profileByName/"+name)
                .retrieve()
                .bodyToMono(Profile.class);
        profile.subscribe(profile1 -> System.out.println(profile1.toString()));
        //  log.info("Exiting NON-BLOCKING ProfileController!");
        return profile;
    }

    @GetMapping(value = "/employee/getAllProfile",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Profile> getEmployeesNonBlacking() {
        Flux<Profile> profileFlux = webClient
                .get()
                .uri("/profiles")
                .retrieve()
                .bodyToFlux(Profile.class);

        profileFlux.subscribe(tweet -> System.out.println(tweet.toString()));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(profileFlux, durationFlux).map(Tuple2::getT1);

    }

// sample event streaming to get idea
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/StreamOfEvents")
    Flux<Employee.Event> events () {
        Flux<Employee.Event> eventFlux = Flux.fromStream(Stream.
                generate(() -> new Employee.Event(System.currentTimeMillis(),
                                                  LocalDateTime.now())));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(5));
        return Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1);
    }
}
