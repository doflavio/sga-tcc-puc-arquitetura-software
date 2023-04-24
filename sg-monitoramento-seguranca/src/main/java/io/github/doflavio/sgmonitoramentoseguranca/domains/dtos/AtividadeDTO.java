package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
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
