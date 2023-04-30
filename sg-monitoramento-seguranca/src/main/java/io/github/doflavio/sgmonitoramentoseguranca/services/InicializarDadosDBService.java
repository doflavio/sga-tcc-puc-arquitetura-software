package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.doflavio.sgmonitoramentoseguranca.config.Mocks;
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.NotificacaoIncidenteDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Impactado;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Sensor;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.infra.EmissaoNotificacaoIncidentePublisher;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AreaRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AtividadeRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.IncidenteRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.PlanoAcaoRepository;

@Service
public class InicializarDadosDBService {
	
	private static final Logger logger = LoggerFactory.getLogger(IncidenteService.class);

	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@Autowired
	private IncidenteRepository incidenteRepository;
	
	@Autowired
	private PlanoAcaoRepository planoAcaoRepository;
	
	@Autowired
	private EmissaoNotificacaoIncidentePublisher emissaoNotificacaoIncidentePublisher;

	public void instanciaDB() {
		criandoAreas();
		criandoAtividades();
		criandoPlanoAcao1();
		criarIncidente1Area4();
	}

	private void criandoAreas() {
		List<Area> areas = Mocks.criarAreas();
		areas = areaRepository.saveAll(areas);
		criandoImpactadosAreas(areas);
		criandoSensoresArea1(areas.get(0));
	}

	private void criandoImpactadosAreas(List<Area> areas) {
		int usuarioId=0;
		for (Area area : areas) {
			
			/*
			if(area.getId().equals(2)) {
				usuarioId = 1;
			}if(area.getId().equals(2)) {
				usuarioId = 2;
			}else {
				usuarioId = 3;
			}
			
			//TODO: Verificar usuarioId
			usuarioId = 1;
			Impactado imp = criarImpactadoArea(usuarioId,area);
			area.getImpactados().add(imp);
			*/

			
			
			Impactado imp1 = criarImpactadoArea(1,area);
			Impactado imp2 = criarImpactadoArea(2,area);
			Impactado imp3 = criarImpactadoArea(3,area);
			Impactado imp4 = criarImpactadoArea(4,area);
			Impactado imp5 = criarImpactadoArea(5,area);
			
			if(area.getId().equals(1)) {
				area.getImpactados().add(imp1);
				area.getImpactados().add(imp3);
			}
			else if(area.getId().equals(2)) {
				area.getImpactados().add(imp2);
				area.getImpactados().add(imp3);
				area.getImpactados().add(imp4);
			}
			else {
				area.getImpactados().add(imp3);
				area.getImpactados().add(imp3);
				area.getImpactados().add(imp4);
			}
			
		}
		areaRepository.saveAll(areas);
	}
	
	private static Impactado criarImpactadoArea(int usuarioId,Area area){
		Impactado impactado = Impactado
				.builder()
				.usuarioId(usuarioId)
				.area(area)
				.dataHoraCadastro(LocalDateTime.now())
				.status(StatusEnum.ATIVO)
				.build();
		
		return impactado;
	}
	
	private void criandoSensoresArea1(Area area1) {
		
		Sensor sensor1Area1 = criarSensor1Area1(area1);
		Sensor sensor2Area1 = criarSensor2Area1(area1);
		area1.getSensores().add(sensor1Area1);
		area1.getSensores().add(sensor2Area1);

		areaRepository.save(area1);
	}
	
	private Sensor criarSensor1Area1(Area area1) {
		String nome = "Nível D'agua";
		String descricao = "Sensor para nível d'agua";
		Long valorLimitePadrao = 600L;
		Long valorLimiteAtencao = 700L;
		String observacao = "Observação ok";

		return Mocks.criandoSensorArea(nome, descricao, valorLimitePadrao, valorLimiteAtencao, observacao, area1);
	}
	
	private Sensor criarSensor2Area1(Area area1) {
		String nome = "Vibração";
		String descricao = "Sensor para medir a vibração";
		Long valorLimitePadrao = 900L;
		Long valorLimiteAtencao = 1000L;
		String observacao = "Observação ok";

		return Mocks.criandoSensorArea(nome, descricao, valorLimitePadrao, valorLimiteAtencao, observacao, area1);
	}
	
