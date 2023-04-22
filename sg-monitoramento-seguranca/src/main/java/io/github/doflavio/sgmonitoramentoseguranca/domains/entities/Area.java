package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AreaDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
public class Area implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@NotNull(message = "O campo NOME é obrigatório")
	private String nome;
	
	@NotNull(message = "O campo LATITUDE é obrigatório")
	private Long latitude;
	
	@NotNull(message = "O campo LONGITURE é obrigatório")
	private Long longitude;
	
	@NotBlank
	@NotNull(message = "O campo DESCRICAO é obrigatório")
	private String descricao;
	
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	private LocalDateTime dataHoraCadastro;
	
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime dataHoraDesativacao;
	
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime dataHoraexclusao;
	
	@JsonInclude(Include.NON_NULL)
	private String descricaoExclusao;
	
	public AreaDTO toAreaDTO() {
		return AreaDTO.builder()
				.id(id)
				.nome(nome)
		        .latitude(latitude)
		        .longitude(longitude)
		        .descricao(descricao)
		        .status(status)
				.build();
		
		
	}
}
