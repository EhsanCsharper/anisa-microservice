package org.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class LogFilter implements GlobalFilter {
    private final static Logger logger = LoggerFactory.getLogger(LogFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        Long start = System.currentTimeMillis();

        // pre process
        return chain.filter(exchange).then(Mono.fromRunnable(
                () -> {
                    // post process
                    Long end = System.currentTimeMillis();
                    logger.info("request time {}ms", end - start);
                }
        ));
    }
}
