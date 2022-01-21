package com.fundatec.lpii.pizzaria.api.v1.dto;

import com.fundatec.lpii.pizzaria.entity.Comanda;

import java.util.ArrayList;
import java.util.List;

public class MesaInputDto {

    private String identificador;
    private List<Comanda> comandas = new ArrayList<>();

    public MesaInputDto() {
    }

    public MesaInputDto(String identificador) {
        this.identificador = identificador;
    }

    public void adicionarComanda(Comanda comanda) {
        this.comandas.add(comanda);

    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

}
