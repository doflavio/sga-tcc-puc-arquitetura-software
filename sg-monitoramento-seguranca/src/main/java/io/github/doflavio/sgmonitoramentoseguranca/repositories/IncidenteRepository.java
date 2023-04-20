package io.github.doflavio.sgmonitoramentoseguranca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;

public interface IncidenteRepository extends JpaRepository<Incidente, Integer>{

}
