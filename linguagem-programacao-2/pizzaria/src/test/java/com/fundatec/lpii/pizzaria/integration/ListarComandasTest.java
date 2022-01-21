package com.fundatec.lpii.pizzaria.integration;

import com.fundatec.lpii.pizzaria.entity.Comanda;
import com.fundatec.lpii.pizzaria.entity.Mesa;
import com.fundatec.lpii.pizzaria.repository.ComandaRepository;
import com.fundatec.lpii.pizzaria.repository.MesaRepository;
import com.fundatec.lpii.pizzaria.service.MesaService;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListarComandasTest {
    private Mesa mesaA;

    @LocalServerPort
    private int port;

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private MesaService mesaService;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        comandaRepository.deleteAll();
        mesaRepository.deleteAll();
        mesaA = new Mesa("A");
        mesaRepository.save(mesaA);
    }

    @Test
    public void deveListarTodasAsComandasDeUmaMesa() {
        Comanda comandaMaria = new Comanda("Maria");
        Comanda comandaRoberta = new Comanda("Roberta");
        Comanda comandaJulia = new Comanda("Julia");
        List<Comanda> comandasEsperadas = Arrays.asList(comandaMaria, comandaRoberta, comandaJulia);

        mesaService.incluiComanda(mesaA.getId(), comandaMaria);
        mesaService.incluiComanda(mesaA.getId(), comandaRoberta);
        mesaService.incluiComanda(mesaA.getId(), comandaJulia);

        Comanda[] resultado = RestAssured
                .get("/v1/mesas/{id}", mesaA.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(Comanda[].class);

        List<String> comandasRecebidas = new ArrayList<>();
        for (Comanda comanda : resultado) {
            comandasRecebidas.add(comanda.getCliente());
        }

        for (Comanda comanda : comandasEsperadas) {
            assertTrue(comandasRecebidas.contains(comanda.getCliente()));
        }
    }
    @After
    public void limparDados() {
        comandaRepository.deleteAll();
        mesaRepository.deleteAll();
    }

}
