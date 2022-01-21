package com.fundatec.lpii.pizzaria.integration;

import com.fundatec.lpii.pizzaria.entity.Consumo;
import com.fundatec.lpii.pizzaria.repository.ConsumoRepository;
import io.restassured.RestAssured;
import junit.framework.Assert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListarConsumoTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ConsumoRepository consumoRepository;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        consumoRepository.deleteAll();
    }

    @Test
    public void deveListarTodosOsConsumosCadastrados() {
        consumoRepository.save(new Consumo("Refrigerante", 6.0));
        consumoRepository.save(new Consumo("Rodízio", 30.0));

        Consumo[] resultado = RestAssured
                .get("/v1/consumos")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(Consumo[].class);

        assertEquals(2, resultado.length);

        List<String> nomesDeProdutosEsperados = Arrays.asList("Refrigerante", "Rodízio");
        List<Double> precosEsperados = Arrays.asList(6.0, 30.0);

        for (Consumo consumo : resultado) {
            assertTrue(nomesDeProdutosEsperados.contains(consumo.getNomeDoProduto()));
            assertTrue(precosEsperados.contains(consumo.getPreco()));
        }
    }
}
