package com.zemoga.portfolio.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorMessage {

    /* HTTP status code */
    @Builder.Default
    private HttpStatus status = null;

    /* Details of the error message */
    @Builder.Default
    private String detail = null;

    /* Error code */
    @Builder.Default
    private String code = null;

    /* Link to more information on the error */
    @Builder.Default
    private String link = null;

    @Builder.Default
    @NotNull
    private LocalDateTime timestamp = LocalDateTime.now();
}
