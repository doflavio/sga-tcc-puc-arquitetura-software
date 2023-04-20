package io.github.doflavio.sgnotificacao.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgnotificacao.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
