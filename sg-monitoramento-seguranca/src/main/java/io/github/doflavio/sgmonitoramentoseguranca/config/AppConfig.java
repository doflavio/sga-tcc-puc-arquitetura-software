package io.github.doflavio.sgmonitoramentoseguranca.config;

import org.modelmapper.ModelMapper;
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
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		/*
		modelMapper.createTypeMap(, null)
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
		*/
		return modelMapper;
	}

}
