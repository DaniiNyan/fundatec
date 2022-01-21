package com.fundatec.lpii.pizzaria.service;

import com.fundatec.lpii.pizzaria.entity.Consumo;
import com.fundatec.lpii.pizzaria.repository.ConsumoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumoService {

    private ConsumoRepository repository;

    public ConsumoService(ConsumoRepository repository) {
        this.repository = repository;
    }

    public List<Consumo> getAll() {
        return repository.findAll();
    }

    public Consumo salvar(Consumo consumo) {
        return repository.save(consumo);
    }
}
