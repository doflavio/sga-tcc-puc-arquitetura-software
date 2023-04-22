package io.github.doflavio.sgnotificacao.infra;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.doflavio.sgnotificacao.dtos.NotificacaoEmitidaIncidenteDTO;

@Component
public class EmissaoNotificacaoIncidenteSubscriber {

	@Autowired
	private ModelMapper modelMapper;
	
	//@RabbitListener(queues = "${mq.queues.notificacao.incidente}")
	public void receberNotificacaoIncidente(@Payload String payload){
		try {
			NotificacaoEmitidaIncidenteDTO emissaoNotificacaoIncidenteDTO = convertIntoEmissaoNoificacaoIncidenteDTO(payload);
			System.out.println("Protocolo emissão: " + emissaoNotificacaoIncidenteDTO.getProtocoloEmissao());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	private NotificacaoEmitidaIncidenteDTO convertIntoEmissaoNoificacaoIncidenteDTO(String payload) throws JsonMappingException, JsonProcessingException {
		//return modelMapper.map(payload, EmissaoNoificacaoIncidenteDTO.class);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(payload, NotificacaoEmitidaIncidenteDTO.class);
	}
	
}
