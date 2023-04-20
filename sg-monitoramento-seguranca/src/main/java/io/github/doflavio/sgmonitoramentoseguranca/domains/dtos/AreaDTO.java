package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.io.Serializable;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AreaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//@NotBlank
	//@NotNull(message = "O campo NOME é obrigatório")
	private String nome;
	
	//@NotBlank(message = "O campo LATITUDE não deve estar em branco")
	//@NotNull(message = "O campo LATITUDE é obrigatório")
	private Long latitude;
	
	//@NotBlank(message = "O campo LONGITUDE não deve estar em branco")
	//@NotNull(message = "O campo LONGITUDE é obrigatório")
	private Long longitude;
	
	//@NotBlank
	//@NotNull(message = "O campo DESCRICAO é obrigatório")
	private String descricao;
	
	private StatusEnum status;
	
}
