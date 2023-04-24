package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AtividadePlanoAcaoDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadePlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusPlanoAcao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PlanoAcaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private List<AtividadePlanoAcaoDTO> atividadesPlanoAcao;
	
	private String titulo;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	private StatusPlanoAcao statusPlanoAcao;
	private LocalDateTime dataHorarFinalizacao;
	private String observacao;
	private Integer usuarioId;
	private LocalDateTime dataHoraCadastro;
	
}
