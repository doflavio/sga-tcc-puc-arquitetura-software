package io.github.doflavio.sgmonitoramentoseguranca.infra;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.EmissaoNotificacaoIncidenteDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmissaoNotificacaoIncidentePublisher {

	private final RabbitTemplate rabbitTemplate;
	private final Queue queueEmissaoNotificacaoIncidente;
	
	
	public void emitirNotificacaoIncidente(EmissaoNotificacaoIncidenteDTO emissaoNotificacaoIncidenteDTO) throws JsonProcessingException {
		var json = convertIntoJson(emissaoNotificacaoIncidenteDTO);
		rabbitTemplate.convertAndSend(queueEmissaoNotificacaoIncidente.getName(),json);
	}
	
	private String convertIntoJson(EmissaoNotificacaoIncidenteDTO emissaoNotificacaoIncidenteDTO) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(emissaoNotificacaoIncidenteDTO);
	}
}
