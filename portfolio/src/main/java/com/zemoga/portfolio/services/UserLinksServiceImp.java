package com.zemoga.portfolio.services;

import com.zemoga.portfolio.cotrollers.TwitterController;
import com.zemoga.portfolio.cotrollers.UserController;
import com.zemoga.portfolio.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import twitter4j.TwitterException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserLinksServiceImp implements UserLinksService {

    private UserService userService;
    private final UserController userController = methodOn(UserController.class);
    private final TwitterController twitterController = methodOn(TwitterController.class);

    @Autowired
    public UserLinksServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<CollectionModel<User>> getAll() {
        Mono<List<User>> users = userService.getAll()
                .map(user -> {
                    try {
                        return user.add(
                                linkTo(userController.getById(user.getId()))
                                        .withSelfRel(),
                                linkTo(twitterController.getTweets(user.getId()))
                                        .withRel("tweets"));
                    } catch (TwitterException e) {
                        return user.add(
                                linkTo(userController.getById(user.getId()))
                                        .withSelfRel());
                    }
                })
                .collectList();

        return users.map(usersList -> new CollectionModel<>(usersList,
                linkTo(userController.getAll())
                        .withSelfRel()));
    }

    @Override
    public Mono<User> getById(Long id) {
        return userService.getUserById(id)
                .map(user ->addUserLinks(id, user));
    }

    @Override
    public Mono<User> updateUser(Long id, User user) {
        return userService.updateUser(id, user)
                .map(userSaved -> addUserLinks(id, userSaved));
    }

    private User addUserLinks(Long id, User user){
        try {
            return user.add(
                    linkTo(userController.getById(id))
                            .withSelfRel(),
                    linkTo(userController.getAll())
                            .withRel("users"),
                    linkTo(twitterController.getTweets(user.getId()))
                            .withRel("tweets")
            );
        } catch (TwitterException e) {
            return user.add(
                    linkTo(userController.getById(id))
                            .withSelfRel(),
                    linkTo(userController.getAll())
                            .withRel("users")
            );
        }
    }

}
