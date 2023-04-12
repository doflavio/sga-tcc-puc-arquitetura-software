package io.github.doflavio.sgaauthauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
public class SgaAuthAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgaAuthAuthorizationApplication.class, args);
	}

}
