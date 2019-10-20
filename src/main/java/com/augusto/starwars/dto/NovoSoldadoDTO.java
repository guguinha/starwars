package com.augusto.starwars.dto;

import java.io.Serializable;

public class NovoSoldadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	//@NotEmpty(message="Preenchimento obrigatório")
	//@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	private Integer idade;
	private String genero;
	private Double latitude;
	private Double longitude;
	private String nomeBase;
	//private Integer tipo;
	
	private Integer quantidadeItem1; //Arma
	private Integer quantidadeItem2;//Munição
	private Integer quantidadeItem3;//Água
	private Integer quantidadeItem4;//Comida
	
	public NovoSoldadoDTO() {
		
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

	/*public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}*/

	public Integer getQuantidadeItem1() {
		return quantidadeItem1;
	}

	public void setQuantidadeItem1(Integer quantidadeItem1) {
		this.quantidadeItem1 = quantidadeItem1;
	}

	public Integer getQuantidadeItem2() {
		return quantidadeItem2;
	}

	public void setQuantidadeItem2(Integer quantidadeItem2) {
		this.quantidadeItem2 = quantidadeItem2;
	}

	public Integer getQuantidadeItem3() {
		return quantidadeItem3;
	}

	public void setQuantidadeItem3(Integer quantidadeItem3) {
		this.quantidadeItem3 = quantidadeItem3;
	}

	public Integer getQuantidadeItem4() {
		return quantidadeItem4;
	}

	public void setQuantidadeItem4(Integer quantidadeItem4) {
		this.quantidadeItem4 = quantidadeItem4;
	}
	
	
}
