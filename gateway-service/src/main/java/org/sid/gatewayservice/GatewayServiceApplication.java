package org.sid.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrix
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	@Bean
	RouteLocator staticRoutes(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(r->r
						.path("/publicCountries/**")
						.filters(f->f
								.addRequestHeader("x-rapidapi-host","restcountries-v1.p.rapidapi.com")
								.addRequestHeader("x-rapidapi-key","801c7b5eeamshf1544ec74a74c59p1042fdjsn1460430e99d0")
								.rewritePath("/publicCountries/(?<segment>.*)","/${segment}")
						)
						.uri("https://restcountries-v1.p.rapidapi.com").id("r1"))
				.route(r->r
				.path("/muslim/**")
				.filters(f->f
						.addRequestHeader("x-rapidapi-host","muslimsalat.p.rapidapi.com")
						.addRequestHeader("x-rapidapi-key","801c7b5eeamshf1544ec74a74c59p1042fdjsn1460430e99d0")
						.rewritePath("/muslim/(?<segment>.*)","/${segment}")
				)
				.uri("https://muslimsalat.p.rapidapi.com").id("r2"))
				.build();
	}
	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,
														DiscoveryLocatorProperties dlp){
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}

}
