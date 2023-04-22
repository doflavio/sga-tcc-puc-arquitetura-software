package io.github.doflavio.sgnotificacao.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.doflavio.sgnotificacao.enums.TipoIncidente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoEmitidaIncidenteDTO {
	private UUID protocoloEmissao;
	private Integer incidenteId;
	//private TipoIncidente tipoIncidente;
	private String tipoIncidente;
	
	//private LocalDateTime dataHoraIncidente;
	//Contorno momentâneo
	private String dataHoraIncidente;
	
	private String areaNome;
	private List<Integer> idsUsuariosImpactados = new ArrayList<>();
	
	public TipoIncidente getTipoIncidente() {
		if(this.tipoIncidente != null && !this.tipoIncidente.isBlank()) {
			return TipoIncidente.toEnum(tipoIncidente);
		}
		throw new IllegalArgumentException("TipoIncidente inválido");
	}
}
