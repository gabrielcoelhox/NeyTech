package com.neytech.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MsCloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCloudgatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
					.route(r -> r.path("/customers/**").uri("lb://ms-customer"))
					.route(r -> r.path("/cards/**").uri("lb://ms-cards"))
					.route(r -> r.path("/credit-appraiser/**").uri("lb://ms-creditappraiser"))
				.build();
	}
}
