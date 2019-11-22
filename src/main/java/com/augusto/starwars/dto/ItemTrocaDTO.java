package com.augusto.starwars.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.augusto.starwars.domain.IvItem;
import com.augusto.starwars.repositories.ItemRepository;

public class ItemTrocaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer idItem; //id do item referido
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer quantidade;
	
	private Integer pontos;
	
	public ItemTrocaDTO() {
		
	}
	
	public ItemTrocaDTO(IvItem obj) {
		idItem = obj.getId();
		quantidade = obj.getQuantidade();
		setPontos();
	}

	public Integer getId() {
		return idItem;
	}

	public void setId(Integer id) {
		this.idItem = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getPontos() {
		return pontos;
	}
	
	@Autowired
	public void setPontos() {
		this.pontos = itemRepository.findById(this.getId()).get().getPontos() * quantidade;
	}

}
