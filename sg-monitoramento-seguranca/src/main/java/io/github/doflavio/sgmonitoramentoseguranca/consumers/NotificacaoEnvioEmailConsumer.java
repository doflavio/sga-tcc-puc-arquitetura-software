package io.github.doflavio.sgmonitoramentoseguranca.consumers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.NotificacaoEnvioEmailDTO;
import io.github.doflavio.sgmonitoramentoseguranca.services.IncidenteService;

@Component
public class NotificacaoEnvioEmailConsumer {

	private static final Logger logger = LoggerFactory.getLogger(NotificacaoEnvioEmailConsumer.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IncidenteService incidenteService;
	
	//@RabbitListener(queues = "${mq.queues.notificacao.envio.email}")
	public void receberNotificacaoIncidente(@Payload String payload){
		try {
			NotificacaoEnvioEmailDTO notificacaoEnvioEmailDTO = convertIntoEmissaoNoificacaoIncidenteDTO(payload);
			System.out.println("Email Envidado id do incidente: " + notificacaoEnvioEmailDTO.getIncidenteId());
			
			incidenteService.confirmarEnvioEmail(notificacaoEnvioEmailDTO.getIncidenteId(), notificacaoEnvioEmailDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	private NotificacaoEnvioEmailDTO convertIntoEmissaoNoificacaoIncidenteDTO(String payload) throws JsonMappingException, JsonProcessingException {
		//return modelMapper.map(payload, EmissaoNoificacaoIncidenteDTO.class);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(payload, NotificacaoEnvioEmailDTO.class);
	}
	
}
