package com.augusto.starwars.domain.enums;

public enum TipoSoldado {
	
	REBELDE(1, "Rebelde"),
	TRAIDOR(2, "Traídor");
	
	private Integer cod;
	private String descricao;
	
	private TipoSoldado(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoSoldado toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoSoldado x : TipoSoldado.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
