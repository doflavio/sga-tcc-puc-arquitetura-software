package io.github.doflavio.sgmonitoramentoseguranca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer>{

}
