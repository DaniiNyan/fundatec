package br.com.fundatec.ExemploApi.integration;

import br.com.fundatec.ExemploApi.api.v1.dto.CachorroOutputDto;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fundatec.ExemploApi.entity.Cachorro;
import br.com.fundatec.ExemploApi.repository.CachorroRepository;
import io.restassured.RestAssured;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListarCachorroTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CachorroRepository cachorroRepository;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
		cachorroRepository.deleteAll();
	}
	
	@Test
	public void deveBuscarUmaListaDeCachorros() {
		cachorroRepository.save(new Cachorro("Bob", "Poodle", "Médio", 15));
		cachorroRepository.save(new Cachorro("Rex", "Pitbull", "Grande", 4));
		
		RestAssured
				.get("/v1/cachorros")
				.then()
				.body("nome", Matchers.hasItems("Bob", "Rex"))
				.body("raca", Matchers.hasItems("Poodle", "Pitbull"))
				.body("porte", Matchers.hasItems("Médio", "Grande"))
				.body("idade", Matchers.hasItems(15, 4))
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveListarCachorroFiltrandoPorNome() {
		cachorroRepository.save(new Cachorro("Bob", "Poodle", "Médio", 15));
		cachorroRepository.save(new Cachorro("Rex", "Pitbull", "Grande", 4));
		cachorroRepository.save(new Cachorro("Tobby", "Terrier", "Médio", 2));

		CachorroOutputDto[] resultado = RestAssured
				.given()
				.when()
				.get("/v1/cachorros?nome=OB")
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.extract()
				.as(CachorroOutputDto[].class);

		Assert.assertEquals(2, resultado.length);

		List<String> nomesEsperados = Arrays.asList("Bob", "Tobby");
		List<String> racasEsperados = Arrays.asList("Poodle", "Terrier");
		List<String> portesEsperados = Arrays.asList("Médio");
		List<Integer> idadesEsperados = Arrays.asList(15, 2);

		for (CachorroOutputDto cachorroOutputDto : resultado) {
			Assert.assertTrue(nomesEsperados.contains(cachorroOutputDto.getNome()));
			Assert.assertTrue(racasEsperados.contains(cachorroOutputDto.getRaca()));
			Assert.assertTrue(portesEsperados.contains(cachorroOutputDto.getPorte()));
			Assert.assertTrue(idadesEsperados.contains(cachorroOutputDto.getIdade()));
		}
	}

	@Test
	public void buscaCachorrosPorIntervaloDeIdade() {
		cachorroRepository.save(new Cachorro("Bob", "Poodle", "Médio", 14));
		cachorroRepository.save(new Cachorro("Rex", "Pitbull", "Grande", 4));
		cachorroRepository.save(new Cachorro("Tobby", "Terrier", "Médio", 2));
		cachorroRepository.save(new Cachorro("Obito", "Chihuahua", "Pequeno", 3));

		CachorroOutputDto[] resultado = RestAssured
				.given()
				.when()
				.get("/v1/cachorros/exercicio?nome=ob&idadeMinima=1&idadeMaxima=15")
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.extract()
				.as(CachorroOutputDto[].class);

		Assert.assertEquals(3, resultado.length);

		List<String> nomesEsperados = Arrays.asList("Bob", "Tobby", "Obito");
		List<String> racasEsperados = Arrays.asList("Poodle", "Terrier", "Chihuahua");
		List<String> portesEsperados = Arrays.asList("Médio", "Pequeno");
		List<Integer> idadesEsperados = Arrays.asList(2, 3, 14);

		for (CachorroOutputDto cachorroOutputDto : resultado) {
			Assert.assertTrue(nomesEsperados.contains(cachorroOutputDto.getNome()));
			Assert.assertTrue(racasEsperados.contains(cachorroOutputDto.getRaca()));
			Assert.assertTrue(portesEsperados.contains(cachorroOutputDto.getPorte()));
			Assert.assertTrue(idadesEsperados.contains(cachorroOutputDto.getIdade()));
		}

	}

}
