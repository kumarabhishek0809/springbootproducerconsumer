package com.springbootproducerconsumer.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Set httpStatusToExpose = new HashSet(Arrays.asList(HttpStatus.OK,
            HttpStatus.CREATED, HttpStatus.ACCEPTED, HttpStatus.BAD_REQUEST));

    @Bean
    public Docket produceApi() {
        final List<ResponseMessage> globalResponses =
                Arrays.stream(HttpStatus.values()).filter(httpStatus -> httpStatusToExpose.contains(httpStatus)).map(httpStatus -> {
                    return new ResponseMessageBuilder().code(httpStatus.value()).message(httpStatus.toString()).build();
                }).collect(Collectors.toList());
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalResponses)
                .globalResponseMessage(RequestMethod.POST, globalResponses)
                .globalResponseMessage(RequestMethod.DELETE, globalResponses)
                .globalResponseMessage(RequestMethod.PATCH, globalResponses).select()
                .apis(RequestHandlerSelectors.basePackage("com.springbootproducerconsumer.controller"))
                .build();
    }
}
