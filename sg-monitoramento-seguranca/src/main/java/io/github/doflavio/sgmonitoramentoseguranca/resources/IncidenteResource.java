package io.github.doflavio.sgmonitoramentoseguranca.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.IncidenteDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.IncidenteDTOAtualizacao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.IncidenteDTOCriacao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.NotificacaoEnvioEmailDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;
import io.github.doflavio.sgmonitoramentoseguranca.services.IncidenteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/incidentes")
public class IncidenteResource {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IncidenteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<IncidenteDTO> findById(@PathVariable Integer id) {
		Incidente obj = service.findById(id);
		//return ResponseEntity.ok().body(modelMapper.map(obj, IncidenteDTOCriacao.class));
		
		//return ResponseEntity.ok().body(modelMapper.map(obj, IncidenteDTO.class));
		return ResponseEntity.ok().body(obj.toIncidenteDTO());
	}

	@GetMapping
	public ResponseEntity<List<IncidenteDTO>> findAll() {
		List<Incidente> list = service.findAll();
		//List<IncidenteDTO> listDTO = list.stream().map(obj -> modelMapper.map(obj, IncidenteDTO.class)).collect(Collectors.toList());
		List<IncidenteDTO> listDTO = list.stream().map(obj -> obj.toIncidenteDTO()).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<IncidenteDTO> create(@Valid @RequestBody IncidenteDTOCriacao objDTO) {
		Incidente newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<IncidenteDTO> update(@PathVariable Integer id,@Valid @RequestBody IncidenteDTOAtualizacao objDTO) {
		objDTO.setId(id);
		Incidente obj = service.atualizar(objDTO);
		return ResponseEntity.ok().body(obj.toIncidenteDTO());
	}
	
	/* TODO: No futuro será utilizado mensageria*/
	@PutMapping(value = "/{id}/envio-email")
	public ResponseEntity<String> confirmarEnvioEmail(@PathVariable Integer id, @RequestBody NotificacaoEnvioEmailDTO objDTO) {
		Incidente obj = service.confirmarEnvioEmail(id,objDTO);
		return ResponseEntity.noContent().build();
	}
	

}
