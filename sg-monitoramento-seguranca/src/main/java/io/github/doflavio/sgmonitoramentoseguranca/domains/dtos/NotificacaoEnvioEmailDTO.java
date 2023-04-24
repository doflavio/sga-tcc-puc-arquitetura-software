package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoEnvioEmailDTO {
	private Integer incidenteId;
	//private LocalDateTime dataHoraIncidente;
	//Contorno momentâneo
	private String dataHoraEnvioEmail;
}
