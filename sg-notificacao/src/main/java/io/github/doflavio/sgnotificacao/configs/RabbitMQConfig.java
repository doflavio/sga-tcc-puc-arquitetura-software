package io.github.doflavio.sgnotificacao.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
	
	@Value("${mq.queues.notificacao.incidente}")
    private String filaNotificacaoIncidente;

	//@Bean(name = "notificacaoIncidente")
	public Queue queueNotificacaoIncidente() {
		return new Queue(filaNotificacaoIncidente,true);
	}
	
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
