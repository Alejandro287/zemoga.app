package com.zemoga.portfolio.controllers;

import com.zemoga.portfolio.models.User;
import com.zemoga.portfolio.services.UserLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class UserControllerImp implements UserController {

    private UserLinksService userLinksService;

    @Autowired
    public UserControllerImp(UserLinksService userLinksService) {
        this.userLinksService = userLinksService;
    }

    @Override
    public Mono<CollectionModel<User>> getAll() {
        return userLinksService.getAll();
    }

    @Override
    public Mono<User> getById(Long id) {
        return userLinksService.getById(id);
    }

    @Override
    public Mono<User> updateUser(Long id, User user) {
        return userLinksService.updateUser(id, user);
    }
}
