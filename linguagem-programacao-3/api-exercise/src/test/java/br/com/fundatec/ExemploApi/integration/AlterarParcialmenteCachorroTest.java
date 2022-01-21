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

import br.com.fundatec.ExemploApi.entity.Cachorro;
import br.com.fundatec.ExemploApi.entity.PorteParametro;
import br.com.fundatec.ExemploApi.repository.CachorroRepository;
import br.com.fundatec.ExemploApi.repository.PorteParametroRepository;
import io.restassured.RestAssured;
import io.restassured.http.Header;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlterarParcialmenteCachorroTest {
	private Cachorro cachorroParaEditar;
	
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
		cachorroParaEditar = new Cachorro("Alexandre", "Terrier", "Médio", 1);
		cachorroParaEditar = cachorroRepository.save(cachorroParaEditar);
	}
	
	@Test
	public void deveAlterarIdadeDoCachorro() {		
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"idade\": 2" + 
					"}")
			.when()
			.patch("/v1/cachorros/{id}", cachorroParaEditar.getId())
			.then()
			.assertThat()
			.statusCode(HttpStatus.OK.value())
			.body("id", Matchers.equalTo(cachorroParaEditar.getId().intValue()))
			.body("nome", Matchers.equalTo("Alexandre"))
			.body("raca", Matchers.equalTo("Terrier"))
			.body("porte", Matchers.equalTo("Médio"))
			.body("idade", Matchers.equalTo(2));	
		
		Cachorro cachorroAlterado = cachorroRepository.findById(cachorroParaEditar.getId()).get();
		
		Assert.assertEquals("Alexandre", cachorroAlterado.getNome());
		Assert.assertEquals("Terrier", cachorroAlterado.getRaca());
		Assert.assertEquals("Médio", cachorroAlterado.getPorte());
		Assert.assertEquals(2, cachorroAlterado.getIdade().intValue());
			
	}
	
}
