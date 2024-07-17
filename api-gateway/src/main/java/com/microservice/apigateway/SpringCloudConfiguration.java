package com.microservice.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfiguration {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("userModule", r -> r.path("/api/users/**")
                        .uri("lb://user-service")
                )

                .route("productModule", r -> r.path("/api/products/**")
                        .uri("lb://product-services")
                )
                .build();
    }
}