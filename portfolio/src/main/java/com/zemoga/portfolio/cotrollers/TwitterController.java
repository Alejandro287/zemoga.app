package com.zemoga.portfolio.cotrollers;

import com.zemoga.portfolio.models.Tweet;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import twitter4j.TwitterException;

@RequestMapping(path = "/api/users")
public interface TwitterController {
    @GetMapping(path = "/{id}/tweets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    Mono<CollectionModel<Tweet>> getTweets(@PathVariable Long id) throws TwitterException;
}
