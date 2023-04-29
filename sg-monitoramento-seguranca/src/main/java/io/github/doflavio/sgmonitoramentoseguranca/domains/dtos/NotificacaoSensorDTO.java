package io.github.doflavio.sgmonitoramentoseguranca.domains.dtos;

public class NotificacaoSensorDTO{
	
	private Integer sensorId;
	private Integer valorReferencia;
	
	
	public NotificacaoSensorDTO(Integer sensorId, Integer valorReferencia) {
		super();
		this.sensorId = sensorId;
		this.valorReferencia = valorReferencia;
	}


	public NotificacaoSensorDTO() {
		super();
	}


	public Integer getSensorId() {
		return sensorId;
	}


	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}


	public Integer getValorReferencia() {
		return valorReferencia;
	}


	public void setValorReferencia(Integer valorReferencia) {
		this.valorReferencia = valorReferencia;
	}
	
	
	
	
}
