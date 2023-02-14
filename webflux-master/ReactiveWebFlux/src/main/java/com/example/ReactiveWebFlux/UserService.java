package com.example.ReactiveWebFlux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	Mono<User> saveUser ( User user );
	Flux<User> getUsers ();
	Mono<User> getUserById ( String id );
	
}
