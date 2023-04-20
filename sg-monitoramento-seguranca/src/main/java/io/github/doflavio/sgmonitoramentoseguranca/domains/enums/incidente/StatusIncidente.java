package io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente;

public enum StatusIncidente {

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");
	
	private Integer codigo;
	private String descricao;
	
	private StatusIncidente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusIncidente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(StatusIncidente x : StatusIncidente.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status inválido");
	}
}
