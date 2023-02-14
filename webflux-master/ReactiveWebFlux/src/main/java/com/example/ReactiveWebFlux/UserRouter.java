package com.example.ReactiveWebFlux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {
    @Autowired
    UserHandler userHandler;

    @Bean
    public RouterFunction<ServerResponse> route () {

        return RouterFunctions
                .route(GET("/user/{id}").and(accept(APPLICATION_JSON)), userHandler::getUser)
                .andRoute(GET("/user").and(accept(APPLICATION_JSON)), userHandler::listUser)
                .andRoute(POST("/user/create").and(accept(APPLICATION_JSON)), userHandler::saveUser);

    }

}
