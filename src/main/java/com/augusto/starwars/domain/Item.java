package com.augusto.starwars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * Esta entidade representa a lista de itens existentes no sistema /
 * permitindo facilmente a adição de novos tipos de itens..
 */

//Modificar Item para tipo Enum

@Entity
public class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer pontos;
	
	@JsonIgnore
	@OneToMany(mappedBy="item")
	private List<IvItem> ivItem = new ArrayList<>();
	
	public Item() {
		
	}

	public Item(Integer id, String nome, Integer pontos) {
		super();
		this.id = id;
		this.nome = nome;
		this.pontos = pontos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public List<IvItem> getIvItem() {
		return ivItem;
	}

	public void setIvItem(List<IvItem> ivItem) {
		this.ivItem = ivItem;
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
