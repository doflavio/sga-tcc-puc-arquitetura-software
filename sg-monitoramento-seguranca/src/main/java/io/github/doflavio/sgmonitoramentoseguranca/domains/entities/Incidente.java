package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AtividadeIncidenteDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.IncidenteDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "TB_INCIDENTE")
public class Incidente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	private String descricao;
	
	@NotNull
	@ManyToOne(cascade=CascadeType.PERSIST )
	@JoinColumn(name = "area_id", updatable = false)
	private Area area;
	
	@Enumerated(EnumType.STRING)
	private TipoIncidente tipoIncidente;
	
	@Enumerated(EnumType.STRING)
	private CategoriaRiscoIncidente categoriaRiscoIncidente;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraIncidente;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraCadastro;
	private Integer usuarioId;
	private boolean exigeNotificacao;
	private boolean notificado;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraNotificacao; 
	
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private StatusIncidente statusIncidente;
	
	
	/*TODO: Será removido, foi incluído o plano de ação
	@JsonManagedReference
	@NotNull
	@OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL, orphanRemoval = true)
	//private Queue<AtividadeIncidente> atividadesIncidente;
	private List<AtividadeIncidente> atividadesIncidente;
	*/
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "planoAcao_id")
	private PlanoAcao planoAcao;
	
	public String getDataHoraIncidenteStr() {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		 return this.dataHoraIncidente.format(formatter);
	}
	
	/*TODO: Será removido, foi incluído o plano de ação
	private List<AtividadeIncidenteDTO> listaAtividadeIncidenteDTO(){
		return this.atividadesIncidente.stream().map(i-> i.toAtividadeIncidenteDTO()).collect(Collectors.toList());
	}*/
	
	public IncidenteDTO toIncidenteDTO() {
		IncidenteDTO  incidenteDTO =  IncidenteDTO
				.builder()
				.id(id)
				.titulo(titulo)
				.descricao(descricao)
				.areaDTO(area.toAreaDTO())
				.tipoIncidente(tipoIncidente)
				.categoriaRiscoIncidente(categoriaRiscoIncidente)
				.statusIncidente(statusIncidente)
				.dataHoraIncidente(dataHoraIncidente)
				.exigeNotificacao(exigeNotificacao)
				.notificado(notificado)
				.dataHoraNotificacao(dataHoraNotificacao)
				//.atividadesIncidente(this.listaAtividadeIncidenteDTO())
				.planoAcaoDTO(planoAcao.toPlanoAcaoDTO())
				.observacao(observacao)
				.build();
		
		return incidenteDTO;
	}
	
	
}
