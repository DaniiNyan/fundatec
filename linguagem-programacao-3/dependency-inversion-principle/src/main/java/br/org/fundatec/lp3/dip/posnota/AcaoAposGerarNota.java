package br.org.fundatec.lp3.dip.posnota;

import br.org.fundatec.lp3.dip.domain.NotaFiscal;

public interface AcaoAposGerarNota {

    void execute(NotaFiscal notaFiscal);
}
