package com.fundatec.lpii.pizzaria.integration;

import com.fundatec.lpii.pizzaria.entity.Mesa;
import com.fundatec.lpii.pizzaria.repository.ComandaRepository;
import com.fundatec.lpii.pizzaria.repository.MesaRepository;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IncluirComandaNaMesaTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        comandaRepository.deleteAll();
        mesaRepository.deleteAll();
        mesaRepository.save(new Mesa("A"));
        mesaRepository.save(new Mesa("B"));
    }

    @Test
    public void deveIncluirUmaComandaEmUmaMesa() {
        RestAssured
                .given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("{" +
                        "\"cliente\": \"Maria\"" +
                        "}")
                .when()
                .patch("/v1/mesas/{id}", 1)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("cliente", Matchers.equalTo("Maria"));
    }

    @After
    public void limparDados() {
        comandaRepository.deleteAll();
        mesaRepository.deleteAll();
    }
}
