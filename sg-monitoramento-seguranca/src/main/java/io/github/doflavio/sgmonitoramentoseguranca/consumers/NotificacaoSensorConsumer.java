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
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.NotificacaoSensorDTO;
import io.github.doflavio.sgmonitoramentoseguranca.services.IncidenteService;

@Component
public class NotificacaoSensorConsumer {

	private static final Logger logger = LoggerFactory.getLogger(NotificacaoSensorConsumer.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	//@RabbitListener(queues = "${mq.queues.notificacao.sensor.envio.valor}")
	public void receberNotificacaoIncidente(@Payload String payload){
		try {
			System.out.println(payload);
			NotificacaoSensorDTO notificacaoSensorDTO = convertIntoNotificacaoSensorDTO(payload);
			System.out.println("Envio messageria - id do sensor: " + notificacaoSensorDTO.getSensorId());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	private NotificacaoSensorDTO convertIntoNotificacaoSensorDTO(String payload) throws JsonProcessingException {
		//return modelMapper.map(payload, EmissaoNoificacaoIncidenteDTO.class);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(payload, NotificacaoSensorDTO.class);
	}
	
}
