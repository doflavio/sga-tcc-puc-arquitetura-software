package io.github.doflavio.sgmonitoramentoseguranca.domains.enums.incidente;

public enum CategoriaRiscoIncidente {

	BAIXO(0, "BAIXO"), MEDIO(1, "MEDIO"), ALTO(2, "ALTO"), MAXIMO(3, "MAXIMO");
	
	private Integer codigo;
	private String descricao;
	
	private CategoriaRiscoIncidente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static CategoriaRiscoIncidente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(CategoriaRiscoIncidente x : CategoriaRiscoIncidente.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Categoria inválida");
	}
}
