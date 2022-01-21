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
public class AlterarCachorroTest {
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
		porteParametroRepository.save(new PorteParametro("M�dio"));
		porteParametroRepository.save(new PorteParametro("Grande"));
		cachorroParaEditar = new Cachorro("Alexandre", "Terrier", "M�dio", 1);
		cachorroParaEditar = cachorroRepository.save(cachorroParaEditar);
	}
	
	@Test
	public void deveAlterarCachorro() {		
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"Alex\"," + 
					"	\"raca\": \"Terrier\"," + 
					"	\"porte\": \"M�dio\"," + 
					"	\"idade\": 2" + 
					"}")
			.when()
			.put("/v1/cachorros/{id}", cachorroParaEditar.getId())
			.then()
			.assertThat()
			.statusCode(HttpStatus.OK.value())
			.body("id", Matchers.equalTo(cachorroParaEditar.getId().intValue()))
			.body("nome", Matchers.equalTo("Alex"))
			.body("raca", Matchers.equalTo("Terrier"))
			.body("porte", Matchers.equalTo("M�dio"))
			.body("idade", Matchers.equalTo(2));	
		
		Cachorro cachorroAlterado = cachorroRepository.findById(cachorroParaEditar.getId()).get();
		
		Assert.assertEquals("Alex", cachorroAlterado.getNome());
		Assert.assertEquals("Terrier", cachorroAlterado.getRaca());
		Assert.assertEquals("M�dio", cachorroAlterado.getPorte());
		Assert.assertEquals(2, cachorroAlterado.getIdade().intValue());
			
	}
	
	@Test
	public void deveValidarNomeVazioCachorroEditado() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"\"," + 
					"	\"raca\": \"Terrier\"," + 
					"	\"porte\": \"M�dio\"," + 
					"	\"idade\": 2" + 
					"}")
			.when()
			.put("/v1/cachorros/{id}", cachorroParaEditar.getId())
			.then()
			.assertThat()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("errors[0].defaultMessage", Matchers.equalTo("O campo nome � obrigat�rio."));
		
		Cachorro cachorroAlterado = cachorroRepository.findById(cachorroParaEditar.getId()).get();
		
		Assert.assertEquals("Alexandre", cachorroAlterado.getNome());
		Assert.assertEquals("Terrier", cachorroAlterado.getRaca());
		Assert.assertEquals("M�dio", cachorroAlterado.getPorte());
		Assert.assertEquals(1, cachorroAlterado.getIdade().intValue());
	}
	
	@Test
	public void deveValidarNomeNuloCachorroEditado() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"raca\": \"Terrier\"," + 
					"	\"porte\": \"M�dio\"," + 
					"	\"idade\": 2" + 
					"}")
			.when()
			.put("/v1/cachorros/{id}", cachorroParaEditar.getId())
			.then()
			.assertThat()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("errors[0].defaultMessage", Matchers.equalTo("O campo nome � obrigat�rio."));
		
		Cachorro cachorroAlterado = cachorroRepository.findById(cachorroParaEditar.getId()).get();
		
		Assert.assertEquals("Alexandre", cachorroAlterado.getNome());
		Assert.assertEquals("Terrier", cachorroAlterado.getRaca());
		Assert.assertEquals("M�dio", cachorroAlterado.getPorte());
		Assert.assertEquals(1, cachorroAlterado.getIdade().intValue());
	}
	
	@Test
	public void deveValidarCpcInvalido() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"Alexandre\"," + 
					"	\"raca\": \"Terrier\"," + 
					"	\"porte\": \"M�dio\"," + 
					"	\"idade\": 1," + 
					"	\"cpc\": \"cpc\" " +	
					"}")
			.when()
			.put("/v1/cachorros/{id}", cachorroParaEditar.getId())
			.then()
			.assertThat()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("errors[0].defaultMessage", Matchers.equalTo("Campo CPC inv�lido."));
		
		Cachorro cachorroAlterado = cachorroRepository.findById(cachorroParaEditar.getId()).get();
		
		Assert.assertEquals("Alexandre", cachorroAlterado.getNome());
		Assert.assertEquals("Terrier", cachorroAlterado.getRaca());
		Assert.assertEquals("M�dio", cachorroAlterado.getPorte());
		Assert.assertEquals(1, cachorroAlterado.getIdade().intValue());
		
	}
	
	@Test
	public void deveValidarPorte() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"Alexandre\"," + 
					"	\"raca\": \"Terrier\"," + 
					"	\"porte\": \"Inv�lido\"," + 
					"	\"idade\": 1" +	
					"}")
			.when()
			.put("/v1/cachorros/{id}", cachorroParaEditar.getId())
			.then()
			.assertThat()
			.body("mensagem", Matchers.equalTo("Porte inv�lido."))
			.statusCode(HttpStatus.EXPECTATION_FAILED.value());
		
		Cachorro cachorroAlterado = cachorroRepository.findById(cachorroParaEditar.getId()).get();
		
		Assert.assertEquals("Alexandre", cachorroAlterado.getNome());
		Assert.assertEquals("Terrier", cachorroAlterado.getRaca());
		Assert.assertEquals("M�dio", cachorroAlterado.getPorte());
		Assert.assertEquals(1, cachorroAlterado.getIdade().intValue());
		
	}
	
	@Test
	public void deveValidarPorteDeAcordoComRaca() {
		RestAssured
			.given()
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body("{" + 
					"	\"nome\": \"Alexandre\"," + 
					"	\"raca\": \"Terrier\"," + 
					"	\"porte\": \"Pequeno\"," + 
					"	\"idade\": 1" +
					"}")
			.when()
			.put("/v1/cachorros/{id}", cachorroParaEditar.getId())
			.then()
			.assertThat()
			.body("mensagem", Matchers.equalTo("Porte inv�lido para esta ra�a."))
			.statusCode(HttpStatus.EXPECTATION_FAILED.value());
	
		Cachorro cachorroAlterado = cachorroRepository.findById(cachorroParaEditar.getId()).get();
		
		Assert.assertEquals("Alexandre", cachorroAlterado.getNome());
		Assert.assertEquals("Terrier", cachorroAlterado.getRaca());
		Assert.assertEquals("M�dio", cachorroAlterado.getPorte());
		Assert.assertEquals(1, cachorroAlterado.getIdade().intValue());
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
					"	\"porte\": \"Inv�lido\"," + 
					"	\"idade\": -1" +	
					"}")
			.when()
			.put("/v1/cachorros/{id}", cachorroParaEditar.getId())
			.then()
			.assertThat()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("errors[0].defaultMessage", Matchers.equalTo("Idade deve ser maior ou igual a zero."));
		
		
		Cachorro cachorroAlterado = cachorroRepository.findById(cachorroParaEditar.getId()).get();
		
		Assert.assertEquals("Alexandre", cachorroAlterado.getNome());
		Assert.assertEquals("Terrier", cachorroAlterado.getRaca());
		Assert.assertEquals("M�dio", cachorroAlterado.getPorte());
		Assert.assertEquals(1, cachorroAlterado.getIdade().intValue());
		
	}
	
	
	
	
}
