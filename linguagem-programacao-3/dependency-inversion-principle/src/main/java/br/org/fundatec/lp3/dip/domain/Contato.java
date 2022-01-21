package br.org.fundatec.lp3.dip.domain;

public class Contato {
	
	private String email;
	private String telefone;
	
	public Contato(String email, String telefone) {
		this.email = email;
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getTelefone() {
		return telefone;
	}

}
