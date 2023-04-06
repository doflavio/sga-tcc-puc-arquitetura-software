package io.github.doflavio.sgnotificao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SgNotificaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgNotificaoApplication.class, args);
	}

}
