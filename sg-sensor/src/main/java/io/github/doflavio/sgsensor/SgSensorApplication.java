package io.github.doflavio.sgsensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SgSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgSensorApplication.class, args);
	}

}


//https://www.javainuse.com/messaging/rabbitmq/exchange