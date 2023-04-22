package io.github.doflavio.sgmonitoramentoseguranca.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;

public interface AreaRepository extends JpaRepository<Area, Integer>{
	
	@Query("Select a from Area a left join fetch a.impactados i where a.id =:id")
	Optional<Area> findAreaFetchImpactados(@Param("id") Integer id);

}
