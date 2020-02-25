package com.zemoga.portfolio.services;

import com.zemoga.portfolio.models.Tweet;
import org.springframework.hateoas.CollectionModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import twitter4j.TwitterException;

public interface TwitterService {
    Mono<CollectionModel<Tweet>> getTimeLine(Long id) throws TwitterException;
}
