package com.zemoga.portfolio.cotrollers;

import com.zemoga.portfolio.models.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequestMapping(path = "/api/users")
public interface UserController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    Mono<CollectionModel<User>> getAll();

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    Mono<User> getById(@Valid @PathVariable Long id);

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    Mono<User> updateUser(@Valid @PathVariable(value="id") Long id, @RequestBody User user);

}
