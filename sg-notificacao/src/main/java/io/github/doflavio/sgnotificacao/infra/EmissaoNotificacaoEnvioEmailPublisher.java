package io.github.doflavio.sgnotificacao.infra;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.doflavio.sgnotificacao.dtos.NotificacaoEnvioEmailDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmissaoNotificacaoEnvioEmailPublisher {

	@Autowired
	private final RabbitTemplate rabbitTemplate;
	
	//@Qualifier("notificacaoEnvioEmail")
	//private final Queue queueEmissaoNotificacaoEnvioEmail;
	
	
	//TODO: Por enquando não está sendoutilizado
	public void emitirNotificacaoIncidente(NotificacaoEnvioEmailDTO emissaoNotificacaoIncidenteDTO) throws JsonProcessingException {
		var json = convertIntoJson(emissaoNotificacaoIncidenteDTO);
		//rabbitTemplate.convertAndSend(queueEmissaoNotificacaoEnvioEmail.getName(),json);
		//rabbitTemplate.convertAndSend("ms.sg.notificacao.envio.email",json);
	}
	
	private String convertIntoJson(NotificacaoEnvioEmailDTO emissaoNotificacaoIncidenteDTO) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(emissaoNotificacaoIncidenteDTO);
	}
}
