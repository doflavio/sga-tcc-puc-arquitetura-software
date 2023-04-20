package io.github.doflavio.sgsensor.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgsensor.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
