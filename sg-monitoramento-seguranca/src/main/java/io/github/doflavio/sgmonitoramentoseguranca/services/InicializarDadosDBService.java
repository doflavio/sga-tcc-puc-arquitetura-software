package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgmonitoramentoseguranca.config.Mocks;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Impactado;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Sensor;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AreaRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AtividadeIncidenteRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AtividadeRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.ImpactadosRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.IncidenteRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.PlanoAcaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@Service
public class InicializarDadosDBService {

	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@Autowired
	private AtividadeIncidenteRepository atividadeIncidenteRepository;
	
	@Autowired
	private IncidenteRepository incidenteRepository;
	
	@Autowired
	private ImpactadosRepository impactadosRepository;
	
	@Autowired
	private PlanoAcaoRepository planoAcaoRepository;

	public void instanciaDB() {
		//inicilizarAreas();
		//inicializarAtividades();
		
		//inicializarAtividadesIncidentes();
		
		//criandoAreas();
		//criandoAtividades();
		criandoPlanoAcao1();
		criarIncidente1Area4();
		
	}

	private void criandoAreas() {
		List<Area> areas = Mocks.criarAreas();
		areas = areaRepository.saveAll(areas);
		criandoImpactadosAreas(areas);
		//criandoSensoresAreas(areas);
		criandoSensoresArea1(areas.get(0));
	}

	private void criandoImpactadosAreas(List<Area> areas) {
		int usuarioId=0;
		for (Area area : areas) {
			if(area.getId().equals(2)) {
				usuarioId = 1;
			}if(area.getId().equals(2)) {
				usuarioId = 2;
			}else {
				usuarioId = 3;
			}
			
			Impactado imp = criarImpactadoArea(usuarioId,area);
			area.getImpactados().add(imp);
			//impactadosRepository.save(imp);
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
	
	private void criandoSensoresAreas(List<Area> areas) {
		for (Area area : areas) {
			if (area.getId().equals(1)) {
				Sensor sensor1Area1 = criarSensor1Area1(area);
				Sensor sensor2Area1 = criarSensor2Area1(area);
				area.getSensores().add(sensor1Area1);
				area.getSensores().add(sensor2Area1);
			}

		}
		areaRepository.saveAll(areas);

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
		String NOME_AREA_4 = "Area 4";
		Long latitude = -205171L;
		Long longitude = -43700L;
		String descricaoArea = "Descrição área 4 ";
		
		Area area4 = Mocks.criarArea(NOME_AREA_4, latitude, longitude, descricaoArea);
		
		//Impactados
		Impactado imp1 = criarImpactadoArea(1,area4);
		Impactado imp2 = criarImpactadoArea(2,area4);
		area4.getImpactados().add(imp1);
		area4.getImpactados().add(imp2);
		
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
				
		List<AtividadeIncidente> atividadesAtividadeIncidentes = new ArrayList<>();
		
		atividades.forEach( atv -> {
			atividadesAtividadeIncidentes.add(Mocks.criarAtividadeIncidente(atv, incidente));
		});
		
		//TODO: Será removido, foi incluído o plano de ação
		//incidente.setAtividadesIncidente(atividadesAtividadeIncidentes);

		Optional<PlanoAcao> optPlanoAcao1 = planoAcaoRepository.findById(1);
		incidente.setPlanoAcao(optPlanoAcao1.get());
		
		incidenteRepository.save(incidente);
	}
	
	
	

	private void criarIncidentes() {
		List<Incidente> incidentes = Mocks.incidentes();
		incidenteRepository.saveAll(incidentes);
	}

	private void inicilizarAreas() {
		Area ar1 = Area.builder().id(1).nome("Area 1").latitude(10L).longitude(20L).descricao("Descrição area 1")
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).build();

		Area ar2 = Area.builder().id(2).nome("Area 2").latitude(30L).longitude(40L).descricao("Descrição area 2")
				.dataHoraCadastro(LocalDateTime.now()).build();

		areaRepository.saveAll(Arrays.asList(ar1, ar2));
	}
	
	private void inicializarAtividades() {
		Atividade atv1 = Atividade.builder().id(1).descricao("Atividade 1") .build();
		Atividade atv2 = Atividade.builder().id(2).descricao("Atividade 2") .build();
		Atividade atv3 = Atividade.builder().id(3).descricao("Atividade 3") .build();
		Atividade atv4 = Atividade.builder().id(4).descricao("Atividade 4") .build();
		Atividade atv5 = Atividade.builder().id(5).descricao("Atividade 5") .build();
		Atividade atv6 = Atividade.builder().id(6).descricao("Atividade 6") .build();
		
		atividadeRepository.saveAll(Arrays.asList(atv1,atv2,atv3,atv4,atv5,atv6));
	}
	
	
	private void inicializarAtividadesIncidentes() {
		
		Area area1 = Area.builder().nome("Area 1").latitude(10L).longitude(20L).descricao("Descrição area 1")
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).build();

		Atividade ativ1 = Atividade.builder().descricao("Atividade 1") .build();
		Atividade ativ2 = Atividade.builder().descricao("Atividade 2") .build();
		
		Incidente incidente1 = Incidente
				.builder()
				.titulo("Título Incidente 1")
				.descricao("Descricao Incidente 1")
				.area(area1)
				.tipoIncidente(TipoIncidente.VEICULO_QUEBRADO)
				.categoriaRiscoIncidente(CategoriaRiscoIncidente.BAIXO)
				.dataHoraIncidente(LocalDateTime.now().minusHours(5))
				.dataHoraCadastro(LocalDateTime.now())
				
				//TODO: verificar como será a utilizacao do usuário - Ex:1 = sistema
				.usuarioId(1)
				.exigeNotificacao(true)
				.observacao("Observação para atenção")
				.statusIncidente(StatusIncidente.ABERTO)
				//.atividadesIncidente(new ArrayList<>())
				//.atividadesIncidente(new LinkedList<>())
				.build();
		
		AtividadeIncidente ativ1Incidente1 = AtividadeIncidente.builder().atividade(ativ1).incidente(incidente1)
				.statusAtividadeIncidente(StatusAtividadeIncidente.ABERTO).dataHoraCadastro(LocalDateTime.now())
				.build();
		
		AtividadeIncidente ativ2Incidente1 = AtividadeIncidente.builder().atividade(ativ2).incidente(incidente1)
				.statusAtividadeIncidente(StatusAtividadeIncidente.ABERTO).dataHoraCadastro(LocalDateTime.now())
				.build();
		
		//TODO: Será removido, foi incluído o plano de ação
		//incidente1.getAtividadesIncidente().add(ativ1Incidente1);
		//incidente1.getAtividadesIncidente().add(ativ2Incidente1);
		
		incidenteRepository.saveAll(Arrays.asList(incidente1));
		
	}
}
