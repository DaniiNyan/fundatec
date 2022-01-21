package com.fundatec.lpii.pizzaria.repository;

import com.fundatec.lpii.pizzaria.entity.Mesa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends CrudRepository<Mesa, Long> {
    List<Mesa> findAll();
}
