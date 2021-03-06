package br.com.fundatec.ExemploApi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fundatec.ExemploApi.entity.Cachorro;

import java.util.List;

@Repository
public interface CachorroRepository extends CrudRepository<Cachorro, Long>{

    List<Cachorro> findByNomeContainingIgnoringCase(String nome);
    List<Cachorro> findByNomeContainingIgnoringCaseAndIdadeBetween(String nome, Integer idadeMinima, Integer idadeMaxima);

    @Query(" select cachorro from Cachorro cachorro " +
            " where cachorro.id = :id " +
            " or cachorro.nome = :nome " +
            " or cachorro.raca = :raca " +
            " or cachorro.porte = :porte " +
            " or cachorro.idade = :idade ")
    List<Cachorro> buscarSemelhantes(@Param(value = "id") Long id,
                                     @Param(value = "nome") String nome,
                                     @Param(value = "raca") String raca,
                                     @Param(value = "porte") String porte,
                                     @Param(value = "idade") Integer idade);

}

