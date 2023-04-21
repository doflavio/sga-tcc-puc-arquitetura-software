package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtividadeDTO {
	private Integer id;
	private String descricao;
	
	public Atividade toAtividade() {
		return Atividade
				.builder()
				.id(id)
				.descricao(descricao)
				.build();
	}
}
