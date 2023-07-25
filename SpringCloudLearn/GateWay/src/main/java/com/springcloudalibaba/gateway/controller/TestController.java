package com.springcloudalibaba.gateway.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@EnableAutoConfiguration
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/getTestDate")
    public String getTestDate(){
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        String formatDateTime = formatter.format(localDate);
        return formatDateTime;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder locatorBuilder){
        return locatorBuilder.routes().route(r -> r.host(""))
    }

}
