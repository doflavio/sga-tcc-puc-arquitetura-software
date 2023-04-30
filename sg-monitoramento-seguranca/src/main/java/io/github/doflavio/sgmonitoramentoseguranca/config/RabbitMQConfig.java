package io.github.doflavio.sgmonitoramentoseguranca.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


//TODO: Jackson
//https://learnersbucket.com/examples/java/serialize-deserialize-java-8-java-time-with-jackson-json-mapper/
//https://www.javaguides.net/2019/04/jackson-jsoninclude-example.html


@Configuration
public class RabbitMQConfig {
	
	@Value("${mq.queues.notificacao.incidente}")
    private String filaNotificacaoIncidente;

	@Bean
	public Queue queueNotificacaoIncidente() {
		return new Queue(filaNotificacaoIncidente,true);
	}
	
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new JavaTimeModule());
		return new Jackson2JsonMessageConverter(mapper);
	}

}
