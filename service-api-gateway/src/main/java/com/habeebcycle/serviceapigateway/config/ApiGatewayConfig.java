package com.habeebcycle.serviceapigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Value("${server-1-api:http://localhost:8081/}") String server1URI;
    @Value("${server-2-api:http://localhost:8082/}") String server2URI;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
            .route("service1Module", 
                r -> r.path("/service-1/**")
                    .uri(server1URI)
            )
            .route("service2Module",
                r -> r.path("/service-2/**")
                    .uri(server2URI)
            ).build();
    }
}