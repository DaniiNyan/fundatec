package br.fundatec.lp3.junit.leilao;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeilaoTest {

    @Test
    public void deveArmazenarUmLance() {
        Leilao leilao = new Leilao("Produto");
        assertTrue(leilao.getLances().isEmpty());

        Lance lance = new Lance(new Usuario("Fulano"), 100);
        leilao.propoe(lance);

        List<Lance> lances = leilao.getLances();
        int quantidadeEsperada = 1;
        assertEquals(quantidadeEsperada, lances.size());
    }

}
