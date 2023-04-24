package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataHoraIncidente;
	
	//Contorno momentâneo
	//private String dataHoraIncidente;
	
	private String areaNome;
	private List<Integer> idsUsuariosImpactados = new ArrayList<>();
	
}
//TODO: Jackson
//https://learnersbucket.com/examples/java/serialize-deserialize-java-8-java-time-with-jackson-json-mapper/
