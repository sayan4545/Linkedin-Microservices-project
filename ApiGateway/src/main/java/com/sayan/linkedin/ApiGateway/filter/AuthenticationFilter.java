package com.sayan.linkedin.ApiGateway.filter;

import com.sayan.linkedin.ApiGateway.servvices.JwtService;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final JwtService jwtService;
    public AuthenticationFilter(JwtService jwtService){
        super(Config.class);
        this.jwtService = jwtService;
    }
    @Override
    public GatewayFilter apply(AuthenticationFilter.Config config) {
        return (exchange,chain)->{
            log.info("Auth request: {}",exchange.getRequest().getURI());
            final String tokenHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if(tokenHeader==null || !tokenHeader.startsWith("Bearer")){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            final String token = tokenHeader.split("Bearer ")[1];

            try{
                String userId = jwtService.getUserIdFromToken(token);
                ServerWebExchange mutatedExchange = exchange.mutate()
                        .request(r->r.header("X-User-Id",userId))
                        .build();
                return chain.filter(mutatedExchange);

            }catch(JwtException e){
                log.error("jwt error {}", e.getLocalizedMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();

            }


        };
    }

    static class Config {




//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//
//            // Check if the request has the required authorization header
//            if (!request.getHeaders().containsKey("Authorization")) {
//                return onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
//            }
//
//            String authHeader = request.getHeaders().getFirst("Authorization");
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                return onError(exchange, "Invalid Authorization header format", HttpStatus.UNAUTHORIZED);
//            }
//
//            // Extract the token
//            String token = authHeader.substring(7);
//
//            // Here you would validate the token
//            // For now, we'll just pass it through
//            // In a real implementation, you would call a service to validate the token
//
//            return chain.filter(exchange);
//        };
    }

//    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(status);
//        return response.setComplete();
//    }
//
//    public static class Config {
//        // Configuration properties can be added here
//    }
}
