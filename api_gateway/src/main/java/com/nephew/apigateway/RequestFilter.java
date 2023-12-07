package com.nephew.apigateway;

import java.net.URI;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RequestFilter implements GlobalFilter, Ordered {

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

	// GET http://localhost:8765/security-service/api/v1/public/health

	private List<String> openApiEndpoints = List.of("/api/v1/public/", "public", "/public");

	private Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));

	private boolean isUriOpen(URI uri) {
		for (String openPoints : openApiEndpoints) {
			if (uri.getPath().contains(openPoints)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		logger.info("Request Address: " + exchange.getRequest().getRemoteAddress());
		logger.info("Request Headers: " + exchange.getRequest().getHeaders());
		logger.info("Request received: {} {}", exchange.getRequest().getMethod(), exchange.getRequest().getURI());

		if (isUriOpen(exchange.getRequest().getURI())) {
			return chain.filter(exchange);
		}

		logger.info("Request requires validation...");
		if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
			throw new RuntimeException("Missing authorization header.");
		}
		String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			try {
				logger.info("Beginning validation process for token... ");
				Boolean validated = restTemplate.getForObject("http://localhost:8000/api/v1/public/validate/" + jwt, Boolean.class);
				// Boolean validated = true;
				if (validated == false) {
					exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
					logger.warn("Request was not validated! validated: " + validated);
					return exchange.getResponse().setComplete();
				}
				logger.info("Request was validated! validated: " + validated);
			} catch (Exception e) {
				exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
				logger.warn("Something went wrong during the validation process!");
				return exchange.getResponse().setComplete();
			}

		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		// Set the filter order (e.g., before or after other filters)
		return -1; // A lower value means it runs earlier in the filter chain
	}
}
