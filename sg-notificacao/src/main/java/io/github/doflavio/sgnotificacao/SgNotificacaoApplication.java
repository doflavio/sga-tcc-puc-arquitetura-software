package io.github.doflavio.sgnotificacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SgNotificacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgNotificacaoApplication.class, args);
	}

}
