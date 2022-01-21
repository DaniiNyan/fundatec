package br.org.fundatec.lp3.dip.domain;

import br.org.fundatec.lp3.dip.domain.Contato;

public class Fatura {
	
	private Contato contato;
	private double valor;
	
	public Fatura(Contato contato, double valor) {
		this.contato = contato;
		this.valor = valor;
	}
	
    public double getValor() {
        return valor;
    }

	public Contato getContato() {
		return contato;
	}

}
