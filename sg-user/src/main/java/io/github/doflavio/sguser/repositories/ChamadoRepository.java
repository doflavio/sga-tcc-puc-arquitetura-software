package io.github.doflavio.sguser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sguser.domains.entities.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
