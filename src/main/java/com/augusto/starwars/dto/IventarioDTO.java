package com.augusto.starwars.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.augusto.starwars.domain.Iventario;

public class IventarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer quantidade;
	
	public IventarioDTO() {
		
	}
	
	public IventarioDTO(Iventario obj) {
		id = obj.getId();
		quantidade = obj.getQuantidade();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
