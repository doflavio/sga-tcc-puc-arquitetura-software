package io.github.doflavio.sgmonitoramentoseguranca.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AtividadeDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.services.AtividadeService;


@RestController
@RequestMapping(value = "/atividades")
public class AtividadeResource {

	@Autowired
	private AtividadeService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<AtividadeDTO> findById(@PathVariable Integer id) {
		Atividade obj = service.findById(id);
		return ResponseEntity.ok().body(obj.toAtividadeDTO());
	}

	@GetMapping
	public ResponseEntity<List<AtividadeDTO>> findAll() {
		List<Atividade> list = service.findAll();
		List<AtividadeDTO> listDTO = list.stream().map(obj -> obj.toAtividadeDTO()).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
