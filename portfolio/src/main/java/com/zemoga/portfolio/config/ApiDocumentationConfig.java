package com.zemoga.portfolio.config;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
@SwaggerDefinition(
        info = @Info(
                description = "This API manages the users' information of the portfolio.",
                version = "1.0.0",
                title = "PORTFOLIO JAVA WEB APP",
                contact = @Contact(
                        name = "Alejandro Cano",
                        email = "alejocano287@gmail.com",
                        url = "https://github.com/Alejandro287/zemoga.app"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "https://github.com/Alejandro287/zemoga.app")
)
public interface ApiDocumentationConfig {}

