package io.github.doflavio.sgmonitoramentoseguranca.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Area;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Atividade;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.AtividadePlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Impactado;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Incidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.PlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.entities.Sensor;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.StatusEnum;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.CategoriaRiscoIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadeIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusAtividadePlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusIncidente;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.StatusPlanoAcao;
import io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente.TipoIncidente;
import jakarta.validation.constraints.NotNull;

public class Mocks {

	private static final String NOME_AREA_1 = "Z Mina";
	private static final String NOME_AREA_2 = "T Mina";
	private static final String NOME_AREA_3 = "Usina W";
	private static final String DESCRICAO_AREA_1 = "Área Z Mina é a área um da Mineradora";
	private static final String DESCRICAO_AREA_2 = "Área T Mina é a área dois da Mineradora";
	private static final String DESCRICAO_AREA_3 = "Área Usina W é a área três da Mineradora";
	private static final String DESCRICAO_ATIVIDADE_1 = "Atividade 1";
	private static final String DESCRICAO_ATIVIDADE_2 = "Atividade 2";
	private static final String DESCRICAO_ATIVIDADE_3 = "Atividade 3";
	private static final String DESCRICAO_ATIVIDADE_4 = "Atividade 4";
	private static final String DESCRICAO_ATIVIDADE_5 = "Atividade 5";
	
	public static List<Area> criarAreas() {
		Area area1 = Area.builder().nome(NOME_AREA_1).latitude(-194984226L).longitude(-410223124L).descricao(DESCRICAO_AREA_1)
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).sensores(new ArrayList<>()).build();

