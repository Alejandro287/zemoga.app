package com.zemoga.portfolio.controllers;

import com.zemoga.portfolio.models.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequestMapping(path = "/api/users")
public interface UserController {

    @ApiOperation(value = "Get a list of all the users")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    Mono<CollectionModel<User>> getAll();

    @ApiOperation(value = "Get the user's data, and the main links to redirect to more info")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    Mono<User> getById(@Valid @PathVariable Long id);

    @ApiOperation(value = "Update the user's data, and get the main links to redirect to more info")
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    Mono<User> updateUser(@Valid @PathVariable(value="id") Long id, @RequestBody User user);

}
