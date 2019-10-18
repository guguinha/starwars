package com.augusto.starwars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Iventario implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private List<IvItem> itens = new ArrayList<>();
	
	public Iventario() {
		
	}

	public Iventario(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<IvItem> getItens() {
		return itens;
	}

	public void setItens(List<IvItem> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Iventario other = (Iventario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
