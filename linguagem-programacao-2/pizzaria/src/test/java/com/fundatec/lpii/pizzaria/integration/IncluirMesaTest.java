package com.fundatec.lpii.pizzaria.integration;

import com.fundatec.lpii.pizzaria.entity.Mesa;
import com.fundatec.lpii.pizzaria.repository.MesaRepository;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IncluirMesaTest {

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
    public void deveIncluirUmaMesa() {
        Mesa resultado = RestAssured
                .given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("{" +
                        "\t\"identificador\": \"A\"" +
                        "}")
                .when()
                .post("/v1/mesas")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(Mesa.class);

        assertTrue(mesaRepository.count() > 0);
        assertEquals("A", resultado.getIdentificador());
    }

    @After
    public void limparDados() {
        mesaRepository.deleteAll();
    }
}
