package com.fundatec.lpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fundatec.lpi.domain.Brinde;

public class BrindeDAO implements BaseDAO<Brinde>{

	@Override
	public void save(Brinde brinde) {
		try {
			String mysqlDriver = "com.mysql.cj.jdbc.Driver"; 
			Class.forName(mysqlDriver);

			String connectionString = "jdbc:mysql://localhost/trabalho-final?user=root&password=";
			Connection connect = DriverManager.getConnection(connectionString);

			String query = "INSERT INTO BRINDES (NOME, ITEM_ID) VALUES (?, ?)";
			
			PreparedStatement preparedStmt = connect.prepareStatement(query);
		    preparedStmt.setString (1, brinde.getNome());
		    preparedStmt.setInt(2, brinde.getItemID());
		    preparedStmt.execute();
		    
		    int linhasAfetadas = preparedStmt.getUpdateCount();
		    if (linhasAfetadas < 1) {
		    	System.out.println("Id inválido.");
		    } else {
		    	System.out.println("Cadastrado com sucesso!");
		    }
		    
			
		} catch (Exception e) {
			System.out.println("Algo deu errado, tente novamente.");
		}
		
	}

	@Override
	public List<Brinde> listAll() {
		List<Brinde> resultado = new ArrayList<Brinde>();
		
		try {
			String mysqlDriver = "com.mysql.cj.jdbc.Driver"; 
			Class.forName(mysqlDriver);

			String connectionString = "jdbc:mysql://localhost/trabalho-final?user=root&password=";
			Connection connect = DriverManager.getConnection(connectionString);
			
			Statement statement = connect.createStatement();
			String query = "SELECT * FROM BRINDES";
			
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {				
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				int itemID = resultSet.getInt("item_id");
				
				Brinde brinde = new Brinde(id, nome, itemID);
				resultado.add(brinde);
			}			
			
		} catch (Exception e) {
			System.out.println("Algo deu errado, tente novamente.");
		}
		
		return resultado;
	}

	@Override
	public void delete(int id) {
		try {
			String mysqlDriver = "com.mysql.cj.jdbc.Driver"; 
			Class.forName(mysqlDriver);

			String connectionString = "jdbc:mysql://localhost/trabalho-final?user=root&password=";
			Connection connect = DriverManager.getConnection(connectionString);
			
			String query = "DELETE FROM BRINDES WHERE ID = " + id + ";";
			
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.execute();
			
			int linhasAfetadas = preparedStmt.getUpdateCount();
		    if (linhasAfetadas < 1) {
		    	System.out.println("Id inválido.");
		    } else {
		    	System.out.println("Deletado com sucesso!");
		    }   
			
			
		} catch (Exception e) {
			System.out.println("Algo deu errado, tente novamente.");
		}
		
	}

	@Override
	public void update(Brinde brinde) {
		try {
			String mysqlDriver = "com.mysql.cj.jdbc.Driver"; 
			Class.forName(mysqlDriver);

			String connectionString = "jdbc:mysql://localhost/trabalho-final?user=root&password=";
			Connection connect = DriverManager.getConnection(connectionString);
			
			String query = "UPDATE BRINDES SET NOME = ?, ITEM_ID = ? WHERE ID = ?;";
			
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString (1, brinde.getNome());
		    preparedStmt.setInt(2, brinde.getItemID());	
		    preparedStmt.setInt(3, brinde.getId());
		    preparedStmt.execute();
		    
		    int linhasAfetadas = preparedStmt.getUpdateCount();
		    if (linhasAfetadas < 1) {
		    	System.out.println("Id inválido.");
		    } else {
		    	System.out.println("Editado com sucesso!");
		    }
			
		} catch (Exception e) {
			System.out.println("Algo deu errado, tente novamente.");
		}
	}

}
