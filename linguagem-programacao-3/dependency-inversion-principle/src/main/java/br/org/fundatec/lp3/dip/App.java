package br.org.fundatec.lp3.dip;

import br.org.fundatec.lp3.dip.domain.Contato;
import br.org.fundatec.lp3.dip.domain.Fatura;
import br.org.fundatec.lp3.dip.posnota.*;

import java.util.Arrays;

public class App {
	
	public static void main(String[] args) {
		
		AcaoAposGerarNota enviadorDeEmail = new ReceptorDeEmail();
		AcaoAposGerarNota gerenciadorContabil = new ReceptorContabil();
		AcaoAposGerarNota notaFiscalDao = new ReceptorNotaDAO();
		AcaoAposGerarNota enviadorDeSMS = new ReceptorDeSMS();
		
		Contato contato = new Contato("exemplo@email.com", "5551999999999");

		GeradorDeNotaFiscal gerador = new GeradorDeNotaFiscal(Arrays.asList(enviadorDeEmail,
				notaFiscalDao, gerenciadorContabil, enviadorDeSMS));
		
		Fatura fatura = new Fatura(contato, 100.0);

		gerador.geraNF(fatura);
		
	}

}
