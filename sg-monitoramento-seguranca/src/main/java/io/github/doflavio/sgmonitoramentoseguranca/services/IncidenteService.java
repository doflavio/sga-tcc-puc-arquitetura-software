package io.github.doflavio.sgmonitoramentoseguranca.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.IncidenteDTOCriacao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.User;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.exception.ObjectnotFoundException;
import io.github.doflavio.sgmonitoramentoseguranca.feignclients.UserFeignClient;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.AtividadeRepository;
import io.github.doflavio.sgmonitoramentoseguranca.repositories.IncidenteRepository;

@Service
public class IncidenteService {

	private static final Logger logger = LoggerFactory.getLogger(IncidenteService.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IncidenteRepository repository;
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public Incidente findById(Integer id) {
		Optional<Incidente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Incidente não encontrado! Id: " + id));
	}

	public List<Incidente> findAll() {
		return repository.findAll();
	}

	
	public Incidente create(IncidenteDTOCriacao objDTO) {
		
		Area area = areaService.findById(objDTO.getAreaId());
		
		User usuario = findUsuarioById(objDTO.getUsuarioId());
		
		List<Integer> idsAtividades = objDTO.getAtividades().stream().map(a -> a.getAtividadeId()).collect(Collectors.toList());
		List<Atividade> atividades =  atividadeRepository.findByIdIn(idsAtividades);
		
		Incidente incidente = criarIncidente(objDTO, area, atividades);
		
		return repository.save(incidente);
	}
	
	private Incidente criarIncidente(IncidenteDTOCriacao objDTO, Area area, List<Atividade> atividades){
		
		TipoIncidente tipoIncidente = TipoIncidente.toEnum(objDTO.getTipoIncidente());
		CategoriaRiscoIncidente categoriaRiscoIncidente = CategoriaRiscoIncidente.toEnum(objDTO.getCategoriaRiscoIncidente());
		
		
		Incidente incidente = Incidente
				.builder()
				.titulo(objDTO.getTitulo())
				.descricao(objDTO.getDescricao())
				.area(area)
				.tipoIncidente(tipoIncidente)
				.categoriaRiscoIncidente(categoriaRiscoIncidente)
				.dataHoraIncidente(LocalDateTime.now().minusHours(5))
				.dataHoraCadastro(LocalDateTime.now())
				
				//TODO: verificar como será a utilizacao do usuário - Ex:1 = sistema
				.usuarioId(objDTO.getUsuarioId())
				.exigeNotificacao(true)
				.observacao(objDTO.getObservacao())
				.statusIncidente(StatusIncidente.ABERTO)
				.atividadesIncidente(new ArrayList<>())
				.build();
		
		List<AtividadeIncidente> atividadesAtividadeIncidentes = new ArrayList<>();
		
		atividades.forEach( a -> {
			atividadesAtividadeIncidentes.add(criarAtividadeIncidente(a, incidente));
		});
		
		incidente.setAtividadesIncidente(atividadesAtividadeIncidentes);
		
		return incidente;
	}
	
	private static AtividadeIncidente criarAtividadeIncidente(Atividade atividade, Incidente incidente){
		
		return AtividadeIncidente.builder().atividade(atividade).incidente(incidente)
				.statusAtividadeIncidente(StatusAtividadeIncidente.ABERTO).dataHoraCadastro(LocalDateTime.now())
				.build();
	}
	
	public User findUsuarioById(Integer id) {
		User user = userFeignClient.findById(id.longValue()).getBody();
		if(user == null) {
			logger.error("ID usuário indexistente: " + id);
			throw new IllegalArgumentException("Usuário not found");
		}
		return user;
	}
	
	
	
	/*
	public Incidente update(Integer id, @Valid IncidenteDTO objDTO) {
		objDTO.setId(id);
		Incidente oldObj = findById(id);
		oldObj = pIncidenterea(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		findById(id);
		
		//TODO: Verificar se áres já associdado algum status
		//Verificar se ao invés de deletar, remover lógicamente = Status removido
		repository.deleteById(id);
	}
	
	public void removerLogicamente(Integer id) {
		Incidente objRemover =  findById(id);
		objRemover.setStatus(StatusEnum.REMOVIDO);
		objRemover.setDataHoraRemocao(LocalDateTime.now());
		objRemover.setDescricaoRemocao("REMOÇÃO LOGICA - TESTE");
		
		repository.save(objRemover);
	}
	
	public Incidente desativar(Integer id) {
		Incidente oldObj = findById(id);
		oldObj.setStatus(StatusEnum.DESATIVADO);
		oldObj.setDataHoraDesativacao(LocalDateTime.now());
		return repository.save(oldObj);
	}
	
	private Incidente pIncidenterea(IncidenteDTO objDTO) {
		return Incidente.builder()
				.nome(objDTO.getNome())
				.descricao(objDTO.getDescricao())
				.latitude(objDTO.getLatitude())
				.longitude(objDTO.getLongitude())
				.descricao(objDTO.getDescricao())
				.build();
	}*/
	
}
