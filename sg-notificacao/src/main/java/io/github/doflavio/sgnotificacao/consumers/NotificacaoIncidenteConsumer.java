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

import io.github.doflavio.sgnotificacao.dtos.NotificacaoEnvioEmailDTO;
import io.github.doflavio.sgnotificacao.dtos.NotificacaoIncidenteDTO;
import io.github.doflavio.sgnotificacao.feignclients.IncidenteFeignClient;
import io.github.doflavio.sgnotificacao.feignclients.UserFeignClient;
import io.github.doflavio.sgnotificacao.infra.EmissaoNotificacaoEnvioEmailPublisher;
import io.github.doflavio.sgnotificacao.models.EmailModel;
import io.github.doflavio.sgnotificacao.models.Notificacao;
import io.github.doflavio.sgnotificacao.models.User;
import io.github.doflavio.sgnotificacao.services.EmailService;
import io.github.doflavio.sgnotificacao.services.NotificacaoService;

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
	private EmissaoNotificacaoEnvioEmailPublisher emissaoNotificacaoEnvioEmail;
	
	@Autowired
	private NotificacaoService notificacaoService;
	
	@RabbitListener(queues = "${mq.queues.notificacao.incidente}")
	public void receberNotificacaoIncidente(@Payload String payload){
		try {
			NotificacaoIncidenteDTO notificacaoEmitidaIncidenteDTO = convertIntoEmissaoNotificacaoIncidenteDTO(payload);
			notificar(notificacaoEmitidaIncidenteDTO);
			notificarEnvioEmailSucesso(notificacaoEmitidaIncidenteDTO.getIncidenteId());
		} catch (JsonProcessingException e) {
			System.out.println("Não foi possível consumir o payload: " + payload);
			System.out.println("Erro ao tentar consumir a fila: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	private NotificacaoIncidenteDTO convertIntoEmissaoNotificacaoIncidenteDTO(String payload) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(payload, NotificacaoIncidenteDTO.class);
	}
	
	private void notificar(NotificacaoIncidenteDTO notificacaoEmitidaIncidenteDTO) {
		
		for (Integer usuarioId : notificacaoEmitidaIncidenteDTO.getIdsUsuariosImpactados()) {
			User usuario = findUsuarioById(usuarioId);
			EmailModel emailModel = criarEmail(usuario);
			enviarEmail(emailModel);
			
			gravarNotificacao(usuario, notificacaoEmitidaIncidenteDTO);
			
		}
		
	}
	
	private void notificarEnvioEmailSucesso(Integer incidenteId) {
		boolean notificacaoEnvioEmailSucessoJaUtilizaMensageria = false;
		
		if(notificacaoEnvioEmailSucessoJaUtilizaMensageria) {
			notificarEnvioEmailSucessoViaMensageria(incidenteId);
		}else {
			notificarEnvioEmailSucessoViaEmail(incidenteId);
		}
		System.out.println("Email enviado, e confirmado o envio com sucesso");
		
	}

	private void notificarEnvioEmailSucessoViaEmail(Integer incidenteId) {
		NotificacaoEnvioEmailDTO notificacaoEnvioEmailDTO = NotificacaoEnvioEmailDTO.builder().dataHoraEnvioEmail(LocalDateTime.now()).build();
		String confirmarEvioEmailAoEmitirNotificacaoIncidente = incidenteFeignClient.confirmarEnvioEmail(incidenteId,notificacaoEnvioEmailDTO).getBody();
	}
	
	private void gravarNotificacao(User usuario,NotificacaoIncidenteDTO notificacaoEmitidaIncidenteDTO) {
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
        System.out.println("Email envidado com sucesso: (para) " + emailModel.getEmailTo());
    }
	
	//TODO: No momento foi utilizando a comunicação via endpoint mas no futuro será mensageria
	private void notificarEnvioEmailSucessoViaMensageria(Integer incidenteId) {
		NotificacaoEnvioEmailDTO notificacaoEnvioEmailDTO = new NotificacaoEnvioEmailDTO();
		
		notificacaoEnvioEmailDTO.setIncidenteId(incidenteId);
		notificacaoEnvioEmailDTO.setDataHoraEnvioEmail(LocalDateTime.now());
		
		try {
			emissaoNotificacaoEnvioEmail.emitirNotificacaoIncidente(notificacaoEnvioEmailDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.out.println("Não foi possível enviar a notificação do envio do email");
		}
	}
	
	
	//TODO: Este método foi incluído aqui para agilizar devido a entrega da POC
	private User findUsuarioById(Integer id) {
		User user = userFeignClient.findById(id.longValue()).getBody();
		if(user == null) {
			logger.error("ID usuário indexistente: " + id);
			throw new IllegalArgumentException("Usuário not found");
		}
		return user;
	}
	
}
