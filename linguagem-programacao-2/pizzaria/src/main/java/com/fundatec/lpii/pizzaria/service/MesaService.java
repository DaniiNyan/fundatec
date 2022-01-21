package com.fundatec.lpii.pizzaria.service;

import com.fundatec.lpii.pizzaria.entity.Comanda;
import com.fundatec.lpii.pizzaria.entity.Mesa;
import com.fundatec.lpii.pizzaria.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    private MesaRepository repository;
    private ComandaService comandaService;

    public MesaService(MesaRepository repository, ComandaService comandaService) {
        this.repository = repository;
        this.comandaService = comandaService;
    }

    public List<Mesa> getAll() {
        return repository.findAll();
    }

    public Mesa encontra(Long id) {
        return repository.findById(id).get();
    }

    public Mesa adiciona(Mesa mesa) {
        return repository.save(mesa);
    }

    public Comanda incluiComanda(Long idMesa, Comanda comanda) {
        Mesa mesaEncontrada = encontra(idMesa);
//        mesaEncontrada.adicionaComanda(comanda);
        comanda.setMesa(mesaEncontrada);
        return comandaService.salva(comanda);
    }
}
