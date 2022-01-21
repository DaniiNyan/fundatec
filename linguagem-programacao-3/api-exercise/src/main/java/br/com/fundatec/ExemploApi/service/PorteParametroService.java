package br.com.fundatec.ExemploApi.service;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.fundatec.ExemploApi.entity.Cachorro;
import br.com.fundatec.ExemploApi.entity.PorteParametro;
import br.com.fundatec.ExemploApi.repository.PorteParametroRepository;

@Service
public class PorteParametroService {

	private PorteParametroRepository porteParametroRepository;
	
	public PorteParametroService(PorteParametroRepository porteParametroRepository) {
		this.porteParametroRepository = porteParametroRepository;
	}

	public boolean porteValido(String porte) {
		List<PorteParametro> listaPorteParametro = (List<PorteParametro>) porteParametroRepository.findAll();
		
		for (PorteParametro porteParametro : listaPorteParametro) {
			if (porteParametro.getNome().equals(porte)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public boolean porteValidoPorRaca(Cachorro cachorro) {		
		String porte = cachorro.getPorte();
		String raca = cachorro.getRaca();
					
		Map<String, List<String>> mapaDePortesPorRacas = new HashMap<String, List<String>>();
		mapaDePortesPorRacas.put("Pequeno", Arrays.asList("Vira-Lata",
															"Poodle",
															"Chihuahua",
															"Pinscher",
															"Dachshund"));
		
		mapaDePortesPorRacas.put("M�dio", Arrays.asList("Vira-Lata",
														"Chow Chow",
														"Terrier"));
		
		mapaDePortesPorRacas.put("Grande", Arrays.asList("Vira-Lata",
														"Pastor Belga"));
		
		List<String> listaDeRacas = mapaDePortesPorRacas.get(porte);
		return listaDeRacas.contains(raca);
		
	}

}
