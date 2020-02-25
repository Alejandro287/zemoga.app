package com.zemoga.portfolio.services;

import com.zemoga.portfolio.models.User;
import org.springframework.hateoas.CollectionModel;
import reactor.core.publisher.Mono;


public interface UserLinksService {
    Mono<CollectionModel<User>> getAll();

    Mono<User> getById(Long id);

    Mono<User> updateUser(Long id, User user);

}
