package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
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
public class ImpactadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer usuarioId;
	private StatusEnum status;
	private LocalDateTime dataHoraCadastro;
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime dataHoraDesativacao;
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime dataHoraexclusao;
	@JsonInclude(Include.NON_NULL)
	private String descricaoExclusao;

}
