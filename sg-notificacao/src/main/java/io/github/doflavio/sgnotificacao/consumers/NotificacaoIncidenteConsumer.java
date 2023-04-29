package io.github.doflavio.sgnotificacao.consumers;

import java.time.LocalDateTime;

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
import io.github.doflavio.sgnotificacao.dtos.NotificacaoEnvioEmailDTO;
import io.github.doflavio.sgnotificacao.feignclients.IncidenteFeignClient;
import io.github.doflavio.sgnotificacao.feignclients.UserFeignClient;
import io.github.doflavio.sgnotificacao.infra.EmissaoNotificacaoEnvioEmail;
import io.github.doflavio.sgnotificacao.models.EmailModel;
import io.github.doflavio.sgnotificacao.models.Notificacao;
import io.github.doflavio.sgnotificacao.models.User;
import io.github.doflavio.sgnotificacao.services.EmailService;
import io.github.doflavio.sgnotificacao.services.NotificacaolService;

@Component
public class NotificacaoIncidenteConsumer {

	private static final Logger logger = LoggerFactory.getLogger(NotificacaoIncidenteConsumer.class);
	
	private static final String EMAIL_FROM = "doflavio@gmail.com";
	private static final String SUBJECT = "SIGAM - Mensagem Urgente";
	private static final String MSG_MOCK_EMAIL = "Esta mensagem faz parte de um teste de envio de email por uma aplicação de notificação do sistema SIGAM";
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    EmailService emailService;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private IncidenteFeignClient incidenteFeignClient;
	
	@Autowired
	private EmissaoNotificacaoEnvioEmail emissaoNotificacaoEnvioEmail;
	
	@Autowired
	private NotificacaolService notificacaoService;
	
	@RabbitListener(queues = "${mq.queues.notificacao.incidente}")
	public void receberNotificacaoIncidente(@Payload String payload){
		try {
			NotificacaoEmitidaIncidenteDTO notificacaoEmitidaIncidenteDTO = convertIntoEmissaoNoificacaoIncidenteDTO(payload);
			System.out.println("Protocolo emissão: " + notificacaoEmitidaIncidenteDTO.getProtocoloEmissao());
			notificar(notificacaoEmitidaIncidenteDTO);
			confirmarEnvioEmail(notificacaoEmitidaIncidenteDTO.getIncidenteId());
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
			usuarioId = 3;
			User usuario = findUsuarioById(usuarioId);
			EmailModel emailModel = criarEmail(usuario);
			enviarEmail(emailModel);
			
			NotificacaoEnvioEmailDTO emissaoNotificacaoIncidenteDTO = new NotificacaoEnvioEmailDTO();
					
			emissaoNotificacaoIncidenteDTO.setIncidenteId(notificacaoEmitidaIncidenteDTO.getIncidenteId());
			emissaoNotificacaoIncidenteDTO.setDataHoraEnvioEmail(LocalDateTime.now());
			
			/*TODO: No momento foi utilizando a comunicaão via endpoint mas no futuro será mensageria
			try {
				emissaoNotificacaoEnvioEmail.emitirNotificacaoIncidente(emissaoNotificacaoIncidenteDTO);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}*/
			
			gravarNotificacao(usuario, notificacaoEmitidaIncidenteDTO);
			
		}
		
	}
	
	private void confirmarEnvioEmail(Integer incidenteId) {
		NotificacaoEnvioEmailDTO notificacaoEnvioEmailDTO = NotificacaoEnvioEmailDTO.builder().dataHoraEnvioEmail(LocalDateTime.now()).build();
		String confirmarEvioEmailAoEmitirNotificacaoIncidente = incidenteFeignClient.confirmarEnvioEmail(incidenteId,notificacaoEnvioEmailDTO).getBody();
		System.out.println("Confirmando o envio do email");
	}
	
	private void gravarNotificacao(User usuario,NotificacaoEmitidaIncidenteDTO notificacaoEmitidaIncidenteDTO) {
		Notificacao notificacao = Notificacao
									.builder()
									.nomeImpactado(usuario.getName())
									.email(usuario.getEmail())
									.incidenteId(notificacaoEmitidaIncidenteDTO.getIncidenteId())
									.incidenteTitulo(notificacaoEmitidaIncidenteDTO.getIncidenteTitulo())
									.tipoIncidente(notificacaoEmitidaIncidenteDTO.getTipoIncidente().getDescricao())
									.areaNome(notificacaoEmitidaIncidenteDTO.getAreaNome())
									.dataHoraNotificacao(LocalDateTime.now())
									.build();
		
		notificacaoService.criar(notificacao);
		
	}
	
	private EmailModel criarEmail(User usuario) {
		EmailModel emailModel = new EmailModel();
		
		emailModel.setOwnerRef(usuario.getName());
		emailModel.setOwnerRef(EMAIL_FROM);
		emailModel.setEmailTo(usuario.getEmail());
		emailModel.setText(MSG_MOCK_EMAIL);
		emailModel.setSubject(SUBJECT);
		
		return emailModel;
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
