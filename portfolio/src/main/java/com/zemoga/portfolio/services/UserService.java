package com.zemoga.portfolio.services;

import com.zemoga.portfolio.models.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public interface UserService {

    Flux<User> getAll();

    Mono<User> getUserById(Long id);

    Mono<User> updateUser(Long id, User user);

}
