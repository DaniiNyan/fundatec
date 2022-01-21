package com.fundatec.lpi.domain;

public class Brinde {
	int id;
	String nome;
	int itemID;
	
	public Brinde(String nome, int itemID) {
		this.nome = nome;
		this.itemID = itemID;
	}

	public Brinde(int id, String nome, int itemID) {
		this.id = id;
		this.nome = nome;
		this.itemID = itemID;
	}

	public String getNome() {
		return nome;
	}

	public int getItemID() {
		return itemID;
	}

	public int getId() {
		return id;
	}
	
	
	

}
