package com.fundatec.lpii.pizzaria.api.v1.dto;

import com.fundatec.lpii.pizzaria.entity.Consumo;
import com.fundatec.lpii.pizzaria.entity.Mesa;

import java.util.Set;

public class ComandaInputDto {
    private String cliente;
    private Mesa mesa;
    private Set<Consumo> consumos;

    public ComandaInputDto(String cliente) {
        this.cliente = cliente;
    }

    public ComandaInputDto() {
    }

    public void inserirConsumo(Consumo consumo) {
        this.consumos.add(consumo);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Set<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(Set<Consumo> consumos) {
        this.consumos = consumos;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
