package br.org.fundatec.lp3.dip.service;

public class GerenciadorContabil {
    public void contabiliza(double valor, double imposto) {
    	String msg = new StringBuilder().append("Contabilizando nota fiscal:\n")
   				.append("Valor: " + valor+"\n")
   				.append("Imposto: " + imposto+"\n")
   				.toString();

    	System.out.println(msg);
    }
}
