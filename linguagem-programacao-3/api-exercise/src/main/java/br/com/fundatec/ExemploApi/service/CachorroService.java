package br.com.fundatec.ExemploApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fundatec.ExemploApi.entity.Cachorro;
import br.com.fundatec.ExemploApi.repository.CachorroRepository;

@Service
public class CachorroService {

	private CachorroRepository cachorroRepository;
	private PorteParametroService porteParametroService;

	public CachorroService(CachorroRepository cachorroRepository, PorteParametroService porteParametroService) {
		this.cachorroRepository = cachorroRepository;
		this.porteParametroService = porteParametroService;
	}

	public List<Cachorro> listar(String nome) {
		return cachorroRepository.findByNomeContainingIgnoringCase(nome);
	}

	public Cachorro salvar(Cachorro cachorro) {
		validarSalvarCachorro(cachorro);
		return cachorroRepository.save(cachorro);
	}
	
	public void validarSalvarCachorro(Cachorro cachorro) {
		validarPorte(cachorro);
	}
	
	public void validarPorte(Cachorro cachorro) {
		if(!porteParametroService.porteValido(cachorro.getPorte())) {
			throw new IllegalArgumentException("Porte inválido.");
		}
		
		if(!porteParametroService.porteValidoPorRaca(cachorro)) {
			throw new IllegalArgumentException("Porte inválido para esta raça.");
		}
	}

	public Cachorro consultar(Long id) {
		return cachorroRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não encontrou cachorro para o id" + id));
	}

	public List<Cachorro> listarPorIntervaloDeIdade (String nome, Integer idadeMinima, Integer idadeMaxima) {
		return cachorroRepository.findByNomeContainingIgnoringCaseAndIdadeBetween(nome, idadeMinima, idadeMaxima);
	}

	public List<Cachorro> encontrarSemelhantes (Long id, String nome, String raca, String porte, Integer idade) {
	    return cachorroRepository.buscarSemelhantes(id, nome, raca, porte, idade);
    }
}
