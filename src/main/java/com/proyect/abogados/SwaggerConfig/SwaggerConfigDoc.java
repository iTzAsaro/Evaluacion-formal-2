package com.proyect.abogados.SwaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigDoc {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                    .info(new Info()
                    .title("API 2025 Aplicacion de abogados JUSTICIA PL")
                    .version("1.0")
                    .description("Esta es la documentacion de nuestro proyecto sobre el sistema de abogados y clientes"));
    }
}
