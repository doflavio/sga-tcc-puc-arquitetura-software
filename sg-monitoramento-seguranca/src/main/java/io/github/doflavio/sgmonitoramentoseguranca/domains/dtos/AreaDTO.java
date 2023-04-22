package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Impactado;
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
	
	@NotBlank(message = "O campo NOME deve ser informado")
	@NotNull(message = "O campo NOME é obrigatório")
	private String nome;
	
	@NotNull(message = "O campo LATITUDE é obrigatório")
	private Long latitude;
	
	@NotNull(message = "O campo LONGITUDE é obrigatório")
	private Long longitude;
	
	@NotBlank(message = "O campo DESCRICAO deve ser informado")
	@NotNull(message = "O campo DESCRICAO é obrigatório")
	private String descricao;
	
	private StatusEnum status;
	
	private List<Impactado> impactados;
	
	public Area toArea() {
		return Area
				.builder()
				.id(id)
				.nome(nome)
				.latitude(latitude)
				.longitude(longitude)
				.descricao(descricao)
				.build();
	}
	
}
