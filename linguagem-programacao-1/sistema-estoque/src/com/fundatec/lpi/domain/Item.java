package com.fundatec.lpi.domain;

public class Item {
	int id;
	String nome;
	float preco;
	
	public Item(String nome, float preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public Item() {
	}

	public Item(int idOriginal, String novoNome, float novoPreco) {
		this.id = idOriginal;
		this.nome = novoNome;
		this.preco = novoPreco;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getId() {
		return id;
	}	

}
