package com.augusto.starwars.domain;

import java.io.Serializable;

/* IvItem é uma classe para separar a quantidade de cada item do iventario permitindo assim 
** poder adicionar qualquer novo item que venha a ser criado..
**/
public class IvItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer quantidade;
	
	private Item item;
	private Iventario iventario;
	
	public IvItem(){
		
	}

	public IvItem(Integer id, Integer quantidade, Item item, Iventario iventario) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.item = item;
		this.iventario = iventario;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Iventario getIventario() {
		return iventario;
	}

	public void setIventario(Iventario iventario) {
		this.iventario = iventario;
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
		IvItem other = (IvItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
