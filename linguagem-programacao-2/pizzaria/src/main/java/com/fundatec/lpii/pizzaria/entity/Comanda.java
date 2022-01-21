package com.fundatec.lpii.pizzaria.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;

    @ManyToOne
    @JoinColumn(name = "id_mesa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Mesa mesa;

    @ManyToMany
    @JoinTable(name="comanda_consumo",
            joinColumns= {@JoinColumn(name="id_comanda")},
            inverseJoinColumns= {@JoinColumn(name="id_consumo")})
    private Set<Consumo> consumos = new HashSet<>();

    public Comanda() {
    }

    public Comanda(String cliente) {
        this.cliente = cliente;
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
