package br.org.fundatec.lp3.dip.service;

public class EnviadorDeSMS {
    public void enviarSmsDeConfirmacao(String telefoneContato, String mensagemDeConfirmacao) {

    	String msg = new StringBuilder().append("Enviando SMS de confirmação\n")
    					   				.append("Tel: " + telefoneContato+"\n")
    					   				.append("Mensagem: " + mensagemDeConfirmacao+"\n")
    					   				.toString();

    	System.out.println(msg);
    }
}
