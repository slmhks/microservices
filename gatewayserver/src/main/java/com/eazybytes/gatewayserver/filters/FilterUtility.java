package com.eazybytes.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Optional;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "eazybank-correlation-id";

    public Optional<String> getCorrelationId(HttpHeaders requestHeaders) {
        return Optional.ofNullable(requestHeaders.get(CORRELATION_ID))
                .flatMap(requestHeadersList -> requestHeadersList.stream().findFirst());
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, correlationId);
    }

    private ServerWebExchange setRequestHeader(ServerWebExchange exchange, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(CORRELATION_ID, value).build()).build();
    }
}
