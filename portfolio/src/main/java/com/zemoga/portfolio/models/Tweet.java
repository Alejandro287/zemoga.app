package com.zemoga.portfolio.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@ApiModel(description="Main data of a Tweet.")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet extends RepresentationModel<Tweet> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes="Name of the person who post the Tweet")
    private String name;
    @ApiModelProperty(notes="Content of the Tweet")
    private String text;
}
