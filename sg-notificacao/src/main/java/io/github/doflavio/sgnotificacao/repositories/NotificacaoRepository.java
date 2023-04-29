package io.github.doflavio.sgnotificacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgnotificacao.models.Notificacao;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Integer> {
}
