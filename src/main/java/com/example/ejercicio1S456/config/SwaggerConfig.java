package com.example.ejercicio1S456.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


//config Swagger para la generación de documentación de la API REST
//http://localhost:8080/swagger-ui/
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){                            //esta clase que implementa el patrón builder
                                                     //esta pensado para ser la interface principal al framework de springfox. nos
                                                    //proporciona la integracio con Swagger

    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiDetails())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
            //con este método se construye todo lo que se fue poniendo
    }
    private ApiInfo apiDetails(){
            return new ApiInfo("Spring Boot Laptop API REST",
                    "Library Api rest docs",
                    "1.0",
                    "http://www.google.com",
                    new Contact("Leandro",
                            "http://www.google.com",
                            "ejercicio@bootcamp.com"),
                            "MIT",
                            "http://www.google.com",
                            Collections.emptyList());
    }
    //entonces se crea un bean con la version simplificada de
    //la configuracion a base de concatentar llamadas a metodos
    //asi se construye la configuración de la doc de la api
}
