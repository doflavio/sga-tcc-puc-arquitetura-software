package com.marcar.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcar.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{

}
