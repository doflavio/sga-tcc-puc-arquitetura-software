package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.exception.ObjectnotFoundException;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.PlanoAcaoRepository;

@Service
public class PlanoAcaoService {

	@Autowired
	private PlanoAcaoRepository repository;

	public PlanoAcao findById(Integer id) {
		Optional<PlanoAcao> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Plano de Ação não encontrado! Id: " + id));
	}

	public List<PlanoAcao> findAll() {
		return repository.findAll();
	}
}
