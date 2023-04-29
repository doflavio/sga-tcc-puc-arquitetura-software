package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncidenteDTOAtualizacao {
	
	private Integer id;
	private String descricao;
	private Integer categoriaRiscoIncidente;
	private Integer statusIncidente;
	private String observacao;
		
}
