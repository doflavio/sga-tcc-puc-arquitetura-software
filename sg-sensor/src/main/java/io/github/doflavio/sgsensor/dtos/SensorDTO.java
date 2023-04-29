package io.github.doflavio.sgsensor.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.doflavio.sgsensor.enums.StatusEnum;
import io.github.doflavio.sgsensor.models.Sensor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SensorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	private String descricao;
	private Integer areaId;
	private Long valorLimitePadrao;
	private Long valorLimiteAtencao; //--> Vai gerar um incidente de inspeção

	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraNotificacaoSensor; 
	
	private String observacao;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraCadastro;
	
	private Integer usuarioId;
	
	private StatusEnum status;
	
	public Sensor toSensor() {
		return Sensor
				.builder()
				.nome(nome)
				.descricao(descricao)
				.areaId(areaId)
				.valorLimiteAtencao(valorLimiteAtencao)
				.valorLimiteAtencao(valorLimiteAtencao)
				.build();
	}
	
}
