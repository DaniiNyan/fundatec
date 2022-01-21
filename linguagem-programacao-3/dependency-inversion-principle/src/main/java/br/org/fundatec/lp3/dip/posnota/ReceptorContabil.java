package br.org.fundatec.lp3.dip.posnota;

import br.org.fundatec.lp3.dip.domain.NotaFiscal;
import br.org.fundatec.lp3.dip.service.GerenciadorContabil;

public class ReceptorContabil implements AcaoAposGerarNota {

    private GerenciadorContabil gerenciadorContabil;

    public ReceptorContabil() {
        gerenciadorContabil = new GerenciadorContabil();
    }

    @Override
    public void execute(NotaFiscal notaFiscal) {
        gerenciadorContabil.contabiliza(notaFiscal.getValor(), notaFiscal.getImposto());
    }
}
