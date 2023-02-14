package com.example.ReactiveWebFlux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Mono<User> saveUser(User user) {
		
		user.setRecordDate(LocalDateTime.now());
		return userRepository.save(user);
	}

	@Override
	public Flux<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public Mono<User> getUserById(String id) {
		
		return userRepository.findById(id);
	}

	

}
