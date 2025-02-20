package br.com.controle_de_estoque.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Controle Estoque")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(
                new Info()
                        .title("API Projeto controle de estoque")
                        .description("Apis controle de estoque, venda e produto")
                        .version("v0.0.1")
        );
    }
}
