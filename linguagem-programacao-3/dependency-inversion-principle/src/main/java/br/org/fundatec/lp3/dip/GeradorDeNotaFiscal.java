package br.org.fundatec.lp3.dip;

import br.org.fundatec.lp3.dip.domain.Contato;
import br.org.fundatec.lp3.dip.domain.Fatura;
import br.org.fundatec.lp3.dip.domain.NotaFiscal;
import br.org.fundatec.lp3.dip.posnota.AcaoAposGerarNota;

import java.util.List;

public class GeradorDeNotaFiscal {

    public static final double ALIQUOTA = 0.06;
    private List<AcaoAposGerarNota> acoesAposGerarNota;

    public GeradorDeNotaFiscal(List<AcaoAposGerarNota> acoesAposGerarNota) {
        this.acoesAposGerarNota = acoesAposGerarNota;
    }

    public NotaFiscal geraNF(Fatura fatura) {

        double valor = fatura.getValor();
        double imposto = impostoSimplesSobre(valor);
        Contato contato = fatura.getContato();

        NotaFiscal notaFiscal = new NotaFiscal(valor, imposto);
        notaFiscal.setEmailContato(contato.getEmail());
        notaFiscal.setTelefoneContato(contato.getTelefone());

        for (AcaoAposGerarNota acao : acoesAposGerarNota) {
            acao.execute(notaFiscal);
        }

        return notaFiscal;
    }


    private double impostoSimplesSobre(double valor) {
        return valor * ALIQUOTA;
    }

}
