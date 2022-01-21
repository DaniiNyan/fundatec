package com.fundatec.lpi.domain;

import java.util.ArrayList;
import java.util.List;

import com.fundatec.lpi.dao.ItemDAO;

public class Estoque {

	public float custoTotal(List<Item> itens) {
		float valorTotal = 0;
		
		for (Item item : itens) {
			valorTotal += item.getPreco();
		}		

		return valorTotal;
	}

}
