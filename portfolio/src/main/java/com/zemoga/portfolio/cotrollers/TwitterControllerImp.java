package com.zemoga.portfolio.cotrollers;

import com.zemoga.portfolio.models.Tweet;
import com.zemoga.portfolio.services.TwitterService;
import com.zemoga.portfolio.services.TwitterServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import twitter4j.TwitterException;

@RestController
public class TwitterControllerImp implements TwitterController {

    private TwitterService twitterService;

    @Autowired
    public TwitterControllerImp(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @Override
    public Mono<CollectionModel<Tweet>> getTweets(Long id) throws TwitterException {
        return twitterService.getTimeLine(id);
    }
}
