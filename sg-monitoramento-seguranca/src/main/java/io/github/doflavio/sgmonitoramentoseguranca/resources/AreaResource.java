package io.github.doflavio.sgmonitoramentoseguranca.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AreaDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.services.AreaService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/areas")
public class AreaResource {

	@Autowired
	private AreaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<AreaDTO> findById(@PathVariable Integer id) {
		Area obj = service.findById(id);
		return ResponseEntity.ok().body(parseArea(obj));
	}

	@GetMapping
	public ResponseEntity<List<AreaDTO>> findAll() {
		List<Area> list = service.findAll();
		List<AreaDTO> listDTO = list.stream().map(obj -> parseArea(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<AreaDTO> create(@Valid @RequestBody AreaDTO objDTO) {
		Area newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<AreaDTO> update(@PathVariable Integer id, @Valid @RequestBody AreaDTO objDTO) {
		Area obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(parseArea(obj));
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AreaDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<AreaDTO> desativar(@PathVariable Integer id){
		service.desativar(id);
		return ResponseEntity.noContent().build();
	}
	
	public AreaDTO parseArea(Area area) {
		return AreaDTO.builder()
				.id(area.getId())
				.nome(area.getNome())
		        .latitude(area.getLatitude())
		        .longitude(area.getLongitude())
		        .descricao(area.getDescricao())
		        .status(area.getStatus())
				.build();
		
		
	}
	
}
