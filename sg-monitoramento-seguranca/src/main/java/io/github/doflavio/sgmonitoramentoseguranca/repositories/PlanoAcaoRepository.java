package io.github.doflavio.sgmonitoramentoseguranca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.IncidenteAtividadeId;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcao;

public interface PlanoAcaoRepository extends JpaRepository<PlanoAcao, Integer>{

}
