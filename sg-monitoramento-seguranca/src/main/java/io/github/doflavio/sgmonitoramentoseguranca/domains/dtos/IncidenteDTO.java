package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcaoDTO;
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
@JsonInclude(Include.NON_NULL)
public class IncidenteDTO {
	
	//private final String PATTERN_DATE = "yyyy-MM-dd HH:mm:ss";
	
	private Integer id;
	private String titulo;
	private String descricao;
	@JsonProperty("area")
	private AreaDTO areaDTO;
	
	private String areaNome;
	
	private Integer tipoIncidente;
	
	private Integer categoriaRiscoIncidente;
	
	private Integer statusIncidente;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraIncidente;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraCadastro;
	private Integer usuarioId;
	private boolean exigeNotificacao;
	private boolean notificado;
	
	//@JsonInclude(Include.NON_NULL)
	private LocalDateTime dataHoraNotificacao; 
	private String observacao;
	private List<AtividadeIncidenteDTO> atividadesIncidente;
	@JsonProperty("PlanoAcao")
	private PlanoAcaoDTO planoAcaoDTO;
	private String planoAcaoNome;
	
}
