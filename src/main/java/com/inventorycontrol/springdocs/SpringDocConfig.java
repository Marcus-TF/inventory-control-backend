package com.inventorycontrol.springdocs;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Spring doc config.
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi groupedOpenApiClients() {
        final String securitySchemeName = "bearerAuth";

        return GroupedOpenApi
                .builder()
                .group("InventoryControl - API")
                .packagesToScan("com.inventorycontrol")
                .addOpenApiCustomizer(openApi -> {
                    openApi
                            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                            .components(
                                    new Components()
                                            .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                                    .name(securitySchemeName)
                                                    .type(SecurityScheme.Type.HTTP)
                                                    .scheme("bearer")
                                                    .bearerFormat("JWT"))
                            )
                            .info(new Info()
                                    .title("InventoryControl - API")
                                    .version("v1")
                                    .description("REST API do Controle de Estoque")
                                    .license(new License().name("Apache 2.0"))
                                    .contact(getContact()));

                })
                .build();
    }

    private Contact getContact() {
        return new Contact()
                .name("Marcus Túlio")
                .email("zMarcust.st@gmail.com");
    }
}
