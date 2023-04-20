package io.github.doflavio.sgoauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgoauth.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}