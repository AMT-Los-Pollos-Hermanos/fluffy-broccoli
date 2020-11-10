package ch.heigvd.broccoli.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ch.heigvd.broccoli"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(singletonList(securityScheme()))
                .securityContexts(singletonList(securityContext()))
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Fluffy broccoli API")
                .description("An API to use to gamify everything")
                .version("0.0.1")
                .build();
    }

    private SecurityScheme securityScheme() {
        return new ApiKey("X-API-KEY", "api_key", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReferences())
                .build();
    }

    private List<SecurityReference> securityReferences() {
        return singletonList(
                new SecurityReference("X-API-KEY", new AuthorizationScope[]{}));
    }

}