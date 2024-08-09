package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDate;

@SpringBootApplication
public class ApiGatewayApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ApiGatewayApplication.class);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec ->
                        predicateSpec.path("/api/v1/product/**")
                                .filters(gatewayFilterSpec -> gatewayFilterSpec
                                        .addResponseHeader("my-response-header-date", LocalDate.now().toString())
                                        .circuitBreaker(config -> config
                                                .setName("apiGatewayCircuitBreaker")
                                                .setFallbackUri("forward:/circuitBreaker/fallback")
                                        )
                                )
                                .uri("lb://PRODUCT")
                ).route(predicateSpec ->
                        predicateSpec.path("/api/v1/discount/**")
                                .filters(gatewayFilterSpec -> gatewayFilterSpec
                                        .retry(retryConfig -> retryConfig
                                                .setRetries(3)
                                                .setMethods(HttpMethod.GET)
                                                .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
                                        )
                                )
                                .uri("lb://DISCOUNT")
                )
                .build();
    }
}
