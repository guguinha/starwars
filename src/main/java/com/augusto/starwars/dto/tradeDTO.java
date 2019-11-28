package com.augusto.starwars.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class tradeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id_soldado_1;
	private Integer id_soldado_2;
	
	private List<ItemTrocaDTO> itens1 = new ArrayList<>();
	private List<ItemTrocaDTO> itens2 = new ArrayList<>();
	
	public tradeDTO() {
		
	}
	
	public tradeDTO(Integer id_soldado_1, Integer id_soldado_2, List<ItemTrocaDTO> itens1, List<ItemTrocaDTO> itens2) {
		super();
		this.id_soldado_1 = id_soldado_1;
		this.id_soldado_2 = id_soldado_2;
	}

	public Integer getId_soldado_1() {
		return id_soldado_1;
	}

	public void setId_soldado_1(Integer id_soldado_1) {
		this.id_soldado_1 = id_soldado_1;
	}

	public Integer getId_soldado_2() {
		return id_soldado_2;
	}

	public void setId_soldado_2(Integer id_soldado_2) {
		this.id_soldado_2 = id_soldado_2;
	}

	public List<ItemTrocaDTO> getItens1() {
		return itens1;
	}

	public void setItens1(List<ItemTrocaDTO> itens1) {
		this.itens1 = itens1;
	}
	
	public List<ItemTrocaDTO> getItens2() {
		return itens2;
	}

	public void setItens2(List<ItemTrocaDTO> itens2) {
		this.itens2 = itens2;
	}
	
	
	
	
	
	
	
}
