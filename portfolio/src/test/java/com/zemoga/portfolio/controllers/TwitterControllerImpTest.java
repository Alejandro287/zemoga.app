package com.zemoga.portfolio.controllers;

import com.zemoga.portfolio.cotrollers.TwitterController;
import com.zemoga.portfolio.cotrollers.TwitterControllerImp;
import com.zemoga.portfolio.cotrollers.UserController;
import com.zemoga.portfolio.models.Tweet;
import com.zemoga.portfolio.models.User;
import com.zemoga.portfolio.services.TwitterService;
import com.zemoga.portfolio.services.TwitterServiceImp;
import com.zemoga.portfolio.services.UserLinksService;
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
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import twitter4j.TwitterException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = TwitterControllerImp.class)
@Import({TwitterControllerImp.class, TwitterServiceImp.class})
public class TwitterControllerImpTest {
    
    @MockBean
    private TwitterService twitterService;

    @Autowired
    private WebTestClient webClient;

    private Tweet tweet1 = new Tweet();
    private Tweet tweet2 = new Tweet();


    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        tweet1.setName("Name1");
        tweet2.setName("Name2");
        tweet1.setText("Text1");
        tweet2.setText("Text2");
    }

    @Test
    public void getAllTest() throws TwitterException {

        Long id = 12L;

        Mockito.when(twitterService.getTimeLine(anyLong()))
                .thenReturn(Mono.just(new CollectionModel<>(Arrays.asList(tweet1, tweet2), new Link("link"))));

        Object result =
                Objects.requireNonNull(webClient.get()
                        .uri("/api/users/{id}/tweets", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(CollectionModel.class)
                        .returnResult()
                        .getResponseBody())
                        .stream()
                        .map(CollectionModel::getContent)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
                        .get(0);

        Mockito.verify(twitterService, times(1)).getTimeLine(id);
    }

}
