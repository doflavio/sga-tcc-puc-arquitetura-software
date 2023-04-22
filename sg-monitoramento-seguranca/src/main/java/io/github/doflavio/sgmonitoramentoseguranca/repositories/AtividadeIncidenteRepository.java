package io.github.doflavio.sgmonitoramentoseguranca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.IncidenteAtividadeId;

public interface AtividadeIncidenteRepository extends JpaRepository<AtividadeIncidente, Integer>{

}
