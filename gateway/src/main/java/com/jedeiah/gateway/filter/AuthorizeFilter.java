package com.jedeiah.gateway.filter;

import com.jedeiah.commons.enums.AuthorizationEnum;
import com.jedeiah.commons.enums.HeaderParamEnum;
import com.jedeiah.commons.utls.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * token校验
 */
@Component
@Order(0)
public class AuthorizeFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getURI().getPath();
        if (path.startsWith("/users/login") || path.startsWith("/ldap/login") || path.startsWith("/users/add") || path.startsWith("/oauth2")) {
            return chain.filter(exchange);
        }

        String token = getTokenFromRequest(request);
        if (!StringUtils.hasLength(token)) {
            response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
            return response.setComplete();
        }

        return parseToken(token)
                .flatMap(claims -> {
                    request.mutate().header(HeaderParamEnum.USER_ID.name(), (String) claims.get(HeaderParamEnum.USER_ID.name())).build();
                    request.mutate().header(HeaderParamEnum.LOGIN_TYPE.name(), (String) claims.get(HeaderParamEnum.LOGIN_TYPE.name())).build();
                    return chain.filter(exchange);
                })
                .onErrorResume(e -> {
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                });
    }

    private Mono<Claims> parseToken(String token) {
        try {
            return Mono.just(JwtTokenUtil.parsePayload(token));
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    private String getTokenFromRequest(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(AuthorizationEnum.JWT_TOKEN.name());
        if (!StringUtils.hasLength(token)) {
            token = request.getQueryParams().getFirst(AuthorizationEnum.JWT_TOKEN.name());
        }
        if (!StringUtils.hasLength(token)) {
            HttpCookie httCcookie = request.getCookies().getFirst(AuthorizationEnum.JWT_TOKEN.name());
            if (httCcookie != null) {
                token = httCcookie.getValue();
            }
        }
        return token;
    }
}