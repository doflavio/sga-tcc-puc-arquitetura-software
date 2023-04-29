package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class NotificacaoEnvioEmailDTO {
	private Integer incidenteId;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataHoraEnvioEmail;

	public NotificacaoEnvioEmailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NotificacaoEnvioEmailDTO(Integer incidenteId, LocalDateTime dataHoraEnvioEmail) {
		super();
		this.incidenteId = incidenteId;
		this.dataHoraEnvioEmail = dataHoraEnvioEmail;
	}

	public Integer getIncidenteId() {
		return incidenteId;
	}

	public void setIncidenteId(Integer incidenteId) {
		this.incidenteId = incidenteId;
	}

	public LocalDateTime getDataHoraEnvioEmail() {
		return dataHoraEnvioEmail;
	}

	public void setDataHoraEnvioEmail(LocalDateTime dataHoraEnvioEmail) {
		this.dataHoraEnvioEmail = dataHoraEnvioEmail;
	}
	
}
