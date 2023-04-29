package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcaoDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcaoDTOCriacao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
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
	private Integer area;
	private Integer tipoIncidente;
	private Integer categoriaRiscoIncidente;
	private Integer statusIncidente;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraincidente;
	private Integer usuarioId;
	private boolean exigeNotificacao;
	private boolean notificado;
	private String observacao;
	//private List<AtividadeIncidenteDTOCriacao> atividades;
	//@JsonProperty("PlanoAcao")
	//private PlanoAcaoDTOCriacao planoAcao;
	private Integer planoAcao;
	
}
