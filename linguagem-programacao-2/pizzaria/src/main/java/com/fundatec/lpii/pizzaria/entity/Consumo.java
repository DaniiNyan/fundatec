package com.fundatec.lpii.pizzaria.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consumo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nomeDoProduto;
    private Double preco;

    public Consumo() {
    }

    public Consumo(String nomeDoProduto, Double preco) {
        this.nomeDoProduto = nomeDoProduto;
        this.preco = preco;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
