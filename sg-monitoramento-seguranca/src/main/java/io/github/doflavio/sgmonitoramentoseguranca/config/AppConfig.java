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
		
		//modelMapper.createTypeMap(, null)
		//mapper.setSerializationInclusion(Include.NON_NULL);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		
		return modelMapper;
	}
	/*
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule module = new JavaTimeModule();
	    module.addSerializer(LOCAL_DATETIME_SERIALIZER);
		//objectMapper.registerModule(new JavaTimeModule());
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return objectMapper;
	}*/
	
	/*
	@Bean
	public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
	    return new Jackson2ObjectMapperBuilder().serializers(LOCAL_DATETIME_SERIALIZER)
	      .serializationInclusion(JsonInclude.Include.NON_NULL);
	}
	*/
	
	/* @Bean
	    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

	        return builder -> {

	            // formatter
	            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	            // deserializers
	            builder.deserializers(new LocalDateDeserializer(dateFormatter));
	            builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));

	            // serializers
	            builder.serializers(new LocalDateSerializer(dateFormatter));
	            builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
	        };
	    }
	*/
}
