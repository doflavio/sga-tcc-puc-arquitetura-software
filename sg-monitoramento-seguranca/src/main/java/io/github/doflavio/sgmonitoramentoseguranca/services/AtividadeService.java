package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AtividadeDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.exception.ObjectnotFoundException;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AtividadeRepository;
import jakarta.validation.Valid;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository repository;

	public Atividade findById(Integer id) {
		Optional<Atividade> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Atividade> findAll() {
		return repository.findAll();
	}

	public Atividade create(AtividadeDTO objDTO) {
		Atividade newObj = objDTO.toAtividade();
		return repository.save(newObj);
	}
 
	public Atividade update(Integer id, @Valid AtividadeDTO objDTO) {
		objDTO.setId(id);
		Atividade oldObj = findById(id);
		oldObj = objDTO.toAtividade();
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
}
