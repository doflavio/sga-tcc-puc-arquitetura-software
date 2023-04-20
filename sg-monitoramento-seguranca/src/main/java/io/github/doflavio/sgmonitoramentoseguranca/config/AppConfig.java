package io.github.doflavio.sgmonitoramentoseguranca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.doflavio.sgmonitoramentoseguranca.services.InicializarDadosDBService;

@Configuration
public class AppConfig {

	@Autowired
	private InicializarDadosDBService dbService;
	
	@Bean
	public void inicializarDB() {
		this.dbService.instanciaDB();
	}
}
