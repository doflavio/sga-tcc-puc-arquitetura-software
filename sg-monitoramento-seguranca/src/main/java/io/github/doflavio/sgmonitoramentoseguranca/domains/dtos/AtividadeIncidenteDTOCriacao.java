package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadeIncidente;
import jakarta.persistence.CascadeType;
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
public class AtividadeIncidenteDTOCriacao implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer atividadeId;
	private String observacao;

}