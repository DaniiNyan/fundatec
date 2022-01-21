package com.fundatec.lpii.pizzaria.service;

import com.fundatec.lpii.pizzaria.entity.Comanda;
import com.fundatec.lpii.pizzaria.repository.ComandaRepository;
import org.springframework.stereotype.Service;

@Service
public class ComandaService {

    private ComandaRepository comandaRepository;

    public ComandaService(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    public Comanda salva(Comanda comanda) {
        return comandaRepository.save(comanda);
    }
}
