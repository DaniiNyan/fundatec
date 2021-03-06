package br.com.fundatec.ExemploApi.api.v1.dto;

import br.com.fundatec.ExemploApi.entity.Cachorro;

public class CachorroOutputDto {

	private Long id;
	private String nome;
	private String raca;
	private String porte;
	private Integer idade;
	
	public CachorroOutputDto(Long id, String nome, String raca, String porte, Integer idade) {
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.porte = porte;
		this.idade = idade;
	}

	public CachorroOutputDto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
