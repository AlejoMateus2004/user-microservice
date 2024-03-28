package com.salessytem.usermicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.salessytem.usermicroservice.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointInfo());
    }

    public ApiInfo apiEndPointInfo(){
        return new ApiInfoBuilder().title("MicroServices Application")
                .description("User MicroService")
                .contact(new Contact("Alejandro Mateus", "", "alejandromateus711@gmail.com"))
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}

