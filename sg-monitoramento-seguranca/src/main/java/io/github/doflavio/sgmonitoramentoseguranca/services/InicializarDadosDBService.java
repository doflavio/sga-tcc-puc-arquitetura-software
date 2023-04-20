package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AreaRepository;

@Service
public class InicializarDadosDBService {

	@Autowired
	private AreaRepository areaRepository;
	

	public void instanciaDB() {

		Area ar1 = Area.builder()
					.id(1)
					.nome("Area 1")
					.latitude(10L)
					.longitude(20L)
					.descricao("Descrição area 1")
					.dataHoraCadastro(LocalDateTime.now())
					.status(StatusEnum.ATIVO)
					.build();
		
		Area ar2 = Area.builder()
				.id(2)
				.nome("Area 2")
				.latitude(30L)
				.longitude(40L)
				.descricao("Descrição area 2")
				.dataHoraCadastro(LocalDateTime.now())
				.build();
		
		
		
		areaRepository.saveAll(Arrays.asList(ar1,ar2));
	}
}
