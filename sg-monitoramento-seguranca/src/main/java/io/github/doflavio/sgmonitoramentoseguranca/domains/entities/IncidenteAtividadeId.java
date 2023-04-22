package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class IncidenteAtividadeId {
	
	@Column(name = "incidente_id")
	private Integer incidenteId;
	
	@Column(name = "atividade_id")
	private Integer atividadeId;
	

}
