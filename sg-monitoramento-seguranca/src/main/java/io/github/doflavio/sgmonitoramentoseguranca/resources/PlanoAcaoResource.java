package io.github.doflavio.sgmonitoramentoseguranca.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcaoDTO;
import io.github.doflavio.sgmonitoramentoseguranca.services.PlanoAcaoService;

@RestController
@RequestMapping(value = "/plano-acao")
public class PlanoAcaoResource {

	@Autowired
	private PlanoAcaoService service;
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<PlanoAcaoDTO> findById(@PathVariable Integer id) {
		PlanoAcao obj = service.findById(id);
		return ResponseEntity.ok().body(obj.toPlanoAcaoDTO());
	}

	@GetMapping
	public ResponseEntity<List<PlanoAcaoDTO>> findAll() {
		List<PlanoAcao> list = service.findAll();
		List<PlanoAcaoDTO> listDTO = list.stream().map(obj -> obj.toPlanoAcaoDTO()).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
}
