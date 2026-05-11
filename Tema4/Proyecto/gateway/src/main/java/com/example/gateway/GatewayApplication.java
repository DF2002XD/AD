package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()

				.route("usuarios", r -> r.path("/usuarios/**")
						.uri("lb://usuarios:8501"))

				.route("reservas", r -> r.path("/reservas/**")
						.uri("lb://reservas:8502"))

				.route("comentarios-graphql", r -> r.path("/comentarios/graphql/**")
						.uri("lb://comentarios:8503"))

				.build();
	}

}
