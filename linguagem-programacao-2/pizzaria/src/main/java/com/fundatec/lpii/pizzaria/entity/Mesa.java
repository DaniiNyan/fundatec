package com.fundatec.lpii.pizzaria.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificador;

    @OneToMany(mappedBy = "mesa")
    private List<Comanda> comandas = new ArrayList<>();

    public Mesa() {
    }

    public Mesa(String identificador) {
        this.identificador = identificador;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public void adicionaComanda(Comanda comanda) {
        this.comandas.add(comanda);
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
