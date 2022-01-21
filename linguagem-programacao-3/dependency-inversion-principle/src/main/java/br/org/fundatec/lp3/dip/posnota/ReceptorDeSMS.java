package br.org.fundatec.lp3.dip.posnota;

import br.org.fundatec.lp3.dip.domain.NotaFiscal;
import br.org.fundatec.lp3.dip.service.EnviadorDeSMS;

public class ReceptorDeSMS implements AcaoAposGerarNota {

    private EnviadorDeSMS enviadorDeSMS;

    public ReceptorDeSMS() {
        enviadorDeSMS = new EnviadorDeSMS();
    }

    @Override
    public void execute(NotaFiscal notaFiscal) {
        enviadorDeSMS.enviarSmsDeConfirmacao(notaFiscal.getTelefoneContato(), "Mensagem");
    }
}
