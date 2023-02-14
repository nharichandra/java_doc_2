package com.example.webclient.Service;

import com.example.webclient.model.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProfileRepository extends ReactiveMongoRepository<Profile,String> {

    Mono<Profile> getProfileByName( String name);
}
