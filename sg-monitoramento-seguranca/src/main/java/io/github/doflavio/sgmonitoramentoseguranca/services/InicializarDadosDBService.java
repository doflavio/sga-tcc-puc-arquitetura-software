package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AreaRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AtividadeRepository;

@Service
public class InicializarDadosDBService {

	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	

	public void instanciaDB() {
		inicilizarAreas();
		inicializarAtividades();
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
}
