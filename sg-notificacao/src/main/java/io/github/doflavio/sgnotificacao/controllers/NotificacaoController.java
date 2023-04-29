package io.github.doflavio.sgnotificacao.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.doflavio.sgnotificacao.models.Notificacao;
import io.github.doflavio.sgnotificacao.services.NotificacaoService;

@RestController
@RequestMapping(value = "/notificacoes")
public class NotificacaoController {

	@Autowired
	NotificacaoService notificacaolService;
	
	@GetMapping
	public ResponseEntity<List<Notificacao>>findAll() {
		List<Notificacao> notificacoes = notificacaolService.findAll();
		
		return ResponseEntity.ok().body(notificacoes);
	}
}
