package com.augusto.starwars.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.augusto.starwars.domain.enums.TipoSoldado;

@Entity
public class Soldado implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer idade;
	private String genero;
	private Double latitude;
	private Double longitude;
	private String nomeBase;
	private Integer tipo; // 1-> Rebelde, 2-> Traidor
	
	//Usar HashMap para melhoraria e exclusão da classe Iventario HashMap
	@OneToMany(mappedBy="soldado")
	private Set<Iventario> iventario = new HashSet<>();
	
	public Soldado() {
		
	}

	public Soldado(Integer id, String nome, Integer idade, String genero, Double latitude, Double longitude,
			String nomeBase, TipoSoldado tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.latitude = latitude;
		this.longitude = longitude;
		this.nomeBase = nomeBase;
		this.tipo = (tipo==null) ? null : tipo.getCod();
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getNomeBase() {
		return nomeBase;
	}

	public void setNomeBase(String nomeBase) {
		this.nomeBase = nomeBase;
	}

	public TipoSoldado getTipo() {
		return TipoSoldado.toEnum(tipo);
	}

	public void setTipo(TipoSoldado tipo) {
		this.tipo = tipo.getCod();
	}
	
	public Set<Iventario> getIventario() {
		return iventario;
	}

	public void setIventario(Set<Iventario> iventario) {
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
		Soldado other = (Soldado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
