package com.zemoga.portfolio.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;


@ApiModel(description="All details about the user.")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "portfolio")
public class User extends RepresentationModel<User> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idportfolio")
    private Long id;

    @Column(name = "description")
    private String description;

    @ApiModelProperty(notes="Profile user image")
    @Column(name = "image_url")
    private String imageUrl;

    @ApiModelProperty(notes="Twitter username")
    @Column(name = "twitter_user_name")
    private String username;

    @ApiModelProperty(notes="Name to show as the title of your profile")
    @Column(name = "title")
    private String title;
}
