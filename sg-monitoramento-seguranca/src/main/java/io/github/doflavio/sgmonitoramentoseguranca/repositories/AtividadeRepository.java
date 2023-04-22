package io.github.doflavio.sgmonitoramentoseguranca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer>{

	List<Atividade> findByIdIn(List<Integer> ids);
}
