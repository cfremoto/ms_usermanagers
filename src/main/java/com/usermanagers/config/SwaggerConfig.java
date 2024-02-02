package com.usermanagers.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.gestionusuarios.privateController"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(
        "Order Service API",
        "Order Service API Description",
        "1.0", // Versión
        "http://codmind.com/terms",
        new Contact("Ing. César Fernandez", "https://www.linkedin.com/in/cesar-augusto-fernandez/",
            "cesar.bfernandez@gmail.com"),
        "LICENSE",
        "https://www.linkedin.com/in/cesar-augusto-fernandez/",
        new ArrayList<>());
  }
}
