package io.github.doflavio.sgmonitoramentoseguranca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Impactado;

public interface ImpactadosRepository extends JpaRepository<Impactado, Integer>{
	
	List<Impactado> findByArea(Integer areaId);

}
