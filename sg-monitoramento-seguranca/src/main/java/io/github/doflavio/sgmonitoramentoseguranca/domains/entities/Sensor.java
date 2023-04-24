package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.SensorDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
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
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "area_id")
	private Area area;
	
	private Long valorLimitePadrao;
	
	private Long valorLimiteAtencao; //--> Vai gerar um incidente de inspeção
	
	//private Long valorLimiteAlerta; // ??? Precisa pois a partir daqui é correria
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraNotificacaoSensor; 
	
	private String observacao;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraCadastro;
	private Integer usuarioId;
	
	public SensorDTO toSensorDTO() {
		return SensorDTO
				.builder()
				.id(id).nome(nome)
				.descricao(descricao)
				.valorLimiteAtencao(valorLimiteAtencao)
				.valorLimiteAtencao(valorLimiteAtencao)
				.observacao(observacao)
				.build();
	}
	
	
	
}