		Area area2 = Area.builder().nome(NOME_AREA_2).latitude(-225871536L).longitude(-422679190L).descricao(DESCRICAO_AREA_2)
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).sensores(new ArrayList<>()).build();
		
		Area area3 = Area.builder().nome(NOME_AREA_3).latitude(-227028777L).longitude(-438810099L).descricao(DESCRICAO_AREA_3)
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).sensores(new ArrayList<>()).build();
		
		return Arrays.asList(area1,area2,area3);
	}
	
	public static Area criarArea(
			String nome,
			Long latitude,
			Long longitude,
			String descricao			
			) {
		
		return Area.builder()
				.nome(nome)
				.latitude(latitude)
				.longitude(-longitude)
				.descricao(descricao)
				.dataHoraCadastro(LocalDateTime.now())
				.status(StatusEnum.ATIVO)
				.impactados(new ArrayList<>())
				.sensores(new ArrayList<>())
				.build();
	}
	
	public static List<Atividade> criarAtividades() {
		Atividade atv1 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_1) .build();
		Atividade atv2 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_2) .build();
		Atividade atv3 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_3) .build();
		Atividade atv4 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_4) .build();
		Atividade atv5 = Atividade.builder().descricao(DESCRICAO_ATIVIDADE_5) .build();
		return Arrays.asList(atv1,atv2,atv3,atv4,atv5);
	}
	
	public static Sensor criandoSensorArea(String nome, String descricao, Long valorLimitePadrao, Long valorLimiteAtencao,
			 String observacao,Area area) {
		
		return Sensor
				.builder()
				.nome(nome)
				.descricao(descricao)
				.valorLimitePadrao(600L).valorLimiteAtencao(700L)
				.observacao(observacao)
				.dataHoraCadastro(LocalDateTime.now())
				.usuarioId(1)
				.area(area)
				.build();
		
	}
	

	public static List<Incidente> incidentes(){

		/*List<Area> areas = criarAreas();
		List<Atividade> atividades = criarAtividades();*/
		List<Incidente> incidentes = new ArrayList<>();
		incidentes.add(incidente1DaArea1());
		return incidentes;
	}
	
	private static List<Area> criarAreasComImpactados() {
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
	
	
	//Incidente 1 da Área 4
	private static Incidente incidente1DaArea1() {
		Area area4 = Area.builder().nome("Area 4").latitude(10L).longitude(20L).descricao("Área 4 - Criada exclusivamente para o incidente 1")
				.dataHoraCadastro(LocalDateTime.now()).status(StatusEnum.ATIVO).impactados(new ArrayList<>()).build();
		List<Atividade> atividades = criarAtividades();
		
		return criarIncidenteComAtividades(area4, atividades);
	}
	
	
	public static Incidente criarIncidenteComAreaSemAtividades(
			String titulo, 
			String descricao, 
			TipoIncidente tipoIncidente,
			CategoriaRiscoIncidente categoriaRiscoIncidente, 
			LocalDateTime dataHoraIncidente,
			boolean exigeNotificacao, 
			boolean notificado,
			LocalDateTime dataHoraNotificacao, 
			String observacao, 
			Area area){
		
		Incidente incidente = Incidente
				.builder()
				.titulo(titulo)
				.descricao(descricao)
				.area(area)
				.tipoIncidente(tipoIncidente)
				.categoriaRiscoIncidente(categoriaRiscoIncidente)
				.dataHoraIncidente(dataHoraIncidente)
				.dataHoraCadastro(LocalDateTime.now())
				
				//TODO: verificar como será a utilizacao do usuário - Ex:1 = sistema
				.usuarioId(1)
				.exigeNotificacao(exigeNotificacao)
				.observacao(observacao)
				.statusIncidente(StatusIncidente.ABERTO)
				//.atividadesIncidente(new ArrayList<>())
				.build();
		
		return incidente;
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
				//.atividadesIncidente(new ArrayList<>())
				.build();
		
		List<AtividadeIncidente> atividadesAtividadeIncidentes = new ArrayList<>();
		
		atividades.forEach( a -> {
			atividadesAtividadeIncidentes.add(criarAtividadeIncidente(a, incidente));
		});
		
		//TODO: Será removido, foi incluído o plano de ação
		//incidente.setAtividadesIncidente(atividadesAtividadeIncidentes);
		
		return incidente;
	}
	
	
	public static AtividadeIncidente criarAtividadeIncidente(Atividade atividade, Incidente incidente){
		
		return AtividadeIncidente.builder().atividade(atividade).incidente(incidente)
				.statusAtividadeIncidente(StatusAtividadeIncidente.ABERTO).dataHoraCadastro(LocalDateTime.now())
				.build();
	}
	
	//---------------- Plano de ação ---------
	
	public static PlanoAcao criarPlanoAcao1() {
		List<AtividadePlanoAcao> atividadesPlanoAcao = new ArrayList<>(); 
		String titulo = "Evacuação urgente"; 
		String descricao = "Plano de evacuação";
		String observacao = "Plano de execução validado por orgãos de controle."; 
		
		PlanoAcao planoAcao1 = PlanoAcao
									.builder()
									.titulo(titulo)
									.descricao(descricao)
									.status(StatusEnum.ATIVO)
									.statusPlanoAcao(StatusPlanoAcao.INICIALIZADO)
									.observacao(observacao)
									.usuarioId(1)
									.dataHoraCadastro(LocalDateTime.now())
									//.atividadesPlanoAcao(atividadePlanoAcao)
									.build();
		
		//-- atividades
		String DescricaoAtividade1 = "Alerta de sergurança para evacuar a região.";
		String DescricaoAtividade2 = "AVISAR autoridades";
		Atividade atividade1 = Atividade.builder().descricao(DescricaoAtividade1).build();
		Atividade atividade2 = Atividade.builder().descricao(DescricaoAtividade2).build();
		List<Atividade> atividades = new ArrayList<>();
		atividades.add(atividade1);
		atividades.add(atividade2);


		atividades.forEach(atv -> {
			atividadesPlanoAcao.add(Mocks.criarAtividadePlanoAcao(atv, planoAcao1));
		});

		planoAcao1.setAtividadesPlanoAcao(atividadesPlanoAcao);
		
		return planoAcao1;
	}
	
	
	public static AtividadePlanoAcao criarAtividadePlanoAcao(Atividade atividade,PlanoAcao planoAcao){
		
		return AtividadePlanoAcao
				.builder()
				.atividade(atividade)
				.planoAcao(planoAcao)
				.statusAtividadePlanoAcao(StatusAtividadePlanoAcao.ABERTO)
				.dataHoraCadastro(LocalDateTime.now())
				.build();
	}
		
	
}
