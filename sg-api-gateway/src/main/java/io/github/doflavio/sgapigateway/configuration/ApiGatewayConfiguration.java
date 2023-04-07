package io.github.doflavio.sgapigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		/*
		Function<PredicateSpec,Buildable<Route>> function = 
				p -> p.path("/get")
			.uri("http://httpbin.org:80");
		return builder.routes().route(function).build();*/
		
				
			return builder.routes()
			.route(p -> p.path("/get")
						.filters(f -> f
							.addRequestHeader("Hello", "World")
							.addRequestParameter("Hello", "World"))
					.uri("http://httpbin.org:80"))
			.route(p -> p.path("/sg-notificacao/**")
					.uri("lb://sg-notificacao"))
			.route(p -> p.path("/sg-user/**")
					.uri("lb://sg-user"))
			.build();
				
	}

}
