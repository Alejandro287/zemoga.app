package com.zemoga.portfolio.services;

import com.zemoga.portfolio.cotrollers.TwitterController;
import com.zemoga.portfolio.cotrollers.UserController;
import com.zemoga.portfolio.exceptions.UserNotFoundException;
import com.zemoga.portfolio.models.Tweet;
import com.zemoga.portfolio.models.User;
import com.zemoga.portfolio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class TwitterServiceImp implements TwitterService {

    private final Twitter twitter = TwitterFactory.getSingleton();
    private final TwitterController twitterController = methodOn(TwitterController.class);
    private final UserController userController = methodOn(UserController.class);
    private UserRepository userRepository;

    @Autowired
    public TwitterServiceImp (UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public Mono<CollectionModel<Tweet>> getTimeLine(Long id) throws TwitterException {
//        final String SCREEN_NAME = "narrydreaming";
        final int COUNT = 5;
        final int PAGE = 1;


        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new UserNotFoundException(id));

        String screenName = optionalUser
                .map(User::getUsername)
                .orElse("LordSnow");

        Mono<List<Tweet>> tweetsPublisher = Flux.fromStream(
                twitter.getUserTimeline(screenName, new Paging(PAGE, COUNT))
                .stream())
                .map(status -> {
                    if(status.getRetweetedStatus() != null){
                        status = status.getRetweetedStatus();
                    }
                    Tweet tweet = new Tweet(status.getUser().getName(), status.getText());
                    Link link = new Link("https://twitter.com/" + status.getUser().getScreenName()
                            + "/status/" + status.getId());
                    return tweet.add(link);
                })
                .collectList();

        return tweetsPublisher.map(tweets -> {
                    try {
                        return new CollectionModel<>(tweets,
                                linkTo(twitterController.getTweets(id))
                                        .withSelfRel(),
                                linkTo(userController.getById(id))
                                        .withRel("user"),
                                linkTo(userController.getAll())
                                        .withRel("users")
                                );
                    } catch (TwitterException e) {
                        return new CollectionModel<>(tweets,
                                linkTo(userController.getById(id))
                                        .withRel("user"),
                                linkTo(userController.getAll())
                                        .withRel("users"));
                    }
                 }
              );
    }
}
