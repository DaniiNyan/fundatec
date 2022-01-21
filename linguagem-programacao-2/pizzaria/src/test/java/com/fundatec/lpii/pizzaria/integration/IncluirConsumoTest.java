package com.fundatec.lpii.pizzaria.integration;

import com.fundatec.lpii.pizzaria.entity.Consumo;
import com.fundatec.lpii.pizzaria.repository.ConsumoRepository;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IncluirConsumoTest {

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
    public void deveIncluirUmConsumo() {
        Consumo resultado = RestAssured
                .given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("{" +
                        "\"nomeDoProduto\": \"Refrigerante\"," +
                        "\"preco\": 6.00" +
                        "}")
                .when()
                .post("/v1/consumos")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(Consumo.class);

        assertTrue(consumoRepository.count() > 0);
    }

    @After
    public void limparDados() {
        consumoRepository.deleteAll();
    }
}
