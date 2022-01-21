package br.org.fundatec.lp3.dip.posnota;

import br.org.fundatec.lp3.dip.domain.NotaFiscal;
import br.org.fundatec.lp3.dip.service.NotaFiscalDAO;

public class ReceptorNotaDAO implements AcaoAposGerarNota {

    private NotaFiscalDAO notaFiscalDAO;

    public ReceptorNotaDAO() {
        notaFiscalDAO = new NotaFiscalDAO();
    }

    @Override
    public void execute(NotaFiscal notaFiscal) {
        notaFiscalDAO.persiste(notaFiscal);
    }
}
