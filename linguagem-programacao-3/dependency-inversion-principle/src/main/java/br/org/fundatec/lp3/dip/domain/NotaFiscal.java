package br.org.fundatec.lp3.dip.domain;

public class NotaFiscal {

    private double valor;
    private double imposto;
    private String emailContato;
    private String telefoneContato;

	public NotaFiscal(double valor, double imposto) {
        this.valor = valor;
        this.imposto = imposto;
    }

    public String getEmailContato() {
        return emailContato;
    }
    
    public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}

    public double getValor() {
        return valor;
    }
    
    public double getImposto() {
    	return imposto;
    }
    
    public String getTelefoneContato() {
        return telefoneContato;
    }
    
    public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}
}
