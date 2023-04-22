package io.github.doflavio.sgmonitoramentoseguranca.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Value("${mq.queues.notificacao.incidente}")
    private String filaNotificacaoIncidente;

	@Bean
	public Queue queueNotificacaoIncidente() {
		return new Queue(filaNotificacaoIncidente,true);
	}
}
