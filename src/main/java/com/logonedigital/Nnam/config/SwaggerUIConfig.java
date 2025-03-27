package com.logonedigital.Nnam.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class SwaggerUIConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi
                .builder()
                .group("public")
                .packagesToScan("com.logonedigital.Nnam") // À ajuster selon ton package principal
                .build();

    }
}
