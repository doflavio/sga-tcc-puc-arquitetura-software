package io.github.doflavio.sgnotificacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgnotificacao.models.Notificacao;
import io.github.doflavio.sgnotificacao.repositories.NotificacaoRepository;

@Service
public class NotificacaoService {

    @Autowired
    NotificacaoRepository notificacaoRepository;

    public List<Notificacao> findAll() {
		return notificacaoRepository.findAll();
	}
    
    
    public Notificacao criar(Notificacao notificacao) {
		return notificacaoRepository.save(notificacao);
	}
}
