package com.admirogaieta.gateway.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;



@Configuration
public class GatewayConf {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder)
    {
        return builder.routes()
                      .route("path", r -> r.path("/api/bi*").uri("http://localhost:8080/"))
                      .route("path", r -> r.path("/api/bi/**").uri("http://localhost:8080/"))
                      .route("path", r -> r.path("/api/nif*").uri("http://localhost:8083/"))
                      .route("path", r -> r.path("/api/nif/**").uri("http://localhost:8083/"))
                      .build();
    }

}
