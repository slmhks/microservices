package com.eazybytes.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    private FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (this.isCorrelationIdPresent(requestHeaders)) {
            logger.debug("eazybank-correlation-id found in RequestTraceFilter: {}",
                    this.filterUtility.getCorrelationId(requestHeaders));
        } else {
            String correlationId = this.generateCorrelationId();
            this.filterUtility.setCorrelationId(exchange, correlationId);
            logger.debug("eazybank-correlation-id generated in RequestTraceFilter: {}", correlationId);
        }
        return chain.filter(exchange);
    }

    public boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        return this.filterUtility.getCorrelationId(requestHeaders).isPresent();
    }

    public String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
