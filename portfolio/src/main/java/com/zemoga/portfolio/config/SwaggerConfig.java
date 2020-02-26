package com.zemoga.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {
//    private static final Contact DEFAULT_CONTACT = new Contact(
//            "Alejandro Cano", "https://github.com/Alejandro287/zemoga.app", "alejocano287@gmail.com");
//    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
//            "PORTFOLIO JAVA WEB APP", "This API manages the users' information of the portfolio.",
//            "1.0.0", "urn:tos", DEFAULT_CONTACT,
//            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
//    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
//            new HashSet<>(Collections.singleton("application/json"));
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(DEFAULT_API_INFO)
//                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
//                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
//    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any()).build();
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.zemoga.portfolio.controllers"))
//                .apis(RequestHandlerSelectors.basePackage("com.zemoga.portfolio.models"))
                .apis(RequestHandlerSelectors.basePackage("com.zemoga.portfolio"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "PORTFOLIO JAVA WEB APP",
                "This API manages the users' information of the portfolio.",
                "1.0.0",
                "Terms of service",
                new Contact("Alejandro Cano", "https://github.com/Alejandro287/zemoga.app",
                        "alejocano287@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}
