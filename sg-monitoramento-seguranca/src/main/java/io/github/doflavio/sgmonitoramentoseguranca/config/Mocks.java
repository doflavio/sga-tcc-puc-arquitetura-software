package io.github.doflavio.sgmonitoramentoseguranca.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Impactado;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;

public class Mocks {
	
	
	private static final String NOME_AREA_1 = "Area 1";
	private static final String NOME_AREA_2 = "Area 2";
	private static final String NOME_AREA_3 = "Area 3";
	private static final String DESCRICAO_AREA_1 = "Descrição area 1";
	private static final String DESCRICAO_AREA_2 = "Descrição area 2";
	private static final String DESCRICAO_ATIVIDADE_1 = "Atividade 1";
	private static final String DESCRICAO_ATIVIDADE_2 = "Atividade 2";
	private static final String DESCRICAO_ATIVIDADE_3 = "Atividade 3";
	private static final String DESCRICAO_ATIVIDADE_4 = "Atividade 4";
	private static final String DESCRICAO_ATIVIDADE_5 = "Atividade 5";
	
	
	
	public static List<Area> criarSomenteAreas() {
		Area ar1 = Area.builder().nome(NOME_AREA_1).latitude(10L).longitude(20L).descricao(DESCRICAO_AREA_1)
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).build();

		Area ar2 = Area.builder().nome(NOME_AREA_2).latitude(30L).longitude(40L).descricao(DESCRICAO_AREA_2)
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).build();
		
		Area ar3 = Area.builder().nome(NOME_AREA_3).latitude(300L).longitude(400L).descricao(DESCRICAO_AREA_2)
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).build();
		
		return Arrays.asList(ar1,ar2,ar3);

	}
	
	public static List<Atividade> criarAtividades() {
		Atividade atv1 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_1) .build();
		Atividade atv2 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_2) .build();
		Atividade atv3 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_3) .build();
		Atividade atv4 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_4) .build();
		Atividade atv5 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_5) .build();
		return Arrays.asList(atv1,atv2,atv3,atv4,atv5);
	}
	
	
	
	
	
	
	
	
	
	

	public static List<Incidente> incidentes(){

		/*List<Area> areas = criarAreas();
		List<Atividade> atividades = criarAtividades();*/
		List<Incidente> incidentes = new ArrayList<>();
		incidentes.add(incidente1DaArea1());
		return incidentes;
	}
	
	public static List<Area> getAreas(){
		return criarAreas();
	}
	
	public static List<Atividade> getAtividades(){
		return criarAtividades();
	}
	
	
	//Incidente 1 da Área 1
	private static Incidente incidente1DaArea1() {
		List<Area> areas = criarAreas();
		List<Atividade> atividades = criarAtividades();
		
		return criarIncidenteComAtividades(areas.get(0), atividades);
	}
	
	
	
	private static List<Area> criarAreas() {
		Area ar1 = Area.builder().nome(NOME_AREA_1).latitude(10L).longitude(20L).descricao(DESCRICAO_AREA_1)
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).build();

		Area ar2 = Area.builder().nome(NOME_AREA_2).latitude(30L).longitude(40L).descricao(DESCRICAO_AREA_2)
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).build();
		
		Area ar3 = Area.builder().nome(NOME_AREA_3).latitude(300L).longitude(400L).descricao(DESCRICAO_AREA_2)
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).build();
		
		
		ar2.getImpactados().add(criarImpactadoArea(1,ar2));
		ar2.getImpactados().add(criarImpactadoArea(2,ar2));
		
		ar3.getImpactados().add(criarImpactadoArea(3,ar3));
		
		return Arrays.asList(ar1,ar2,ar3);

	}
	
	private static Impactado criarImpactadoArea(int usuarioId,Area area){
		Impactado impactado = Impactado
				.builder()
				.usuarioId(usuarioId)
				.area(area)
				.dataHoraCadastro(LocalDateTime.now())
				.build();
		
		return impactado;
	}
	
	
	
	
	private static Incidente criarIncidenteComAtividades(Area area, List<Atividade> atividades){
		
		Incidente incidente = Incidente
				.builder()
				.titulo("Título Incidente 1")
				.descricao("Descricao Incidente 1")
				.area(area)
				.tipoIncidente(TipoIncidente.VEICULO_QUEBRADO)
				.categoriaRiscoIncidente(CategoriaRiscoIncidente.BAIXO)
				.dataHoraIncidente(LocalDateTime.now().minusHours(5))
				.dataHoraCadastro(LocalDateTime.now())
				
				//TODO: verificar como será a utilizacao do usuário - Ex:1 = sistema
				.usuarioId(1)
				.exigeNotificacao(true)
				.observacao("Observação para atenção")
				.statusIncidente(StatusIncidente.ABERTO)
				.atividadesIncidente(new ArrayList<>())
				.build();
		
		List<AtividadeIncidente> atividadesAtividadeIncidentes = new ArrayList<>();
		
		atividades.forEach( a -> {
			atividadesAtividadeIncidentes.add(criarAtividadeIncidente(a, incidente));
		});
		
		incidente.setAtividadesIncidente(atividadesAtividadeIncidentes);
		
		return incidente;
	}
	
	
	private static AtividadeIncidente criarAtividadeIncidente(Atividade atividade, Incidente incidente){
		
		return AtividadeIncidente.builder().atividade(atividade).incidente(incidente)
				.statusAtividadeIncidente(StatusAtividadeIncidente.ABERTO).dataHoraCadastro(LocalDateTime.now())
				.build();
	}
		
	
}
