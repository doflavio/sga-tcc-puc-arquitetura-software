package io.github.doflavio.sgnotificacao.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		//modelMapper.createTypeMap(, null)
		//mapper.setSerializationInclusion(Include.NON_NULL);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		
		return modelMapper;
	}
}
