package com.zemoga.portfolio.controllers;

import com.zemoga.portfolio.cotrollers.UserController;
import com.zemoga.portfolio.cotrollers.UserControllerImp;
import com.zemoga.portfolio.models.User;
import com.zemoga.portfolio.services.UserLinksService;
import com.zemoga.portfolio.services.UserLinksServiceImp;
import com.zemoga.portfolio.services.UserLinksService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserController.class)
class UserControllerImpTest {

    @MockBean
    private UserLinksService userLinksService;

    @Autowired
    private WebTestClient webClient;

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
    void getByIdTest() {

        Long id = 12L;

        Mockito.when(userLinksService.getById(Mockito.anyLong()))
                .thenReturn(Mono.just(user1));

        User result = webClient.get()
                .uri("/api/users/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assertEquals(user1, result);
    }


    @Test
    public void updateUserByIdTest() {

        Long id = 12L;

        Mockito.when(userLinksService.getById(Mockito.anyLong()))
                .thenReturn(Mono.just(user1));

        Mockito.when(userLinksService.updateUser(Mockito.anyLong() ,any(User.class)))
                .thenReturn(Mono.just(user1));

        User result = webClient.put()
                .uri("/api/users/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(user1))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assertEquals(user1, result);
    }

    @Test
    public void getAllTest() {

        Mockito.when(userLinksService.getAll())
                .thenReturn(Mono.just(new CollectionModel<>(Arrays.asList(user1, user2), new Link("link"))));

       Stream<Object> result =
              Objects.requireNonNull(webClient.get()
                      .uri("/api/users")
                      .accept(MediaType.APPLICATION_JSON)
                      .exchange()
                      .expectStatus().isOk()
                      .expectBodyList(CollectionModel.class)
                      .returnResult()
                      .getResponseBody())
                      .stream()
                      .map(CollectionModel::getContent);

        Mockito.verify(userLinksService, times(1)).getAll();
    }


}
