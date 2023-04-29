package io.github.doflavio.sgsensor.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgsensor.dtos.SensorDTO;
import io.github.doflavio.sgsensor.enums.StatusEnum;
import io.github.doflavio.sgsensor.exception.ObjectnotFoundException;
import io.github.doflavio.sgsensor.models.Sensor;
import io.github.doflavio.sgsensor.repositories.SensorRepository;
import jakarta.validation.Valid;

@Service
public class SensorService {

	@Autowired
	private SensorRepository repository;

	public Sensor findById(Integer id) {
		Optional<Sensor> obj = repository.findById(id);
		//Optional<Sensor> obj = repository.findSensorFetchImpactados(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Aárea não encontrada! Id: " + id));
	}

	public List<Sensor> findAll() {
		return repository.findAll();
	}

	public Sensor create(SensorDTO objDTO) {
		
		Sensor newObj = objDTO.toSensor();
		newObj.setDataHoraCadastro(LocalDateTime.now());
		newObj.setStatus(StatusEnum.ATIVO);
		
		return repository.save(newObj);
	}
 
	public Sensor update(Integer id, @Valid SensorDTO objDTO) {
		objDTO.setId(id);
		Sensor oldObj = findById(id);
		
		oldObj = SensorAtualizada(oldObj,objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		findById(id);
		
		//TODO: Verificar se áreas já associdado algum status
		//Verificar se ao invés de deletar, remover lógicamente = Status removido
		repository.deleteById(id);
	}
	
	public void removerLogicamente(Integer id) {
		Sensor objRemover =  findById(id);
		objRemover.setStatus(StatusEnum.REMOVIDO);
		objRemover.setDataHoraexclusao(LocalDateTime.now());
		objRemover.setDescricaoExclusao("REMOÇÃO LOGICA - TESTE");
		
		repository.save(objRemover);
	}
	
	public Sensor desativar(Integer id) {
		Sensor oldObj = findById(id);
		oldObj.setStatus(StatusEnum.DESATIVADO);
		oldObj.setDataHoraDesativacao(LocalDateTime.now());
		return repository.save(oldObj);
	}
	
	private Sensor SensorAtualizada(Sensor Sensor,SensorDTO objDTO) {
		Sensor.setNome(objDTO.getNome());
		Sensor.setDescricao(objDTO.getDescricao());
		Sensor.setLatitude(objDTO.getLatitude());
		Sensor.setLongitude(objDTO.getLongitude());
		Sensor.setDescricao(objDTO.getDescricao());
		return Sensor;
	}
	
}
