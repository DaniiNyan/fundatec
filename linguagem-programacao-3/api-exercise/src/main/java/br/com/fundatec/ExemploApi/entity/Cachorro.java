package br.com.fundatec.ExemploApi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cachorro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String raca;
	private String porte;
	private Integer idade;
	
	public Cachorro() {
	}
	
	public Cachorro(String nome, String raca, String porte, Integer idade) {
		this.nome = nome;
		this.raca = raca;
		this.porte = porte;
		this.idade = idade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Cachorro{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", raca='" + raca + '\'' +
				", porte='" + porte + '\'' +
				", idade=" + idade +
				'}';
	}
}
