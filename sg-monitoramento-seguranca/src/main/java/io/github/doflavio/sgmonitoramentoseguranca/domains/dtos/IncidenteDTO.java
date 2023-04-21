package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.time.LocalDateTime;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;

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
	
}
