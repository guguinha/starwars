package com.augusto.starwars.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class tradeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id_soldado_1;
	private Integer id_soldado_2;
	
	private List<IventarioDTO> itens1 = new ArrayList<>();
	private List<IventarioDTO> itens2 = new ArrayList<>();
	
	public tradeDTO() {
		
	}
	
	public tradeDTO(Integer id_soldado_1, Integer id_soldado_2, List<IventarioDTO> itens1, List<IventarioDTO> itens2) {
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

	public List<IventarioDTO> getItens1() {
		return itens1;
	}

	public void setItens1(List<IventarioDTO> itens1) {
		this.itens1 = itens1;
	}
	
	public List<IventarioDTO> getItens2() {
		return itens2;
	}

	public void setItens2(List<IventarioDTO> itens2) {
		this.itens2 = itens2;
	}
	
	
	
	
	
	
	
}
