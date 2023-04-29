package io.github.doflavio.sgnotificacao.enums;

public enum TipoIncidente {
	
	EVACUACAO(0, "EVACUAÇÃO"), 
	DESABAMENTO(1, "DESABAMENTO"),
	TOMBAMENTO_VEICULO(2, "TOMBAMENTO VEÍCULO"),
	VEICULO_QUEBRADO(3,"VEÍCULO QUEBRADO"),
	INSPECAO(4, "INSPEÇÃO"),
	DESLOCAMENTO_TERRA(5, "DESLOCAMENTO DE TERRAS");
	
	private Integer codigo;
	private String descricao;
	
	private TipoIncidente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoIncidente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoIncidente x : TipoIncidente.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("TipoIncidente inválido");
	}
	
	public static TipoIncidente toEnum(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		for(TipoIncidente x : TipoIncidente.values()) {
			if(descricao.equals(x.getDescricao())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("TipoIncidente inválido");
	}
}
