package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgmonitoramentoseguranca.config.Mocks;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AreaRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AtividadeIncidenteRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AtividadeRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.IncidenteRepository;

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

	public void instanciaDB() {
		//inicilizarAreas();
		//inicializarAtividades();
		
		//inicializarAtividadesIncidentes();
		
		criarIncidentes();
		
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
				.atividadesIncidente(new ArrayList<>())
				//.atividadesIncidente(new LinkedList<>())
				.build();
		
		AtividadeIncidente ativ1Incidente1 = AtividadeIncidente.builder().atividade(ativ1).incidente(incidente1)
				.statusAtividadeIncidente(StatusAtividadeIncidente.ABERTO).dataHoraCadastro(LocalDateTime.now())
				.build();
		
		AtividadeIncidente ativ2Incidente1 = AtividadeIncidente.builder().atividade(ativ2).incidente(incidente1)
				.statusAtividadeIncidente(StatusAtividadeIncidente.ABERTO).dataHoraCadastro(LocalDateTime.now())
				.build();
		
		
		incidente1.getAtividadesIncidente().add(ativ1Incidente1);
		incidente1.getAtividadesIncidente().add(ativ2Incidente1);
		
		incidenteRepository.saveAll(Arrays.asList(incidente1));
		
	}
}
