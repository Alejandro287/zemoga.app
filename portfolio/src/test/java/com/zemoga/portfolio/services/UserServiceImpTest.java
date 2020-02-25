package com.zemoga.portfolio.services;

import com.zemoga.portfolio.exceptions.UserNotFoundException;
import com.zemoga.portfolio.models.User;
import com.zemoga.portfolio.repositories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
public class UserServiceImpTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user1 = new User();
    private User user2 = new User();


    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        user1.setTitle("Title1");
        user2.setTitle("Title2");
        user1.setUsername("Username1");
        user2.setUsername("Username2");
    }

    @Test
    public void findAllTest(){

        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(user1, user2));

        Flux<User> result = userService.getAll();

        StepVerifier
                .create(result)
                .expectNext(user1)
                .expectNext(user2)
                .expectComplete()
                .verify();
    }

    @Test
    public void findUserByIdTest(){

        Long id = 123L;

        Mockito.when(userRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(user1));

        Mono<User> result = userService.getUserById(id);

        StepVerifier
                .create(result)
                .expectNext(user1)
                .expectComplete()
                .verify();
    }

    @Test
    public void findUserByIdErrorTest(){

        Long id = 123L;

        Mockito.when(userRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        StepVerifier
                .create(userService.getUserById(id))
                .expectErrorMatches(throwable -> throwable instanceof UserNotFoundException)
                .verify();
    }

    @Test
    public void saveTest(){

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(user1);

        Mockito.when(userRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(user1));

        Mono<User> result = userService.updateUser(12L, user1);

        StepVerifier
                .create(result)
                .expectNext(user1)
                .expectComplete()
                .verify();
    }
}
