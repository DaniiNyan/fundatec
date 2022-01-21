package com.fundatec.lpii.pizzaria.unit;

import com.fundatec.lpii.pizzaria.entity.Comanda;
import com.fundatec.lpii.pizzaria.entity.Mesa;
import org.junit.Assert;
import org.junit.Test;

public class MesaTest {

    @Test
    public void deveCriarUmaMesaComNúmero() {
        Mesa mesa = new Mesa("1");

        Assert.assertEquals("1", mesa.getIdentificador());
    }

//    @Test
//    public void deveAdicionarUmaComandaNaMesa() {
//        Mesa mesa = new Mesa("1");
//        Comanda comanda = new Comanda();
//        mesa.adicionarComanda(comanda);
//
//        Assert.assertTrue(mesa.getComandas().contains(comanda));
//    }
}
