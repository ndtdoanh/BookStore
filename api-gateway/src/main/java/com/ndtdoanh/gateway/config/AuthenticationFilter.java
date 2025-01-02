package com.ndtdoanh.gateway.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndtdoanh.gateway.dto.ApiResponse;
import com.ndtdoanh.gateway.service.IdentityService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationFilter implements GlobalFilter, Ordered {
    IdentityService identityService;
    ObjectMapper objectMapper;

    @NonFinal
    private String[] publicEndpoints = {"/identity/auth/.*", "/identity/users/registration", "/notification/email/send"
    };

    @Value("${app.api-prefix}")
    @NonFinal
    private String apiPrefix;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Enter Authentication Filter");

        if (isPublicEndpoint(exchange.getRequest())) return chain.filter(exchange);

        // get token from authorization header
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (CollectionUtils.isEmpty(authHeader)) return unauthenticated(exchange.getResponse());

        String token = authHeader.getFirst().replace("Bearer ", "");
        log.info("token: {}", token);

        return identityService
                .introspect(token)
                .flatMap(introspectResponse -> {
                    if (introspectResponse.getResult().isValid()) return chain.filter(exchange);
                    else return unauthenticated(exchange.getResponse());
                })
                .onErrorResume(throwable -> unauthenticated(exchange.getResponse()));
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private boolean isPublicEndpoint(ServerHttpRequest request) {
        return Arrays.stream(publicEndpoints)
                .anyMatch(s -> request.getURI().getPath().matches(apiPrefix + s));
    }

    Mono<Void> unauthenticated(ServerHttpResponse response) {
        ApiResponse<?> apiResponse =
                ApiResponse.builder().code(1401).message("Unauthenticated").build();

        String body = null;
        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
}
