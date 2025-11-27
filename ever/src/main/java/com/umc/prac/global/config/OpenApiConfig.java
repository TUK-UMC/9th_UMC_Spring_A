package com.umc.prac.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "UMC Ever API",
                version = "v1",
                description = "UMC 9th Spring A 서비스 API 명세",
                contact = @Contact(name = "UMC Team")
        )
)
public class OpenApiConfig {
}

