package com.zemoga.portfolio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet extends RepresentationModel<Tweet> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String text;
}
