package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncidenteDTO {
	
	private Integer id;
	private String titulo;
	private String descricao;
	private Area area;
	private TipoIncidente tipoIncidente;
	private CategoriaRiscoIncidente categoriaRiscoIncidente;
	private StatusIncidente statusIncidente;
	private LocalDateTime dataHoraincidente;
	private LocalDateTime dataHoraCadastro;
	private Integer usuarioId;
	private boolean exigeNotificacao;
	private boolean notificado;
	
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime dataHoraNotificacao; 
	private String observacao;
	private List<AtividadeIncidenteDTO> atividadesIncidente;
	
}
