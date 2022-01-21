package com.fundatec.lpi.main;

import java.util.List;
import java.util.Scanner;

import com.fundatec.lpi.dao.BrindeDAO;
import com.fundatec.lpi.dao.ItemDAO;
import com.fundatec.lpi.domain.Brinde;
import com.fundatec.lpi.domain.Estoque;
import com.fundatec.lpi.domain.Item;
import com.fundatec.lpi.services.CapitalizaNome;

public class App {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int comando = 0;

		do {
			System.out.println("Sistema de Estoque - Escolha uma opção:");
			System.out.println("---------------------------------------");
			System.out.println("1 - Cadastro de item");
			System.out.println("2 - Listar itens");
			System.out.println("3 - Editar item por ID");
			System.out.println("4 - Excluir item por ID");
			System.out.println("5 - Mostrar custo total do estoque");
			System.out.println("");
			System.out.println("6 - Cadastro de brinde");
			System.out.println("7 - Editar brinde por ID");
			System.out.println("8 - Excluir brinde por ID");
			System.out.println("0 - Sair");
			System.out.println("");
			System.out.println("Qual opção?");
			comando = teclado.nextInt();

			switch (comando) {
			case 1: {
				System.out.println("Você escolheu cadastrar item.");
				System.out.println("=> NOME DO ITEM: ");
				String nome = teclado.next();

				System.out.println("=> PREÇO DO ITEM: ");
				float preco = teclado.nextFloat();

				CapitalizaNome capitalizaNome = new CapitalizaNome();
				String nomeCapitalizado = capitalizaNome.transforma(nome);

				Item item = new Item(nomeCapitalizado, preco);
				ItemDAO itemDAO = new ItemDAO();
				itemDAO.save(item);

				System.out.println("Salvo com sucesso!");
				System.out.println("---------------------------------------");
				break;
			}

			case 2: {
				System.out.println("Você escolheu listar itens.");

				ItemDAO itemDAO = new ItemDAO();
				List<Item> listaItens = itemDAO.listAll();

				BrindeDAO brindeDAO = new BrindeDAO();
				List<Brinde> listaBrindes = brindeDAO.listAll();

				for (Item item : listaItens) {
					int id = item.getId();
					String nome = item.getNome();
					float preco = item.getPreco();
					
					String infoBrinde = "(sem brinde)";

					for (Brinde brinde : listaBrindes) {
						if (brinde.getItemID() == item.getId()) {
							int idBrinde = brinde.getId();
							String nomeBrinde = brinde.getNome();
							infoBrinde = "Brinde: " + idBrinde + " - " + nomeBrinde;
							break;
						}
					}
					
					System.out.format("%s - %s - %s --- %s\n", id, nome, preco, infoBrinde);
				}
				System.out.println("---------------------------------------");
				break;

			}

			case 3: {
				System.out.println("Você escolheu editar item.");
				System.out.println("=> ID DO ITEM: ");
				int idOriginal = teclado.nextInt();

				System.out.println("=> NOVO NOME: ");
				String novoNome = teclado.next();

				System.out.println("=> NOVO PREÇO: ");
				float novoPreco = teclado.nextFloat();

				Item item = new Item(idOriginal, novoNome, novoPreco);
				ItemDAO itemDAO = new ItemDAO();
				itemDAO.update(item);

				System.out.println("---------------------------------------");
				break;

			}

			case 4: {
				System.out.println("Você escolheu excluir item.");
				System.out.println("=> ID DO ITEM: ");
				int id = teclado.nextInt();

				ItemDAO itemDAO = new ItemDAO();
				itemDAO.delete(id);

				System.out.println("---------------------------------------");

				break;
			}

			case 5: {
				System.out.println("Custo total do estoque:");

				ItemDAO itemDAO = new ItemDAO();
				List<Item> listaItens = itemDAO.listAll();

				Estoque estoque = new Estoque();
				System.out.println(estoque.custoTotal(listaItens));

				System.out.println("---------------------------------------");
				break;
			}

			case 6: {
				System.out.println("Você escolheu cadastrar brinde.");
				System.out.println("=> NOME DO BRINDE: ");
				String nome = teclado.next();

				System.out.println("=> ID DO ITEM: ");
				int itemID = teclado.nextInt();

				Brinde brinde = new Brinde(nome, itemID);
				BrindeDAO brindeDAO = new BrindeDAO();

				brindeDAO.save(brinde);

				System.out.println("---------------------------------------");
				break;
			}

			case 7: {
				System.out.println("Você escolheu editar brinde.");
				System.out.println("=> ID DO BRINDE: ");
				int idOriginal = teclado.nextInt();

				System.out.println("=> NOVO NOME: ");
				String novoNome = teclado.next();

				System.out.println("=> NOVO ID DO ITEM: ");
				int novoItemID = teclado.nextInt();
				
				Brinde brinde = new Brinde(idOriginal, novoNome, novoItemID);
				BrindeDAO brindeDAO = new BrindeDAO();
				brindeDAO.update(brinde);

				System.out.println("---------------------------------------");
				break;
			}

			case 8: {
				System.out.println("Você escolheu excluir brinde.");
				System.out.println("=> ID DO BRINDE:");
				int id = teclado.nextInt();
				
				BrindeDAO brindeDAO = new BrindeDAO();
				brindeDAO.delete(id);

				System.out.println("---------------------------------------");
				break;
			}

			case 0: {
				System.out.println("---------------------------------------");
				System.out.println("Você saiu.");

				break;
			}

			default: {
				System.out.println("Você não digitou um comando válido.");
				System.out.println("---------------------------------------");
				break;
			}

			}

		} while (comando != 0);

		teclado.close();
	}
}
