package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ImpactadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer usuarioId;
	private StatusEnum status;
	private LocalDateTime dataHoraCadastro;
	private LocalDateTime dataHoraDesativacao;
	private LocalDateTime dataHoraexclusao;
	private String descricaoExclusao;

}
