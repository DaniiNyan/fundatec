package br.com.fundatec.ExemploApi.api.v1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fundatec.ExemploApi.api.v1.dto.CachorroOutputDto;
import br.com.fundatec.ExemploApi.api.v1.dto.ErroDto;
import br.com.fundatec.ExemploApi.api.v1.dto.CachorroAlterarIdadeDto;
import br.com.fundatec.ExemploApi.api.v1.dto.CachorroInputDto;
import br.com.fundatec.ExemploApi.entity.Cachorro;
import br.com.fundatec.ExemploApi.mapper.CachorroMapper;
import br.com.fundatec.ExemploApi.service.CachorroService;

@RestController
public class CachorroController {

	private CachorroService cachorroService;
	private CachorroMapper cachorroMapper;

	public CachorroController(CachorroService cachorroService, CachorroMapper cachorroMapper) {
		this.cachorroService = cachorroService;
		this.cachorroMapper = cachorroMapper;
	}

	@GetMapping("/v1/cachorros")
	@ApiOperation(value = "Listar todos os cachorros",
				notes = "Lista todos os cachorros cadastrados no sistema.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cachorros retornados com sucesso", response = CachorroOutputDto[]	.class),
	})
	public ResponseEntity<List<CachorroOutputDto>> getCachorros(@RequestParam(defaultValue = "") String nome) {
		List<Cachorro> listaCachorro = cachorroService.listar(nome);
		List<CachorroOutputDto> listaCachorroDto = cachorroMapper.mapearListaCachorroOutputDto(listaCachorro);
		return ResponseEntity.status(HttpStatus.OK).body(listaCachorroDto);
	}

	@PostMapping("/v1/cachorros")
	@ApiOperation(value = "Inserir um cachorro",
			notes = "Insere um cachorro no sistema.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cachorro inserido com sucesso", response = CachorroOutputDto[]	.class),
	})
	public ResponseEntity<?> incluirCachorro(@Valid @RequestBody CachorroInputDto cachorroInputDto) {

		try {
			Cachorro cachorro = cachorroMapper.mapearCachorro(cachorroInputDto);
			cachorro = cachorroService.salvar(cachorro);
			CachorroOutputDto cachorroOutputDto = cachorroMapper.mapearCachorroOutputDto(cachorro);

			return ResponseEntity.status(HttpStatus.CREATED).body(cachorroOutputDto);

		} catch (IllegalArgumentException e) {
			ErroDto erroDto = new ErroDto(e.getMessage());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(erroDto);

		} catch (Exception e) {
			ErroDto erroDto = new ErroDto(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroDto);
		}
	}

	@PutMapping("/v1/cachorros/{id}")
	@ApiOperation(value = "Alterar cachorro por id",
			notes = "Busca um cachorro pelo id e altera todos os seus dados.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cachorro alterado com sucesso", response = CachorroOutputDto[].class),
	})
	public ResponseEntity<?> alterarCachorro(@PathVariable Long id, @RequestBody @Valid CachorroInputDto cachorroInputDto) {

		try {
			Cachorro cachorro = cachorroMapper.mapearCachorro(cachorroInputDto);
			cachorro.setId(id);
			cachorro = cachorroService.salvar(cachorro);

			CachorroOutputDto cachorroOutputDto = cachorroMapper.mapearCachorroOutputDto(cachorro);

			return ResponseEntity.ok(cachorroOutputDto);

		} catch (IllegalArgumentException e) {
			ErroDto erroDto = new ErroDto(e.getMessage());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(erroDto);

		} catch (Exception e) {
			ErroDto erroDto = new ErroDto(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroDto);
		}
	}

	@PatchMapping("/v1/cachorros/{id}")
	@ApiOperation(value = "Alterar cachorro parcialmente por id",
			notes = "Busca um cachorro pelo id e altera algum de seus seus dados.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cachorro alterado com sucesso", response = CachorroOutputDto[].class),
	})
	public ResponseEntity<?> alteraParcialmenteCachorro(@PathVariable Long id, @RequestBody @Valid CachorroAlterarIdadeDto cachorroAlterarIdadeDto) {
		Cachorro cachorro = cachorroService.consultar(id);
		cachorro.setIdade(cachorroAlterarIdadeDto.getIdade());
		cachorro = cachorroService.salvar(cachorro);

		CachorroOutputDto cachorroOutputDto = cachorroMapper.mapearCachorroOutputDto(cachorro);
		return ResponseEntity.ok(cachorroOutputDto);
	}

	@GetMapping("/v1/cachorros/exercicio")
	@ApiOperation(value = "Busca cachorros por intervalo de idade",
			notes = "Busca todos os cachorros cuja idade esteja entre o intervalo passados.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cachorro retornado com sucesso", response = CachorroOutputDto[].class),
	})
	public ResponseEntity<List<CachorroOutputDto>> getCachorrosPorIntervaloDeIdade(@RequestParam(defaultValue = "") String nome,
																				   @RequestParam Integer idadeMinima,
																				   @RequestParam Integer idadeMaxima) {

		List<Cachorro> listaCachorro = cachorroService.listarPorIntervaloDeIdade(nome, idadeMinima, idadeMaxima);
		List<CachorroOutputDto> listaCachorroDto = cachorroMapper.mapearListaCachorroOutputDto(listaCachorro);

		return ResponseEntity.status(HttpStatus.OK).body(listaCachorroDto);
	}

	@GetMapping("/v1/cachorros/exercicio/lista")
	@ApiOperation(value = "Busca cachorros semelhantes",
			notes = "Busca todos os cachorros que contenham algum dos parâmetros solicitados.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cachorro alterado com sucesso", response = CachorroOutputDto[].class),
	})
	public ResponseEntity<List<CachorroOutputDto>> getCachorrosSemelhantes(@RequestParam(required = false) Long id,
																		   @RequestParam(required = false) String nome,
																		   @RequestParam(required = false) String raca,
																		   @RequestParam(required = false) String porte,
																		   @RequestParam(required = false) Integer idade) {

		List<Cachorro> listaCachorro = cachorroService.encontrarSemelhantes(id, nome, raca, porte, idade);
		List<CachorroOutputDto> listaCachorroDto = cachorroMapper.mapearListaCachorroOutputDto(listaCachorro);

		return ResponseEntity.status(HttpStatus.OK).body(listaCachorroDto);
	}


}
