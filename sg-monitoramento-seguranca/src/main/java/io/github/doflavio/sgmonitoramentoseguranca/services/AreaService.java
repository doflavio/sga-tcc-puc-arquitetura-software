package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AreaDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Impactado;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.exception.ObjectnotFoundException;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AreaRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.ImpactadosRepository;
import jakarta.validation.Valid;

@Service
public class AreaService {

	@Autowired
	private AreaRepository repository;

	public Area findById(Integer id) {
		Optional<Area> obj = repository.findById(id);
		//Optional<Area> obj = repository.findAreaFetchImpactados(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Aárea não encontrada! Id: " + id));
	}

	public List<Area> findAll() {
		return repository.findAll();
	}

	public Area create(AreaDTO objDTO) {
		
		Area newObj = objDTO.toArea();
		newObj.setDataHoraCadastro(LocalDateTime.now());
		newObj.setStatus(StatusEnum.ATIVO);
		
		return repository.save(newObj);
	}
 
	public Area update(Integer id, @Valid AreaDTO objDTO) {
		objDTO.setId(id);
		Area oldObj = findById(id);
		objDTO.setStatus(oldObj.getStatus());
		oldObj = areaAtualizada(oldObj,objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		findById(id);
		
		//TODO: Verificar se áreas já associdado algum status
		//Verificar se ao invés de deletar, remover lógicamente = Status removido
		repository.deleteById(id);
	}
	
	public void removerLogicamente(Integer id) {
		Area objRemover =  findById(id);
		objRemover.setStatus(StatusEnum.REMOVIDO);
		objRemover.setDataHoraexclusao(LocalDateTime.now());
		objRemover.setDescricaoExclusao("REMOÇÃO LOGICA - TESTE");
		
		repository.save(objRemover);
	}
	
	public Area desativar(Integer id) {
		Area oldObj = findById(id);
		oldObj.setStatus(StatusEnum.DESATIVADO);
		oldObj.setDataHoraDesativacao(LocalDateTime.now());
		return repository.save(oldObj);
	}
	
	private Area areaAtualizada(Area area,AreaDTO objDTO) {
		area.setNome(objDTO.getNome());
		area.setDescricao(objDTO.getDescricao());
		area.setLatitude(objDTO.getLatitude());
		area.setLongitude(objDTO.getLongitude());
		area.setDescricao(objDTO.getDescricao());
		return area;
	}
	
}
