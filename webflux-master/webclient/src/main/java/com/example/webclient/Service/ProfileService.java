package com.example.webclient.Service;

import com.example.webclient.model.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProfileService {
    Mono<Profile> findProfileById( String id);

    Flux<Profile> findAll();

    Mono<Profile> save(Profile profile);

    Mono<Void> delete(Profile profile);

    Mono<Profile> findProfileByName( String name);
}
