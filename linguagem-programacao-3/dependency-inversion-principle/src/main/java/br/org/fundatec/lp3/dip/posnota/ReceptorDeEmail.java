package br.org.fundatec.lp3.dip.posnota;

import br.org.fundatec.lp3.dip.domain.NotaFiscal;
import br.org.fundatec.lp3.dip.service.EnviadorDeEmail;

public class ReceptorDeEmail implements AcaoAposGerarNota {

    private EnviadorDeEmail enviadorDeEmail;

    public ReceptorDeEmail() {
        enviadorDeEmail = new EnviadorDeEmail();
    }

    @Override
    public void execute(NotaFiscal notaFiscal) {
        enviadorDeEmail.enviarEmail(notaFiscal.getEmailContato());
    }
}
