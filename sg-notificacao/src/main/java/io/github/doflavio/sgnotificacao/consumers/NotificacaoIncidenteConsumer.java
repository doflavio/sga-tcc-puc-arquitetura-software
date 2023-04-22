package io.github.doflavio.sgnotificacao.consumers;

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

import io.github.doflavio.sgnotificacao.dtos.NotificacaoEmitidaIncidenteDTO;
import io.github.doflavio.sgnotificacao.feignclients.UserFeignClient;
import io.github.doflavio.sgnotificacao.models.EmailModel;
import io.github.doflavio.sgnotificacao.models.User;
import io.github.doflavio.sgnotificacao.services.EmailService;

@Component
public class NotificacaoIncidenteConsumer {

	private static final Logger logger = LoggerFactory.getLogger(NotificacaoIncidenteConsumer.class);
	
	private static final String EMAIL_FROM = "doflavio@gmail.com";
	private static final String SUBJECT = "Teste de integração";
	private static final String MSG_MOCK_EMAIL = "Esta mensagem faz parte de um teste de envio de email por uma aplicação de notificação do sistema SIGAM";
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    EmailService emailService;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@RabbitListener(queues = "${mq.queues.notificacao.incidente}")
	public void receberNotificacaoIncidente(@Payload String payload){
		try {
			NotificacaoEmitidaIncidenteDTO notificacaoEmitidaIncidenteDTO = convertIntoEmissaoNoificacaoIncidenteDTO(payload);
			System.out.println("Protocolo emissão: " + notificacaoEmitidaIncidenteDTO.getProtocoloEmissao());
			notificar(notificacaoEmitidaIncidenteDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	private NotificacaoEmitidaIncidenteDTO convertIntoEmissaoNoificacaoIncidenteDTO(String payload) throws JsonMappingException, JsonProcessingException {
		//return modelMapper.map(payload, EmissaoNoificacaoIncidenteDTO.class);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(payload, NotificacaoEmitidaIncidenteDTO.class);
	}
	
	
	private void notificar(NotificacaoEmitidaIncidenteDTO notificacaoEmitidaIncidenteDTO) {
		
		for (Integer usuarioId : notificacaoEmitidaIncidenteDTO.getIdsUsuariosImpactados()) {
			User usuario = findUsuarioById(usuarioId);
			EmailModel emailModel = new EmailModel();
			
			emailModel.setOwnerRef(usuario.getName());
			emailModel.setOwnerRef(EMAIL_FROM);
			emailModel.setEmailTo(usuario.getEmail());
			emailModel.setText(MSG_MOCK_EMAIL);
			emailModel.setSubject(SUBJECT);
			enviarEmail(emailModel);
		}
		
	}
	
	public void enviarEmail(EmailModel emailModel) {
        emailService.sendEmail(emailModel);
        System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
        System.out.println("ownerRef: " + emailModel.getOwnerRef());
    }
	
	private User findUsuarioById(Integer id) {
		User user = userFeignClient.findById(id.longValue()).getBody();
		if(user == null) {
			logger.error("ID usuário indexistente: " + id);
			throw new IllegalArgumentException("Usuário not found");
		}
		return user;
	}
	
}
