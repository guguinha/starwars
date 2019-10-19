package com.augusto.starwars.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.augusto.starwars.domain.Soldado;

public class LocalizacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Double latitude;
	@NotEmpty(message="Preenchimento obrigatório")
	private Double longitude;
	@NotEmpty(message="Preenchimento obrigatório")
	private String nomeBase;
	
	public LocalizacaoDTO() {
		
	}
	
	public LocalizacaoDTO(Soldado obj) {
		id = obj.getId();	
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
