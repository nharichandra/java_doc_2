package com.example.ReactiveWebFlux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class UserHandler {
	@Autowired
	 UserService userService;

	// to get all users
    public Mono<ServerResponse> listUser(ServerRequest request) {
        Flux<User> user = userService.getUsers();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(user, User.class);
    }

    // to get the user by id
    public Mono<ServerResponse> getUser(ServerRequest request) {
    String id = request.pathVariable("id");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> userMono = userService.getUserById(id);
        return userMono.flatMap(user -> ServerResponse.ok()
        		.contentType(APPLICATION_JSON)
        		.body(BodyInserters.fromObject(user))).
                switchIfEmpty(notFound);
    }

    // to save the iser
    public Mono<ServerResponse> saveUser ( ServerRequest request ) {

        Mono<User> driver = request.body(BodyExtractors.toMono(User.class)).flatMap(d -> userService.saveUser(d));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(driver, User.class);
    }
}
