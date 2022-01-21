package com.fundatec.lpi.services;

public class CapitalizaNome implements TransformadorNome{

	@Override
	public String transforma(String nome) {
		String nomeTransformado = "";
		
		for (int i = 0; i < nome.length(); i++) {
			char letraAtual = nome.charAt(i);			
			
			if (i == 0) {
				nomeTransformado += Character.toUpperCase(letraAtual);
				
			} else {
				nomeTransformado += Character.toLowerCase(letraAtual);
			}
		}
		
		return nomeTransformado;
	}

}
