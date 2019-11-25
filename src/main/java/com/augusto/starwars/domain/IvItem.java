package com.augusto.starwars.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

/* 
 * IvItem é uma entidade de relacinamento para cada item contido no iventario de um soldado 
 */
@Entity
public class IvItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer quantidade;
	private Integer pontos;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="soldado_id")
	private Soldado soldado;
	
	public IvItem(){
		
	}

	public IvItem(Integer id, Integer quantidade, Item item, Soldado soldado) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.item = item;
		setPontos();
		this.soldado = soldado;
	}
	
	public IvItem(IvItem ivItem) {
		super();
		this.id = ivItem.getId();
		this.quantidade = ivItem.getQuantidade();
		this.item = ivItem.getItem();
		setPontos();
		this.soldado = ivItem.getSoldado();
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
	
	public Soldado getSoldado() {
		return soldado;
	}

	public void setSoldado(Soldado soldado) {
		this.soldado = soldado;
	}
	
	public Integer getPontos() {
		return pontos;
	}
	
    @Autowired
	public void setPontos() {
		this.pontos = getItem().getPontos() * quantidade;
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
