package com.springcloudalibaba.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GateRouteConfig {
//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route(r -> r.path("/order/getorder").uri("http://127.0.0.1:8011/order/getorder")
//                ).build();
//    }
}
