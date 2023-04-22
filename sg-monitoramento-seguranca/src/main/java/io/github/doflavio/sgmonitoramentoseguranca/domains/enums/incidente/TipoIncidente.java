package io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente;

public enum TipoIncidente {
	
	DESLOCAMENTO_TERRA(0, "DESLOCAMENTO"), 
	DESABAMENTO(1, "ALTO"),
	TOMBAMENTO_VEICULO(2, "MEDIO"),
	VEICULO_QUEBRADO(3,"VEICULO_QUEBRADO"),
	MAXIMO(3, "MAXIMO");
	
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
		
		throw new IllegalArgumentException("Status inválido");
	}
}
