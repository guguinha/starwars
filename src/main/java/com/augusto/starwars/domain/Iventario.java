package com.augusto.starwars.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/* Iventario é uma classe para separar a quantidade de cada item do iventario permitindo assim 
** poder adicionar qualquer novo item que venha a ser criado..
*/
@Entity
public class Iventario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Item item;
	private Integer quantidade;
	
	@JsonIgnore
	@ManyToOne
	private Soldado soldado;
	
	public Iventario(){
		
	}

	public Iventario(Integer id, Integer quantidade, Item item, Soldado soldado) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.item = item;
		this.setSoldado(soldado);

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
