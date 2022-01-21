package br.com.fundatec.ExemploApi.integration;

import org.hamcrest.Matchers;
import org.junit.Assert;
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

import br.com.fundatec.ExemploApi.entity.PorteParametro;
import br.com.fundatec.ExemploApi.repository.CachorroRepository;
import br.com.fundatec.ExemploApi.repository.PorteParametroRepository;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IncluirCachorroTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CachorroRepository cachorroRepository;
	
	@Autowired
	private PorteParametroRepository porteParametroRepository;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
		cachorroRepository.deleteAll();
		porteParametroRepository.deleteAll();
		porteParametroRepository.save(new PorteParametro("Pequeno"));
		porteParametroRepository.save(new PorteParametro("Médio"));
		porteParametroRepository.save(new PorteParametro("Grande"));
	}
	
	@Test
	public void deveIncluirUmCachorro() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"Urso\"," + 
					"	\"raca\": \"Pastor Belga\"," + 
					"	\"porte\": \"Grande\"," + 
					"	\"idade\": 2," + 
					"	\"cpc\": \"012.345.678-90\" " +	
					"}")
			.when()
			.post("/v1/cachorros")
			.then()
			.assertThat()
			.statusCode(HttpStatus.CREATED.value())
			.body("nome", Matchers.equalTo("Urso"))
			.body("raca", Matchers.equalTo("Pastor Belga"))
			.body("porte", Matchers.equalTo("Grande"))
			.body("idade", Matchers.equalTo(2))
			.body("id", Matchers.greaterThan(0));
		
		
		Assert.assertTrue(cachorroRepository.count() > 0);
	}
	
	@Test
	public void deveValidarCachorroSemNome() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"raca\": \"Pastor Belga\"," + 
					"	\"porte\": \"Grande\"," + 
					"	\"idade\": 2" + 
					"}")
			.when()
			.post("/v1/cachorros")
			.then()
			.assertThat()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("errors[0].defaultMessage", Matchers.equalTo("O campo nome é obrigatório."));
		
		Assert.assertTrue(cachorroRepository.count() == 0);
	}

	@Test
	public void deveValidarCpcInvalido() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"Urso\"," + 
					"	\"raca\": \"Pastor Belga\"," + 
					"	\"porte\": \"Grande\"," + 
					"	\"idade\": 2," +
					"	\"cpc\": \"cpc\" " +	
					"}")
			.when()
			.post("/v1/cachorros")
			.then()
			.assertThat()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("errors[0].defaultMessage", Matchers.equalTo("Campo CPC inválido."));
		
		Assert.assertTrue(cachorroRepository.count() == 0);
	}
	
	@Test
	public void deveValidarPorteDoCachorro() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"Urso\"," + 
					"	\"raca\": \"Pastor Belga\"," + 
					"	\"porte\": \"Qualquer\"," + 
					"	\"idade\": 2," +
					"	\"cpc\": \"012.345.678-90\" " +	
					"}")
			.when()
			.post("/v1/cachorros")
			.then()
			.assertThat()
			.body("mensagem", Matchers.equalTo("Porte inválido."))
			.statusCode(HttpStatus.EXPECTATION_FAILED.value());
		
		Assert.assertTrue(cachorroRepository.count() == 0);
	}
	
	@Test
	public void deveValidarPorteDeAcordoComRaca() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"Urso\"," + 
					"	\"raca\": \"Pastor Belga\"," + 
					"	\"porte\": \"Pequeno\"," + 
					"	\"idade\": 2," +
					"	\"cpc\": \"012.345.678-90\" " +	
					"}")
			.when()
			.post("/v1/cachorros")
			.then()
			.assertThat()
			.body("mensagem", Matchers.equalTo("Porte inválido para esta raça."))
			.statusCode(HttpStatus.EXPECTATION_FAILED.value());
	
		Assert.assertTrue(cachorroRepository.count() == 0);
	}
	
	@Test
	public void deveValidarIdade() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"Alexandre\"," + 
					"	\"raca\": \"Terrier\"," + 
					"	\"porte\": \"Inválido\"," + 
					"	\"idade\": -1" +	
					"}")
			.when()
			.post("/v1/cachorros")
			.then()
			.assertThat()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("errors[0].defaultMessage", Matchers.equalTo("Idade deve ser maior ou igual a zero."));
		
		Assert.assertTrue(cachorroRepository.count() == 0);
	}
}
