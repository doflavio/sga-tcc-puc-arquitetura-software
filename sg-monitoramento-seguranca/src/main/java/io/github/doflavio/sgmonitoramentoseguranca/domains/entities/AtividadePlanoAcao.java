package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AtividadeIncidenteDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AtividadePlanoAcaoDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadePlanoAcao;
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
public class AtividadePlanoAcao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*@EmbeddedId
	private IncidenteAtividadeId id;*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "atividade_id", updatable = false)
	private Atividade atividade;
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "planoAcao_id")
	private PlanoAcao planoAcao;
	
	@Enumerated(EnumType.STRING)
	private StatusAtividadePlanoAcao statusAtividadePlanoAcao;
		
	private LocalDateTime dataHoraCadastro;
	private LocalDateTime dataHorarRealizacao;
	private String observacao;
	
	public AtividadePlanoAcaoDTO toAtividadePlanoAcaoDTO() {
		return AtividadePlanoAcaoDTO
				.builder()
				.id(id)
				.atividadeDTO(atividade.toAtividadeDTO())
				//.planoAcao(planoAcao)
				.statusAtividadePlanoAcao(statusAtividadePlanoAcao)
				.dataHorarRealizacao(dataHorarRealizacao)
				.observacao(observacao)
				.build();
	}
	
	/*	
	public AtividadeIncidenteDTO toAtividadeIncidenteDTO() {
		return AtividadeIncidenteDTO
				.builder()
				.id(id)
				.atividadeDTO(atividade.toAtividadeDTO())
				//.incidenteDTO(incidente.toIncidenteDTO())
				//Plano de ação
				.statusAtividadeIncidente(statusAtividadeIncidente)
				.dataHorarRealizacao(dataHorarRealizacao)
				.observacao(observacao)
				.build();
	}*/

}
