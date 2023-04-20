package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.time.LocalDateTime;

import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
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
//@Table(name = "TB_INCIDENTE")
public class Incidente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	private String descricao;
	private Area area;
	private TipoIncidente tipoIncidente;
	private CategoriaRiscoIncidente categoriaRiscoIncidente;
	private StatusIncidente statusIncidente;
	private LocalDateTime dataHoraincidente;
	private LocalDateTime dataHoraCadastro;
	private Integer usuarioId;
	
}
