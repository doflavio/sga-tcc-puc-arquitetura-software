package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AreaDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@JsonManagedReference
	//@NotNull
	//@OneToMany(mappedBy = "area",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(mappedBy = "area",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Impactado> impactados = new ArrayList<>();
	
	public AreaDTO toAreaDTO() {
		return AreaDTO.builder()
				.id(id)
				.nome(nome)
		        .latitude(latitude)
		        .longitude(longitude)
		        .descricao(descricao)
		        .status(status)
		        .impactados(impactados)
				.build();
		
		
	}
}
