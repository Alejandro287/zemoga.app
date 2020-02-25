package com.zemoga.portfolio.services;

import com.zemoga.portfolio.exceptions.UserNotFoundException;
import com.zemoga.portfolio.models.User;
import com.zemoga.portfolio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.Serializable;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> getUserById(Long id) {
        return Mono.fromCallable(
                () -> userRepository.findById(id)
                                    .orElseThrow(
                                        () -> new UserNotFoundException(id)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<User> updateUser(Long id, User user) {
        return Mono.fromCallable(
                () -> {
                    userRepository.findById(id)
                            .orElseThrow(() -> new UserNotFoundException(id));
                    user.setId(id);
                    return userRepository.save(user);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<User> getAll(){
        return Flux.fromIterable(userRepository.findAll());
    }
}
