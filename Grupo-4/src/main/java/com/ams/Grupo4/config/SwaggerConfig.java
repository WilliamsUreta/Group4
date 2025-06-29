package com.ams.Grupo4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
                .title("Api de Sistema de Ventas")
                .version("1.3")
                .description("Con esta API se puede administrar todo lo que contiene una venta, incluyendo la creación, actualización, eliminación y consulta de usuarios, productos, categorías, métodos de pago y ventas.")
        );
    }
}
