package io.github.doflavio.sgconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SgConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgConfigServerApplication.class, args);
	}

}
