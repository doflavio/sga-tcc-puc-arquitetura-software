package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Impactado;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmissaoNotificacaoIncidenteDTO {
	private UUID protocoloEmissao;
	private Integer incidenteId;
	private TipoIncidente tipoIncidente;
	
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	//private LocalDateTime dataHoraIncidente;
	
	//Contorno momentâneo
	private String dataHoraIncidente;
	
	private String areaNome;
	private List<Integer> idsUsuariosImpactados = new ArrayList<>();
	
}
