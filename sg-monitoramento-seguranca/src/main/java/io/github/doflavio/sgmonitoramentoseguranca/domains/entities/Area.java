package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
//@Table(name = "TB_AREA")
public class Area implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private Long latitude;
	private Long longitude;
	private String descricao;
	private LocalDateTime dataHoraCadastro;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	private LocalDateTime dataHoraDesativacao;
	
	private LocalDateTime dataHoraRemocao;
	private String descricaoRemocao;
}