	private void criandoAtividades() {
		List<Atividade> atividades = Mocks.criarAtividades();
		atividadeRepository.saveAll(atividades);
	}
	
	/*--------------------------------------------
	--            PLANO DE AÇÃO                     -
	--------------------------------------------*/
	public void criandoPlanoAcao1() {
		PlanoAcao planoAcao1 = Mocks.criarPlanoAcao1();
		
		planoAcaoRepository.save(planoAcao1);
	}
	
	
	/*--------------------------------------------
	--            INCIDENTES                     -
	--------------------------------------------*/
	private void criarIncidente1Area4() {
		
		//Dados Área
		String NOME_AREA_4 = "Area F4";
		Long latitude = -205171L;
		Long longitude = -43700L;
		String descricaoArea = "Descrição Área F4B ";
		
		Area area4 = Mocks.criarArea(NOME_AREA_4, latitude, longitude, descricaoArea);
		
		//Impactados
		Impactado imp3 = criarImpactadoArea(3,area4);
		Impactado imp4 = criarImpactadoArea(4,area4);
		Impactado imp5 = criarImpactadoArea(5,area4);
	
		
		area4.getImpactados().add(imp3);
		area4.getImpactados().add(imp4);
		area4.getImpactados().add(imp5);
		
		//Sensores
		Sensor sensor1Area1 = criarSensor1Area1(area4);
		Sensor sensor2Area1 = criarSensor2Area1(area4);
		area4.getSensores().add(sensor1Area1);
		area4.getSensores().add(sensor2Area1);
		
		//Incidentes
		//Dados Incidentes
		String titulo = "Incidente para evacuação de área ";
		String descricaoIncidente = "Deve ser realizada a evacuação da área"; 
		TipoIncidente tipoIncidente = TipoIncidente.EVACUACAO;
		CategoriaRiscoIncidente categoriaRiscoIncidente = CategoriaRiscoIncidente.MAXIMO; 
		LocalDateTime dataHoraIncidente = LocalDateTime.now().minusHours(5);
		boolean exigeNotificacao = true; 
		boolean notificado = false;
		LocalDateTime dataHoraNotificacao = null; 
		String observacaoIncidente = "Observacao do Incidente"; 
		
		Incidente incidente = Mocks.criarIncidenteComAreaSemAtividades(
				titulo,
				descricaoIncidente,
				tipoIncidente,
				categoriaRiscoIncidente,
				dataHoraIncidente,
				exigeNotificacao,
				notificado,
				dataHoraNotificacao,
				observacaoIncidente,
				area4);
		
		//-- atividades
		String DescricaoAtividade = "Alerta de sergurança para evacuar a região. AVISAR autoridades";
				Atividade atividade1 = Atividade.builder().descricao(DescricaoAtividade).build();
		List<Atividade> atividades = new ArrayList<>();
		atividades.add(atividade1);

		Optional<PlanoAcao> optPlanoAcao1 = planoAcaoRepository.findById(1);
		incidente.setPlanoAcao(optPlanoAcao1.get());
		
		incidente = incidenteRepository.save(incidente);
		
		try {
			notificarIncidente(incidente);
		} catch (Exception e) {
			logger.error("Não foi possível enviar notificação para o incidente :" + incidente.getId());
		}
		
	}
	
	private void notificarIncidente(Incidente incidente) {
		
		Optional<Area> areaIncidenteOpt = areaRepository.findAreaFetchImpactados(incidente.getArea().getId());
		Area areaIncidente = areaIncidenteOpt.get();
		List<Integer> idsUsuariosImpactados = areaIncidente.getImpactados().stream().map(i -> i.getUsuarioId()).collect(Collectors.toList());
		
		//List<Integer> idsUsuariosImpactados = Arrays.asList(3);
		
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
}
