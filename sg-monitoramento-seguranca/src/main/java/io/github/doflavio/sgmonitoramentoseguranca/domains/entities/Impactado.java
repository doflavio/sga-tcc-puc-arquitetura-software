package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
//@Table(name = "TB_AREA")
public class Impactado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer usuarioId;
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "area_id")
	private Area area;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	private LocalDateTime dataHoraCadastro;
	
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime dataHoraDesativacao;
	
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime dataHoraexclusao;
	
	@JsonInclude(Include.NON_NULL)
	private String descricaoExclusao;

}
