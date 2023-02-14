package com.example.webclient.controller;

import com.example.webclient.Service.ProfileRepository;
import com.example.webclient.Service.ProfileService;
import com.example.webclient.model.Employee;
import com.example.webclient.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.xml.ws.Response;
import java.time.Duration;
import java.util.Date;

@RestController
public class ProfileController {


    @Autowired
    private ProfileService profileService;

    public ProfileController ( ProfileService profileService ) {
        this.profileService = profileService;
    }

    @GetMapping(value = "/profiles")
    public Flux<Profile> getAll () {
        return profileService.findAll();
    }

    @GetMapping(value = "/profile/{id}")

    public ResponseEntity<Mono<Profile>> getId ( @PathVariable("id") final String proId ) {
        return new ResponseEntity<>(profileService.findProfileById(proId), HttpStatus.OK);
    }

    @GetMapping(value = "/profileByName/{name}")
    public Mono<Profile> getProfileByName ( @PathVariable("name") final String name ) {
        return profileService.findProfileByName(name);
    }

    @PostMapping("/profile")
    public Mono<Profile> save ( @RequestBody Profile profile ) {
        return profileService.save(profile);
    }

}