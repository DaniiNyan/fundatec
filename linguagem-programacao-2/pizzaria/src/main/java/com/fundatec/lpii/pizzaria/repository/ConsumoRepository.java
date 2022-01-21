package com.fundatec.lpii.pizzaria.repository;

import com.fundatec.lpii.pizzaria.entity.Consumo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumoRepository extends CrudRepository<Consumo, Long> {

    List<Consumo> findAll();

}
