package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AtividadeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Atividade implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	public AtividadeDTO toAtividadeDTO() {
		return AtividadeDTO
				.builder()
				.id(id)
				.descricao(descricao)
				.build();
	}
}
