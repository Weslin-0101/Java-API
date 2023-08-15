package com.projeto.pessoal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .contact(new Contact()
                                .name("Wesley Lira")
                                .email("wesleeylirac12@gmail.com")
                        )
                        .title("My Project Rest API")
                        .version("V1 - 1.0.0")
                        .description("Rest API Developed by Weslin-0101 using Spring Boot 3")
                        .termsOfService("Terms of Service")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                );
    }
}
