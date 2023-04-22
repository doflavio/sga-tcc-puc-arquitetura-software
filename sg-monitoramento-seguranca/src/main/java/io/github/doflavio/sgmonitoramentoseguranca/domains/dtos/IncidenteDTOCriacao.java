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
public class IncidenteDTOCriacao {
	
	private Integer id;
	private String titulo;
	private String descricao;
	private Integer areaId;
	private Integer tipoIncidente;
	private Integer categoriaRiscoIncidente;
	private LocalDateTime dataHoraincidente;
	private Integer usuarioId;
	private boolean exigeNotificacao;
	private boolean notificado;
	private String observacao;
	private List<AtividadeIncidenteDTOCriacao> atividades;
	
}
