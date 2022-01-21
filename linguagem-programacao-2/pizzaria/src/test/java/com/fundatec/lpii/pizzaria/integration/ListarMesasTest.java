package com.fundatec.lpii.pizzaria.integration;

import com.fundatec.lpii.pizzaria.entity.Mesa;
import com.fundatec.lpii.pizzaria.repository.MesaRepository;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
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

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListarMesasTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MesaRepository mesaRepository;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        mesaRepository.deleteAll();
    }


    @Test
    public void deveListarTodasAsMesasCadastradas() {
        mesaRepository.save(new Mesa("A"));
        mesaRepository.save(new Mesa("B"));

        Mesa[] mesas = RestAssured
                .get("/v1/mesas")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(Mesa[].class);

        List<String> identificadoresRecebidos = new ArrayList<>();
        for (Mesa mesa : mesas) {
            identificadoresRecebidos.add(mesa.getIdentificador());
        }

        List<String> identificadoresEsperados = Arrays.asList("A", "B");
        for (String identificador : identificadoresEsperados) {
            assertTrue(identificadoresRecebidos.contains(identificador));
        }
    }

    @After
    public void limparDados() {
        mesaRepository.deleteAll();
    }
}
