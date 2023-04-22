package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
//@Table(name = "TB_INCIDENTE")
public class Incidente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	private String descricao;
	
	@ManyToOne(cascade=CascadeType.PERSIST )
	@JoinColumn(name = "area_id", insertable = false, updatable = false)
	private Area area;
	
	@Enumerated(EnumType.STRING)
	private TipoIncidente tipoIncidente;
	
	@Enumerated(EnumType.STRING)
	private CategoriaRiscoIncidente categoriaRiscoIncidente;
	
	private LocalDateTime dataHoraIncidente;
	private LocalDateTime dataHoraCadastro;
	private Integer usuarioId;
	private boolean exigeNotificacao;
	private boolean notificado;
	private LocalDateTime dataHoraNotificacao; 
	private String observacao;
	
	@JsonManagedReference
	@NotNull
	@OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL, orphanRemoval = true)
	//private Queue<AtividadeIncidente> atividadesIncidente;
	private List<AtividadeIncidente> atividadesIncidente;
	
	@Enumerated(EnumType.STRING)
	private StatusIncidente statusIncidente;
	
}
