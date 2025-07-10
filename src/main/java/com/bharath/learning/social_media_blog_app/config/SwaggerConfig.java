package com.bharath.learning.social_media_blog_app.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI customerOpenAPI() {
        return new OpenAPI()
           /*     .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new Components().addSecuritySchemes("basicAuth", ))
                        new io.swagger.v3.oas.models.security.SecurityScheme()
                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                .scheme("basic")
                                //.in(SecurityScheme.In.HEADER)
                                .name("Authorization")*/
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Social Media Blog App API")
                        .version("1.0.0")
                        .description("API documentation for the Social Media Blog Application")
                        .contact(new Contact()
                                .name("Bharath Ashok")
                                .email("iambharath.ashoka@gmail.com")
                                .url("https://localhost:8080/swagger-ui/index.html#/"))
                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }
}
