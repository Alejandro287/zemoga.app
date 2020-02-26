package com.zemoga.portfolio.controllers;

import com.zemoga.portfolio.models.Tweet;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import twitter4j.TwitterException;

@RequestMapping(path = "/api/users")
public interface TwitterController {
    @ApiOperation(value = "Get the five last tweets posted for the user",
            notes = "Also, it returns a link to retrieve all user with rel - users and the user owner of the tweets - user")
    @GetMapping(path = "/{id}/tweets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    Mono<CollectionModel<Tweet>> getTweets(@PathVariable Long id) throws TwitterException;
}
