package io.github.doflavio.sgsensor.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.github.doflavio.sgsensor.dtos.SensorDTO;
import io.github.doflavio.sgsensor.enums.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "TB_SENSOR")
public class Sensor implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String descricao;
	
	private Integer areaId;
	
	private Long valorLimitePadrao;
	
	private Long valorLimiteAtencao; //--> Vai gerar um incidente de inspeção
	
	//private Long valorLimiteAlerta; // ??? Precisa pois a partir daqui é correria
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraNotificacaoSensor; 
	
	private String observacao;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraCadastro;
	private Integer usuarioId;
	
	private StatusEnum status;
	
	public SensorDTO toSensorDTO() {
		return SensorDTO
				.builder()
				.id(id)
				.nome(nome)
				.descricao(descricao)
				.valorLimiteAtencao(valorLimiteAtencao)
				.valorLimiteAtencao(valorLimiteAtencao)
				.observacao(observacao)
				.status(status)
				.areaId(areaId)
				.build();
	}
	
	
	
}