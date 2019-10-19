package com.augusto.starwars.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.augusto.starwars.domain.Soldado;

public class SoldadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	private Integer idade;
	private String genero;
	private Double latitude;
	private Double longitude;
	private String nomeBase;
	
	public SoldadoDTO() {
		
	}
	
	public SoldadoDTO(Soldado obj) {
		id = obj.getId();
		nome = obj.getNome();
		idade = obj.getIdade();
		genero = obj.getGenero();		
		latitude = obj.getLatitude();
		longitude = obj.getLongitude();
		nomeBase = obj.getNomeBase();
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
	
	

}
