package com.fundatec.lpii.pizzaria.repository;

import com.fundatec.lpii.pizzaria.entity.Comanda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComandaRepository extends CrudRepository<Comanda, Long> {

    List<Comanda> findAll();
}
