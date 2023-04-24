package io.github.doflavio.sgmonitoramentoseguranca.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.github.doflavio.sgmonitoramentoseguranca.domains.dtos.AtividadePlanoAcaoDTO;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadePlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusPlanoAcao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PlanoAcao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonManagedReference
	@NotNull
	@OneToMany(mappedBy = "planoAcao", cascade = CascadeType.ALL, orphanRemoval = true)
	//private Queue<AtividadeIncidente> atividadesIncidente;
	private List<AtividadePlanoAcao> atividadesPlanoAcao;
	
	public List<AtividadePlanoAcaoDTO> listaAtividadePlanoAcaoDTO(){
		if(this.atividadesPlanoAcao == null) {
			return new ArrayList<>();
		}
		return this.atividadesPlanoAcao.stream().map(ap-> ap.toAtividadePlanoAcaoDTO()).collect(Collectors.toList());
	}
	
	private String titulo;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	private StatusPlanoAcao statusPlanoAcao;
	private LocalDateTime dataHorarFinalizacao;
	private String observacao;
	
	private Integer usuarioId;
	private LocalDateTime dataHoraCadastro;
	
	
	public PlanoAcaoDTO toPlanoAcaoDTO() {
		return PlanoAcaoDTO
				.builder()
				.id(id)
				.titulo(descricao)
				.status(status)
				.status(status)
				.statusPlanoAcao(statusPlanoAcao)
				.observacao(observacao)
				.atividadesPlanoAcao(this.listaAtividadePlanoAcaoDTO())
				.build();
	}
	
	
	
}
