package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadeIncidente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class AtividadeIncidente implements Serializable{
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
	@JoinColumn(name = "incidente_id")
	private Incidente incidente;
	
	@Enumerated(EnumType.STRING)
	private StatusAtividadeIncidente statusAtividadeIncidente;
		
	private LocalDateTime dataHoraCadastro;
	private LocalDateTime dataHorarRealizacao;
	private String observacao;

}
