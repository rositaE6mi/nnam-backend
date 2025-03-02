package com.logonedigital.Nnam.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "NANM BACKEND APIs",
                        email = "contact@nanm.com",
                        url = "https://nanm.com"
                ),
                title = "NANM WEB APIs",
                description = "NANM BACKEND APIs",
                termsOfService = "&copy; LOGONEDIGITAL",
                version = "v1"
        )
)
public class OpenApiConfig {
}
