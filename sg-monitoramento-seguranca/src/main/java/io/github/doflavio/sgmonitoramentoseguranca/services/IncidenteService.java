package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.NotificacaoIncidenteDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.IncidenteDTOAtualizacao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.IncidenteDTOCriacao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.NotificacaoEnvioEmailDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.User;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.exception.ObjectnotFoundException;
import io.github.doflavio.sgmonitoramentoseguranca.feignclients.UserFeignClient;
import io.github.doflavio.sgmonitoramentoseguranca.infra.EmissaoNotificacaoIncidentePublisher;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AtividadeRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.IncidenteRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.PlanoAcaoRepository;

@Service
public class IncidenteService {

	private static final Logger logger = LoggerFactory.getLogger(IncidenteService.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IncidenteRepository repository;
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@Autowired
	private PlanoAcaoRepository planoAcaoRepository;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private EmissaoNotificacaoIncidentePublisher emissaoNotificacaoIncidentePublisher;
	
	public Incidente findById(Integer id) {
		Optional<Incidente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Incidente não encontrado! Id: " + id));
	}

	public List<Incidente> findAll() {
		return repository.findAll();
	}

	
	public Incidente create(IncidenteDTOCriacao objDTO) {
		
		Area area = areaService.findById(objDTO.getArea());
		User usuario = findUsuarioById(objDTO.getUsuarioId());
		PlanoAcao planoAcao =  findPlanoAcaoById(objDTO.getPlanoAcao());
		
		//List<Integer> idsAtividades = objDTO.getAtividades().stream().map(a -> a.getAtividadeId()).collect(Collectors.toList());
		//List<Atividade> atividades =  atividadeRepository.findByIdIn(idsAtividades);
		
		Incidente incidente = criarIncidente(objDTO, area, null,planoAcao);
		incidente = repository.save(incidente);
		
		try {
			if (incidente.isExigeNotificacao()) {
				notificarIncidente(incidente);
				incidente.setNotificado(true);
			}
		} catch (Exception e) {
			logger.error("Não foi possível enviar notificação para o incidente :" + incidente.getId());
		}
		
		return repository.save(incidente);
		
	}
	
	public Incidente atualizar(IncidenteDTOAtualizacao objDTO) {
		
		Incidente incidente = findById(objDTO.getId());
		
		incidente = repository.save(incidente);
		incidente.setDescricao(objDTO.getDescricao());
		incidente.setObservacao(objDTO.getObservacao());
		incidente.setCategoriaRiscoIncidente(CategoriaRiscoIncidente.toEnum(objDTO.getCategoriaRiscoIncidente()));
		incidente.setStatusIncidente(StatusIncidente.toEnum(objDTO.getStatusIncidente()));
		
		
		
		try {
			if (incidente.isExigeNotificacao()) {
				notificarIncidente(incidente);
				incidente.setNotificado(true);			}
		} catch (Exception e) {
			logger.error("Não foi possível enviar notificação para o incidente :" + incidente.getId());
		}
		incidente.setNotificado(true);	
		return repository.save(incidente);
		
	}
	
	public Incidente confirmarEnvioEmail(Integer id, NotificacaoEnvioEmailDTO objDTO) {
		Incidente incidente = findById(id);
		incidente.setNotificado(true);
		incidente.setDataHoraNotificacao(objDTO.getDataHoraEnvioEmail());
		String observacao = incidente.getObservacao();
		observacao = observacao + "\n" + "Email de notificação enviado";
		incidente.setObservacao(null);
		incidente = repository.save(incidente);
		return incidente;
	}

	
	/*TODO: Olhar erro jackson
	com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.EmissaoNoificacaoIncidenteDTO["dataHoraIncidente"])
	at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
	*/
	private void notificarIncidente(Incidente incidente) {
		
		Area areaIncidente = areaService.findById(incidente.getArea().getId());
		List<Integer> idsUsuariosImpactados = areaIncidente.getImpactados().stream().map(i -> i.getUsuarioId()).collect(Collectors.toList());
		
		
		NotificacaoIncidenteDTO notificacao = NotificacaoIncidenteDTO
																		.builder()
																		.protocoloEmissao(UUID.randomUUID())
																		.incidenteId(incidente.getId())
																		.tipoIncidente(incidente.getTipoIncidente())
																		.dataHoraIncidente(incidente.getDataHoraIncidente())
																		//.dataHoraIncidente(incidente.getDataHoraIncidenteStr())
																		.areaNome(areaIncidente.getNome())
																		.idsUsuariosImpactados(idsUsuariosImpactados)
																		.build();
		try {
			emissaoNotificacaoIncidentePublisher.emitirNotificacaoIncidente(notificacao);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	private Incidente criarIncidente(IncidenteDTOCriacao objDTO, Area area, List<Atividade> atividades,PlanoAcao planoAcao){
		
		TipoIncidente tipoIncidente = TipoIncidente.toEnum(objDTO.getTipoIncidente());
		CategoriaRiscoIncidente categoriaRiscoIncidente = CategoriaRiscoIncidente.toEnum(objDTO.getCategoriaRiscoIncidente());
		StatusIncidente statusIncidente = StatusIncidente.toEnum(objDTO.getStatusIncidente());
		
		Incidente incidente = Incidente
				.builder()
				.titulo(objDTO.getTitulo())
				.descricao(objDTO.getDescricao())
				.area(area)
				.tipoIncidente(tipoIncidente)
				.categoriaRiscoIncidente(categoriaRiscoIncidente)
				.dataHoraIncidente(LocalDateTime.now())
				.dataHoraCadastro(LocalDateTime.now())
				
				//TODO: verificar como será a utilizacao do usuário - Ex:1 = sistema
				.usuarioId(objDTO.getUsuarioId())
				.exigeNotificacao(objDTO.isExigeNotificacao())
				.observacao(objDTO.getObservacao())
				.statusIncidente(statusIncidente)
				//.atividadesIncidente(new ArrayList<>())
				.planoAcao(planoAcao)
				.build();
		
		/* Atividade foi removido
		List<AtividadeIncidente> atividadesAtividadeIncidentes = new ArrayList<>();
		
		atividades.forEach( a -> {
			atividadesAtividadeIncidentes.add(criarAtividadeIncidente(a, incidente));
		});*/
		
		//TODO: Será removido, foi incluído o plano de ação
		//incidente.setAtividadesIncidente(atividadesAtividadeIncidentes);
		
		return incidente;
	}
	
	private AtividadeIncidente criarAtividadeIncidente(Atividade atividade, Incidente incidente){
		
		return AtividadeIncidente.builder().atividade(atividade).incidente(incidente)
				.statusAtividadeIncidente(StatusAtividadeIncidente.ABERTO).dataHoraCadastro(LocalDateTime.now())
				.build();
	}
	
	private User findUsuarioById(Integer id) {
		User user = userFeignClient.findById(id.longValue()).getBody();
		if(user == null) {
			logger.error("ID usuário indexistente: " + id);
			throw new IllegalArgumentException("Usuário not found");
		}
		return user;
	}
	
	private PlanoAcao findPlanoAcaoById(Integer id) {
		Optional<PlanoAcao> optPlanoAcao = planoAcaoRepository.findById(id);
		return optPlanoAcao.orElseThrow(() -> new ObjectnotFoundException("Incidente não encontrado! Id: " + id));
	}
	
}
