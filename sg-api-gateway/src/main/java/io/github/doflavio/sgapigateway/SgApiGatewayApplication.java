package io.github.doflavio.sgapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
public class SgApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgApiGatewayApplication.class, args);
	}

}
